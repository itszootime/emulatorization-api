package org.uncertweb.et.request;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class LearningRequest extends Request {

	private Design design;
	private ProcessEvaluationResult evaluationResult;
	
	private String selectedOutputIdentifier;
	
	private int trainingSetSize;

	private String meanFunction;
	private String covarianceFunction;
	private double lengthScale;
	private double processVariance;
	private Double nuggetVariance;
	
	public LearningRequest() {
		
	}

	public Design getDesign() {
		return design;
	}

	public ProcessEvaluationResult getEvaluationResult() {
		return evaluationResult;
	}

	public String getSelectedOutputIdentifier() {
		return selectedOutputIdentifier;
	}

	public int getTrainingSetSize() {
		return trainingSetSize;
	}

	public String getMeanFunction() {
		return meanFunction;
	}

	public String getCovarianceFunction() {
		return covarianceFunction;
	}

	public double getLengthScale() {
		return lengthScale;
	}

	public double getProcessVariance() {
		return processVariance;
	}

	public Double getNuggetVariance() {
		return nuggetVariance;
	}
	
}
