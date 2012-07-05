package org.uncertweb.et.validation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.DesignException;
import org.uncertweb.et.design.LHSDesign;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.emulator.EmulatorEvaluator;
import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluator;
import org.uncertweb.et.process.ProcessEvaluatorException;

public class Validator {

	// make something simple
	// json inputs:
	//   String serviceURL;
	//   String processIdentifier;
	//   List<Input> inputs;	  } for creating design, need min/max
	//   List<Output> outputs;	  }
	//   Emulator emulator;
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

//	public static List<ValidatorOutputResult> validate(Double[] observed, Double[] predicted) {
//		return calculateZScores(observed, predicted);
//	}
	
	// observed (real measurement)
	// vs samples (from model)
	
	public static ValidatorResult validate(ProcessEvaluationResult measurements, ProcessEvaluationResult processResults) {
		// for each output
		List<ValidatorOutputResult> outputResults = new ArrayList<ValidatorOutputResult>();
		for (String outputIdentifier : measurements.getOutputIdentifiers()) {
			// calculate z scores and rmse
			Double[] zScores = Validator.calculateZScores(measurements.getResults(outputIdentifier), processResults.getResults(outputIdentifier));
			Double rmse = Validator.calculateRMSE(measurements.getResults(outputIdentifier), processResults.getResults(outputIdentifier));
			
			// add to result
			ValidatorOutputResult outputResult = new ValidatorOutputResult(outputIdentifier, zScores, null, null, null, rmse);
			outputResults.add(outputResult);
		}
		
		// construct result
		ValidatorResult result = new ValidatorResult(outputResults, 0);
		return result;
	}

	public static ValidatorResult validate(String serviceURL, String processIdentifier, List<Input> inputs, List<Output> outputs, Emulator emulator, int designSize) throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException {
		// create design
		Design design = LHSDesign.create(inputs, designSize);
		
		// evaluate process
		long time = System.nanoTime();
		ProcessEvaluationResult processResult = ProcessEvaluator.evaluate(serviceURL, processIdentifier, inputs, outputs, design);
		long processDuration = System.nanoTime() - time;
		logger.info("Took " + (double)processDuration / 1000000000.0 + "s to evaluate process.");
		
		// validate
		return validate(emulator, design, processResult);
	}

	public static ValidatorResult validate(Emulator emulator, Design design, ProcessEvaluationResult processResult) throws ProcessEvaluatorException, EmulatorEvaluatorException {
		// evaluate emulator
		long time = System.nanoTime();
		EmulatorEvaluationResult emulatorResult = EmulatorEvaluator.run(emulator, design);
		long emulatorDuration = System.nanoTime() - time;
		logger.info("Took " + (double)emulatorDuration / 1000000000.0 + "s to evaluate emulator.");

		// fetch results
		// FIXME: emulators can only be trained for one output... should be more elegant here!
		String outputId = emulator.getOutputDescriptions()[0].getIdentifier();
		Double[] processResults = processResult.getResults(outputId);
		Double[] meanResults = emulatorResult.getMeanResults(outputId);
		Double[] covarianceResults = emulatorResult.getCovarianceResults(outputId);
		
//		for (int i = 0; i < design.getSize(); i++) {
//			System.out.println(processResults[i] + ", " + meanResults[i] + " with " + covarianceResults[i] + " covariance");
//			System.out.println(processResults[i] - meanResults[i] + " difference");
//		}
		
		logger.info("Calculating Z-scores and RMSE...");
		Double[] zScores = Validator.calculateZScores(processResults, meanResults, covarianceResults);
		Double rmse = Validator.calculateRMSE(processResults, meanResults);
		
//		System.out.println(Arrays.toString(processResults));
//		System.out.println(Arrays.toString(meanResults));
//		System.out.println(Arrays.toString(covarianceResults));		
//		System.out.println(Arrays.toString(scores));
//		System.out.println(rmse);
		
		// construct result		
		List<ValidatorOutputResult> outputResults = new ArrayList<ValidatorOutputResult>();
		ValidatorOutputResult outputResult = new ValidatorOutputResult(outputId, zScores, processResults, meanResults, covarianceResults, rmse);
		outputResults.add(outputResult);
		ValidatorResult result = new ValidatorResult(outputResults, emulatorDuration);
		
		return result;
	}

	/**
	 * Two cases for validation:
	 * 
	 *   observed (real measured) vs. samples (from model)
	 *   observed (from model)    vs. mean/covariance (from emulator)
	 */
	public static Double[] calculateZScores(Double[] observed, Double[] predicted) {
		Double[] scores = new Double[observed.length];
		Double stdev = Math.sqrt(Validator.calculateVariance(predicted));
		for (int i = 0; i < observed.length; i++) {
			// for each result			
			scores[i] = (observed[i] - predicted[i]) / stdev;
		}
		return scores;
	}
	
	public static Double[] calculateZScores(Double[] observed, Double[] predictedMean, Double[] predictedCovariance) {
		Double[] scores = new Double[observed.length];
		for (int i = 0; i < observed.length; i++) {
			// for each result
			double stdev = Math.sqrt(Math.abs(predictedCovariance[i]));
			double diff = observed[i] - predictedMean[i];
			scores[i] = diff / stdev;
		}
		return scores;
	}

	private static Double calculateMean(Double[] values) {
		Double total = 0d;
		for (Double value : values) {
			total += value;
		}
		return total / values.length;
	}

	private static Double calculateVariance(Double[] values) {
		Double mean = calculateMean(values);
		Double var = 0d;
		for (Double value : values) {
			var += Math.pow(value - mean, 2);
		}
		return var / values.length;
	}
	
	private static Double calculateRMSE(Double[] observed, Double[] predicted) {
		Double sum = 0d;
		for (int i = 0; i < observed.length; i++) {
			sum += Math.pow(observed[i] - predicted[i], 2);
		}
		return Math.sqrt(sum);
	}

}
