package org.uncertweb.et.validation;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
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
import org.uncertweb.et.value.EnsembleValues;
import org.uncertweb.et.value.MeanVariance;
import org.uncertweb.et.value.MeanVarianceValues;
import org.uncertweb.et.value.Numeric;
import org.uncertweb.et.value.NumericValues;
import org.uncertweb.et.value.Values;

public class Validator {

	// make something simple
	// json inputs:
	//   String serviceURL;
	//   String processIdentifier;
	//   List<Input> inputs;	  } for creating design, need min/max
	//   List<Output> outputs;	  }
	//   Emulator emulator;
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(Validator.class);
	
	public static ValidatorResult validate(NumericValues observed, EnsembleValues simulated) {
		// calculate
//		double[] standardScores = Validator.calculateStandardScores(observed, simulated);
//		Double rmse = Validator.calculateRMSE(measurements.getResults(outputIdentifier), processResults.getResults(outputIdentifier));
//			
//			// add to result
//			ValidatorOutputResult outputResult = new ValidatorOutputResult(outputIdentifier, zScores, null, null, null, rmse);
//			outputResults.add(outputResult);
//		}
//		
//		// construct result
//		ValidatorResult result = new ValidatorResult(Arrays.asList(new ValidationOutputResult[] { result }));
//		return result;
		return null;
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
		NumericValues simulated = NumericValues.fromArray(ArrayUtils.toPrimitive(processResults));
		MeanVarianceValues emulated = MeanVarianceValues.fromArrays(ArrayUtils.toPrimitive(meanResults), ArrayUtils.toPrimitive(covarianceResults));
		
		// calculate
		logger.info("Calculating standard scores and RMSE...");
		Values<Numeric> standardScores = Validator.calculateStandardScores(simulated, emulated);
		double rmse = Validator.calculateRMSE(simulated, emulated);
		
		// construct result		
		ValidatorResult result = new ValidatorResult(standardScores, rmse);
		return result;
	}
	
	public static NumericValues calculateStandardScores(NumericValues observed, NumericValues simulated) {
		double[] scores = new double[observed.size()];
		double stdev = Math.sqrt(Validator.calculateVariance(simulated.toArray()));
		for (int i = 0; i < scores.length; i++) {	
			scores[i] = (observed.get(i).getNumber() - simulated.get(i).getNumber()) / stdev;
		}
		return NumericValues.fromArray(scores);
	}
	
	public static NumericValues calculateStandardScores(NumericValues observed, MeanVarianceValues emulated) {
		double[] scores = new double[observed.size()];
		for (int i = 0; i < scores.length; i++) {
			Numeric n = observed.get(i);
			MeanVariance mc = emulated.get(i);
			double stdev = Math.sqrt(Math.abs(mc.getVariance()));
			double diff = n.getNumber() - mc.getMean();
			scores[i] = diff / stdev;
		}
		return NumericValues.fromArray(scores);
	}

	private static double calculateMean(double[] values) {
		double total = 0d;
		for (double value : values) {
			total += value;
		}
		return total / values.length;
	}

	private static double calculateVariance(double[] values) {
		double mean = calculateMean(values);
		double var = 0d;
		for (double value : values) {
			var += Math.pow(value - mean, 2);
		}
		return var / values.length;
	}
	
	private static double calculateRMSE(NumericValues observed, MeanVarianceValues emulated) {
		NumericValues means = new NumericValues();
		for (MeanVariance value : emulated) {
			means.add(value.getMean());
		}
		return calculateRMSE(observed, means);
	}
	
	private static double calculateRMSE(NumericValues observed, NumericValues simulated) {
		double sum = 0d;
		for (int i = 0; i < observed.size(); i++) {
			sum += Math.pow(observed.get(i).getNumber() - simulated.get(i).getNumber(), 2);
		}
		return Math.sqrt(sum);
	}

}
