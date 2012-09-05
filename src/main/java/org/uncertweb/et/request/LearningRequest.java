package org.uncertweb.et.request;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class LearningRequest extends Request {

	private Design design;
	private ProcessEvaluationResult evaluationResult;
	
	private String selectedOutputIdentifier;

	private String meanFunction;
	private String covarianceFunction;
	private double lengthScaleMultiplier;
	private Double nuggetVariance;
	
	private boolean normalisation;
	
	public LearningRequest() {
		
	}

	public boolean isNormalisation() {
		return normalisation;
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
	
	public String getMeanFunction() {
		return meanFunction;
	}

	public String getCovarianceFunction() {
		return covarianceFunction;
	}

	public double getLengthScaleMultiplier() {
		return lengthScaleMultiplier;
	}

	public Double getNuggetVariance() {
		return nuggetVariance;
	}
	
}
