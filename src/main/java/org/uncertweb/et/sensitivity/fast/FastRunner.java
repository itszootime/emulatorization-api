package org.uncertweb.et.sensitivity.fast;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.R;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.sensitivity.AnalysisInputResult;
import org.uncertweb.et.sensitivity.AnalysisOutputResult;

//FIXME: code duplication from SobolRunner
public class FastRunner {
	private RConnection conn;
	private Design x;
	private String outputIdentifier;
	
	private static final Logger logger = LoggerFactory.getLogger(FastRunner.class);
	
	public FastRunner(List<VariableInput> inputs, int sampleSize)
	throws REngineException, REXPMismatchException, ConfigException {
		// connect to r
		conn = R.getConnection();

		// setup fast
		List<String> inputIdentifiers = new ArrayList<String>();
		String[] factorsArr = new String[inputs.size()];
		String[] qArr = new String[inputs.size()];
		String[] qargArr = new String[inputs.size()];
		for (int i = 0; i < inputs.size(); i++) {
			VariableInput input = inputs.get(i);
			inputIdentifiers.add(input.getIdentifier());
			factorsArr[i] = "'" + input.getIdentifier() + "'";
			qArr[i] = "'qunif'"; // or qnorm?
			qargArr[i] = "list(min = " + input.getMin() + ", max = " + input.getMax() + ")";
		}
		String factors = "c(" + StringUtils.join(factorsArr, ",") + ")";
		String q = "c(" + StringUtils.join(qArr, ",") + ")";
		String qarg = "list(" + StringUtils.join(qargArr, ",") + ")";
		
		
		// FIXME: sampleSize cannot be small, otherwise this fails
		
		logger.info("Initialising fast99 with n=" + sampleSize + ".");
		conn.voidEval("saFast <- fast99(model = NULL, factors = " + factors + ", n = "
			+ sampleSize + ", q = " + q + ", q.arg = " + qarg + ")");

		// parse design
		x = readDataFrameAsDesign("saFast$X", inputIdentifiers);
	}

	public Design getX() {
		return x;
	}

	public void setY(Double[] y) throws REngineException {
		// assign in R
		conn.assign("y", convertToPrimitiveArray(y));
		
		// tell sobol
		conn.voidEval("tell(saFast, y)");
	}

	// FIXME: just first for now - emulator is only trained to one output
	// anyway!
	public void setY(EmulatorEvaluationResult y) throws REngineException {
		outputIdentifier = y.getOutputIdentifiers().get(0);
		setY(y.getMeanResults(outputIdentifier));
	}
	
	public void setY(ProcessEvaluationResult y, String outputIdentifier) throws REngineException {
		// FIXME: this is a bit nasty could lose track of which output is set when calling getResult()
		this.outputIdentifier = outputIdentifier; 
		setY(y.getResults(outputIdentifier));
	}
	
	public AnalysisOutputResult getResult() throws RserveException, REXPMismatchException {
		return this.getResult(false);
	}

	public AnalysisOutputResult getResult(boolean doPlot) throws RserveException, REXPMismatchException {
		// log
		//logger.info("Generating result for output '" + outputIdentifier + "'.");
		
		// TODO: could cache result
		List<AnalysisInputResult> inputResults = new ArrayList<AnalysisInputResult>();
		List<String> inputIdentifiers = x.getInputIdentifiers();
		BufferedImage plot = null;
		for (int i = 1; i <= inputIdentifiers.size(); i++) {
			// the estimations of Variances of the Conditional Expectations (VCE) with respect
			// to each factor.
			// c.eval("saFast$D1");
			
			// the estimations of VCE with respect to each factor complementary set of factors
			// ("all but Xi").
			// c.eval("saFast$Dt");
			
			double d1 = conn.eval("saFast$D1[" + i + "]").asDouble();
			double dt = conn.eval("saFast$Dt[" + i + "]").asDouble();
			double v = conn.eval("saFast$V[" + i + "]").asDouble();

			// add to results
			inputResults.add(new FastInputResult(inputIdentifiers.get(i - 1), d1, dt, v));
			
			// do plot if requested			
			if (doPlot) {
				plot = getPlot();
			}
		}
		return new AnalysisOutputResult(outputIdentifier, inputResults, plot);
	}

	private BufferedImage getPlot() throws RserveException, REXPMismatchException {
		BufferedImage plot = null;
		conn.voidEval("Cairo(width=940, height=500, file='plot.png')");
		conn.voidEval("plot(saFast)");
		conn.voidEval("dev.off()");
		byte[] bytes = conn.eval("readBin('plot.png', 'raw', 999999)").asBytes();
		try {
			InputStream in = new ByteArrayInputStream(bytes);
			plot = ImageIO.read(in);
			in.close();
		}
		catch (IOException e) {
			// won't get one of these with a byte array input stream
		}
		conn.voidEval("unlink('plot.png')");
		return plot;
	}

	private double[] convertToPrimitiveArray(Double[] array) {
		double[] primitiveArray = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			primitiveArray[i] = array[i];
		}
		return primitiveArray;
	}
	
	private Design readDataFrameAsDesign(String string,
		List<String> inputIdentifiers) throws RserveException,
		REXPMismatchException {
		// get rows and cols
		int rows = conn.eval("nrow(" + string + ")").asInteger();
		int cols = conn.eval("ncol(" + string + ")").asInteger();

		// read
		//logger.info("Reading " + rows + "x" + cols + " design from data frame.");
		Design design = new Design(rows);
		for (int i = 0; i < inputIdentifiers.size(); i++) {
			String inputIdentifier = inputIdentifiers.get(i);
			double[] points = conn.eval(string + "[," + (i + 1) + "]").asDoubles();
			// cast
			Double[] cpoints = new Double[points.length];
			for (int ii = 0; ii < points.length; ii++) {
				cpoints[ii] = points[ii];
			}
			design.addPoints(inputIdentifier, cpoints);
		}

		// all done
		return design;
	}

	public void done() {
		if (conn != null) {
			conn.close();
		}
	}
	
	public String getLastError() {
		if (conn != null) {
			try {
				return conn.eval("geterrmessage()").asString();
			}
			catch (Exception e) {
				// ignore
			}
		}
		return null;
	}
}