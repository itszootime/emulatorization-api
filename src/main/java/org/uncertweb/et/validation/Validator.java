package org.uncertweb.et.validation;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.DesignException;
import org.uncertweb.et.design.LHSDesign;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.emulator.EmulatorEvaluator;
import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.plot.PlotData;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluator;
import org.uncertweb.et.process.ProcessEvaluatorException;
import org.uncertweb.et.value.Sample;
import org.uncertweb.et.value.SampleValues;
import org.uncertweb.et.value.Distribution;
import org.uncertweb.et.value.DistributionValues;
import org.uncertweb.et.value.Scalar;
import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLStruct;
import org.uncertweb.matlab.value.MLValue;

public class Validator {

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

	private ScalarValues observed;
	private Values predicted;
	
	private double rmse;
	private PlotData meanResidualHistogram;
	private PlotData medianResidualHistogram;
	private PlotData reliabilityDiagram;
	
	public Validator(ScalarValues observed, Values predicted) throws ValidatorException {
		this.observed = observed;
		this.predicted = predicted;
		calculateMetrics();
	}
	
	private double[][] transposeArray(double[] array) {
		// transpose array to matrix
		double[][] transpose = new double[array.length][1];
		for (int i = 0; i < transpose.length; i++) {
			transpose[i] = new double[] { array[i] };
		}
		return transpose;
	}

	private void calculateMetrics() throws ValidatorException {
		// build matlab request
		// FIXME: problems if sending ensemble matrix with 2 cols (code will think it's mean/variance)
		MLRequest request = new MLRequest("validate_predictions", 1);
		request.addParameter(new MLMatrix(transposeArray(observed.toArray())));
		request.addParameter(new MLMatrix(predicted.toMatrix()));
		
		// send
		try {
			MLResult result = MATLAB.sendRequest(request);
			MLStruct metrics = result.getResult(0).getAsStruct();
			
			// rmse
			rmse = getMetric(metrics, "mean.rmse");
			meanResidualHistogram = getPlotData(metrics, "meanresidual.histogram");						
			medianResidualHistogram = getPlotData(metrics, "medianresidual.histogram");
			reliabilityDiagram = getPlotData(metrics, "reliability");
		}
		catch (IOException e) {
			throw new ValidatorException("Couldn't perform validation.", e);
		}
		catch (MLException e) {
			throw new ValidatorException("Couldn't perform validation.", e);
		}
		catch (ConfigException e) {
			throw new ValidatorException("Couldn't perform validation.", e);
		}
	}
	
	private double getMetric(MLStruct metrics, String path) {
		// navigate
		MLValue current = metrics;
		for (String p : path.split("\\.")) {
			current = current.getAsStruct().getField(p);
		}
		
		// return
		return current.getAsScalar().getScalar();
	}
	
	private PlotData getPlotData(MLStruct metrics, String path) {
		// navigate
		MLStruct current = metrics;
		for (String p : path.split("\\.")) {
			current = current.getField(p).getAsStruct();
		}
		
		// build
		double[] x = current.getField("x").getAsArray().getArray();
		double[] y = current.getField("y").getAsArray().getArray();
		MLValue n = current.getField("n");
		if (n != null) {
			return new PlotData(x, y, n.getAsArray().getArray());
		}
		else {
			return new PlotData(x, y);
		}
	}

	public ScalarValues getObserved() {
		return observed;
	}

	public Values getPredicted() {
		return predicted;
	}

	public static Validator usingSimulatorAndEmulator(String serviceURL, String processIdentifier, Emulator emulator, int designSize) throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
		// get inputs and outputs from emulator
		List<Input> inputs = emulator.getInputs();
		List<Output> outputs = emulator.getOutputs();

		// create design for evaluation
		Design design = LHSDesign.create(inputs, designSize);

		// evaluate process
		long time = System.nanoTime();
		ProcessEvaluationResult processResult = ProcessEvaluator.evaluate(serviceURL, processIdentifier, inputs, outputs, design);
		long processDuration = System.nanoTime() - time;
		logger.info("Took " + (double)processDuration / 1000000000.0 + "s to evaluate process.");

