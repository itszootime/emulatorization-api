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

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

	private NumericValues observed;
	private Values predicted;

	public Validator(NumericValues observed, Values predicted) {
		this.observed = observed;
		this.predicted = predicted;
	}

	public NumericValues getObserved() {
		return observed;
	}

	public Values getPredicted() {
		return predicted;
	}

	public static Validator usingSimulatorAndEmulator(String serviceURL, String processIdentifier, Emulator emulator, int designSize) throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException {
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

	public static Validator usingPredictionsAndEmulator(Design design, ProcessEvaluationResult processResult, Emulator emulator) throws ProcessEvaluatorException, EmulatorEvaluatorException {
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

		// return
		return new Validator(simulated, emulated);
	}

	public double getRMSE() {
		double[] o = observed.toArray();
		double[] p = new double[predicted.size()];

		// means, numeric, or ensembles?
		if (predicted instanceof MeanVarianceValues) {
			for (int i = 0; i < p.length; i++) {
				p[i] = ((MeanVarianceValues)predicted).get(i).getMean();
			}
		}
		else if (predicted instanceof NumericValues) {
			p = ((NumericValues)predicted).toArray();
		}
		else {
			// not sure yet! just taking first member in ensemble here
			for (int i = 0; i < p.length; i++) {
				p[i] = ((EnsembleValues)predicted).get(i).getMembers()[0];
			}
		}

		// calculate
		double total = 0d;
		for (int i = 0; i < o.length; i++) {
			total += Math.pow(o[i] - p[i], 2);
		}
		total = total / o.length;

		return Math.sqrt(total);		
	}

	public NumericValues getStandardScores() {
		double[] scores = new double[observed.size()];

		for (int i = 0; i < scores.length; i++) {
			// get observed and predicted
			Numeric o = observed.get(i);
			MeanVariance p;
			
			if (predicted instanceof MeanVarianceValues) {
				p = ((MeanVarianceValues)predicted).get(i);
				double stdev = Math.sqrt(Math.abs(p.getVariance()));
				double diff = o.getNumber() - p.getMean();
				scores[i] = diff / stdev;
			}
			else if (predicted instanceof NumericValues) {
				NumericValues values = (NumericValues)predicted;
				double diff = o.getNumber() - values.get(i).getNumber();
				scores[i] = diff / values.getStandardDeviation();
			}
			else {
				// making this up
				scores[i] = 123;
			}
		}

		return NumericValues.fromArray(scores);
	}

	//	public static NumericValues calculateStandardScores(NumericValues observed, NumericValues simulated) {
	//		double[] scores = new double[observed.size()];
	//		double stdev = Math.sqrt(Validator.calculateVariance(simulated.toArray()));
	//		for (int i = 0; i < scores.length; i++) {	
	//			scores[i] = (observed.get(i).getNumber() - simulated.get(i).getNumber()) / stdev;
	//		}
	//		return NumericValues.fromArray(scores);
	//	}
	//	
	//	public static NumericValues calculateStandardScores(NumericValues observed, MeanVarianceValues predicted) {
	//		double[] scores = new double[observed.size()];
	//		for (int i = 0; i < scores.length; i++) {
	//			Numeric n = observed.get(i);
	//			MeanVariance mc = predicted.get(i);
	//			double stdev = Math.sqrt(Math.abs(mc.getVariance()));
	//			double diff = n.getNumber() - mc.getMean();
	//			scores[i] = diff / stdev;
	//		}
	//		return NumericValues.fromArray(scores);
	//	}
	//



}
