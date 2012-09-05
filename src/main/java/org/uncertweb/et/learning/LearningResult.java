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
	private double[] lengthScales;
	private Double nuggetVariance;
	
	public LearningResult(double[] predictedMean, double[] predictedCovariance, Design design, ProcessEvaluationResult evaluationResult, double[] lengthScales, Double nuggetVariance) {
		this.predictedMean = predictedMean;
		this.predictedCovariance = predictedCovariance;
		this.design = design;
		this.evaluationResult = evaluationResult;
		this.lengthScales = lengthScales;
		this.nuggetVariance = nuggetVariance;
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

	public double[] getLengthScales() {
		return lengthScales;
	}

	public double getNuggetVariance() {
		return nuggetVariance;
	}
	
}