		// validate
		return Validator.usingPredictionsAndEmulator(design, processResult, emulator);
	}

	public static Validator usingPredictionsAndEmulator(Design design, ProcessEvaluationResult processResult, Emulator emulator) throws ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
		// evaluate emulator
		long time = System.nanoTime();
		EmulatorEvaluationResult emulatorResult = EmulatorEvaluator.run(emulator, design);
		long emulatorDuration = System.nanoTime() - time;
		logger.info("Took " + (double)emulatorDuration / 1000000000.0 + "s to evaluate emulator.");

		// fetch results
		// FIXME: emulators can only be trained for one output... should be more elegant here!
		String outputId = emulator.getOutputs().get(0).getIdentifier();
		Double[] processResults = processResult.getResults(outputId);
		Double[] meanResults = emulatorResult.getMeanResults(outputId);
		Double[] covarianceResults = emulatorResult.getCovarianceResults(outputId);

		// for test REMOVE!
		// NormalisedProcessEvaluationResult nper = NormalisedProcessEvaluationResult.fromProcessEvaluationResult(processResult, ((NormalisedProcessEvaluationResult)emulator.getEvaluationResult()).getMeans(), ((NormalisedProcessEvaluationResult)emulator.getEvaluationResult()).getStdDevs());

		//		for (int i = 0; i < design.getSize(); i++) {
		//			System.out.println(processResults[i] + ", " + meanResults[i] + " with " + covarianceResults[i] + " covariance");
		//			System.out.println(processResults[i] - meanResults[i] + " difference");
		//		}

		// build values for now
		ScalarValues simulated = ScalarValues.fromArray(ArrayUtils.toPrimitive(processResults));
		DistributionValues emulated = DistributionValues.fromArrays(ArrayUtils.toPrimitive(meanResults), ArrayUtils.toPrimitive(covarianceResults));

		// return
		return new Validator(simulated, emulated);
	}

	public ScalarValues getStandardScores() {
		double[] scores = new double[observed.size()];

		for (int i = 0; i < scores.length; i++) {
			// get observed and predicted
			Scalar o = observed.get(i);

			if (predicted instanceof DistributionValues) {
				Distribution p = ((DistributionValues)predicted).get(i);
				double diff = o.getScalar() - p.getMean();
				scores[i] = diff / p.getStandardDeviation();
			}
			else if (predicted instanceof ScalarValues) {
				ScalarValues values = (ScalarValues)predicted;
				double diff = o.getScalar() - values.get(i).getScalar();
				scores[i] = diff / values.getStandardDeviation();
			}
			else {
				// not sure yet! using sample members mean and variance
				Sample p = ((SampleValues)predicted).get(i);
				double diff = o.getScalar() - p.getMean();
				scores[i] = diff / p.getStandardDeviation();
			}
		}

		return ScalarValues.fromArray(scores);
	}
	
	public double getRMSE() {
		return rmse;
	}

	public PlotData getMeanResidualHistogram() {
		return meanResidualHistogram;
	}

	public PlotData getMedianResidualHistogram() {
		return medianResidualHistogram;
	}
	
	public PlotData getReliabilityDiagram() {
		return reliabilityDiagram;
	}

//	public double getRMSE() {
//		double[] o = observed.toArray();
//		double[] p = new double[predicted.size()];
//
//		// means, numeric, or ensembles?
//		if (predicted instanceof MeanVarianceValues) {
//			for (int i = 0; i < p.length; i++) {
//				p[i] = ((MeanVarianceValues)predicted).get(i).getMean();
//			}
//		}
//		else if (predicted instanceof NumericValues) {
//			p = ((NumericValues)predicted).toArray();
//		}
//		else {
//			// not sure yet! using mean of ensemble members
//			for (int i = 0; i < p.length; i++) {
//				p[i] = ((EnsembleValues)predicted).get(i).getMean();
//			}
//		}
//
//		// calculate
//		double total = 0d;
//		for (int i = 0; i < o.length; i++) {
//			total += Math.pow(o[i] - p[i], 2);
//		}
//		total = total / o.length;
//
//		return Math.sqrt(total);		
//	}



}
