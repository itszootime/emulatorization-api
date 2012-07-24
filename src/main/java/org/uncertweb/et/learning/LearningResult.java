package org.uncertweb.et.learning;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class LearningResult {
	
	// will want to know parameters used here too!

	// predicted mean
	private double[] predictedMean;
	
	// predicted covariance
	private double[] predictedCovariance;
	
	// input and output training set (useful if normalised)
	private Design design;
	private ProcessEvaluationResult evaluationResult;
	
	// optimised
	private double lengthScale;
	private double processVariance;
	
	public LearningResult(double[] predictedMean, double[] predictedCovariance, Design design, ProcessEvaluationResult evaluationResult, double lengthScale, double processVariance) {
		this.predictedMean = predictedMean;
		this.predictedCovariance = predictedCovariance;
		this.design = design;
		this.evaluationResult = evaluationResult;
		this.lengthScale = lengthScale;
		this.processVariance = processVariance;
	}

	public double[] getPredictedMean() {
		return predictedMean;
	}

	public double[] getPredictedCovariance() {
		return predictedCovariance;
	}

	public Design getDesign() {
		return design;
	}

	public ProcessEvaluationResult getEvaluationResult() {
		return evaluationResult;
	}

	public double getLengthScale() {
		return lengthScale;
	}

	public double getProcessVariance() {
		return processVariance;
	}
	
}
