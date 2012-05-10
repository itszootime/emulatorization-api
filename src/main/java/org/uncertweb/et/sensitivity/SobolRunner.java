package org.uncertweb.et.sensitivity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.R;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.LHSDesign;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class SobolRunner {
	private RConnection conn;
	private Design x;
	private String outputIdentifier;
	
	private static final Logger logger = LoggerFactory.getLogger(SobolRunner.class);
	
	public SobolRunner(LHSDesign x1, LHSDesign x2, int nboot, double conf)
	throws REngineException, REXPMismatchException, ConfigException {
		// connect to r
		conn = R.getConnection();

		// setup sobol
		assignDesignToDataFrame(x1, "X1");
		assignDesignToDataFrame(x2, "X2");
		
		// FIXME: nboot cannot equal 1, otherwise eval fails. at the moment nboot=0 is unsupported too.
		
		logger.info("Initialising sobol2002 with nboot=" + nboot + ", conf=" + conf + ".");
		conn.voidEval("saSobol <- sobol2002(model = NULL, X1, X2, nboot = "
			+ nboot + ", conf = " + conf + ")");

		// parse design
		x = readDataFrameAsDesign("saSobol$X", x1.getInputIdentifiers());
	}

	public Design getX() {
		return x;
	}

	public void setY(Double[] y) throws REngineException {
		// assign in R
		conn.assign("y", convertToPrimitiveArray(y));

		// tell sobol
		conn.voidEval("tell(saSobol, y)");
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
	
	public SobolOutputResult getResult() throws RserveException, REXPMismatchException {
		return this.getResult(false);
	}

	public SobolOutputResult getResult(boolean doPlot) throws RserveException, REXPMismatchException {
		// log
		logger.info("Generating result for output '" + outputIdentifier + "'.");
		
		// TODO: could cache result
		List<SobolInputResult> inputResults = new ArrayList<SobolInputResult>();
		List<String> inputIdentifiers = x.getInputIdentifiers();
		BufferedImage plot = null;
		for (int i = 1; i <= inputIdentifiers.size(); i++) {
			// get the estimations of Variances of the Conditional
			// Expectations (VCE) with respect
			// to each factor and also with respect to the complementary set
			// of each factor ("all
			// but Xi")
			// c.eval("saSobol$V");

			// the estimations of the Sobol first-order indices
			double[] firstArray = conn.eval("unlist(saSobol$S[" + i + ",])").asDoubles();

			// the estimations of the Sobol total sensitivity indices
			double[] totalArray = conn.eval("unlist(saSobol$T[" + i + ",])").asDoubles();

			// add to results
			// 0 bootstrap replicates only gives a single value, but limited by calling class for now
			inputResults.add(new SobolInputResult(inputIdentifiers.get(i - 1),
				firstArray[0], firstArray[1], firstArray[2], firstArray[3], firstArray[4],
				totalArray[0], totalArray[1], totalArray[2], totalArray[3], totalArray[4]));
			
			// do plot if requested
			
			if (doPlot) {
				plot = getPlot();
			}
		}
		return new SobolOutputResult(outputIdentifier, inputResults, plot);
	}

	private BufferedImage getPlot() throws RserveException, REXPMismatchException {
		BufferedImage plot = null;
		conn.voidEval("Cairo(width=940, height=500, file='plot.png')");
		conn.voidEval("plot(saSobol)");
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
		logger.info("Reading " + rows + "x" + cols + " design from data frame.");
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

	private void assignDesignToDataFrame(LHSDesign design, String name)
	throws RserveException, REngineException {
		// add values
		Double[][] matrix = design.getPoints();
		double[] flat = new double[design.getInputIdentifiers().size()
		                           * design.getSize()];
		for (int c = 0; c < matrix[0].length; c++) {
			for (int r = 0; r < matrix.length; r++) {
				flat[(c * matrix.length) + r] = matrix[r][c];
			}
		}

		// assign
		conn.assign(name, flat);
		logger.info("Creating data frame for " + name + " design.");
		conn.voidEval(name + " <- data.frame(matrix(" + name + ", "
			+ design.getSize() + "))");
		
		// set column names
		String[] names = design.getInputIdentifiers().toArray(new String[0]);
		conn.assign("names", names);
		conn.voidEval("colnames(" + name + ") <- names");
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