package org.uncertweb.et.learning;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class LearningResult {
	
	// will want to know parameters used here too!

	// predicted mean
	private double[] predictedMean;
	
	// predicted covariance
	private double[] predictedCovariance;
	
	// input training set
	private Design trainingDesign;
	
	// output training set
	private ProcessEvaluationResult trainingEvaluationResult;
	
	// optimised
	private double lengthScale;
	private double processVariance;
	
	public LearningResult(double[] predictedMean, double[] predictedCovariance, Design trainingDesign, ProcessEvaluationResult trainingEvaluationResult, double lengthScale, double processVariance) {
		this.predictedMean = predictedMean;
		this.predictedCovariance = predictedCovariance;
		this.trainingDesign = trainingDesign;
		this.trainingEvaluationResult = trainingEvaluationResult;
		this.lengthScale = lengthScale;
		this.processVariance = processVariance;
	}

	public double[] getPredictedMean() {
		return predictedMean;
	}

	public double[] getPredictedCovariance() {
		return predictedCovariance;
	}

	public Design getTrainingDesign() {
		return trainingDesign;
	}

	public ProcessEvaluationResult getTrainingEvaluationResult() {
		return trainingEvaluationResult;
	}

	public double getLengthScale() {
		return lengthScale;
	}

	public double getProcessVariance() {
		return processVariance;
	}
	
}
