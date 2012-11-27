package org.uncertweb.et.response;

import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.value.MeanVarianceValues;
import org.uncertweb.et.value.NumericValues;

public class ValidationResponse extends Response {
	
	private double rmse;
	private NumericValues standardScores;
	private NumericValues observed;
	private MeanVarianceValues predicted;
	
	public ValidationResponse(double rmse, NumericValues standardScores,
			NumericValues observed, MeanVarianceValues predicted) {
		super();
		this.rmse = rmse;
		this.standardScores = standardScores;
		this.observed = observed;
		this.predicted = predicted;
	}

	public double getRMSE() {
		return rmse;
	}

	public NumericValues getStandardScores() {
		return standardScores;
	}

	public NumericValues getObserved() {
		return observed;
	}

	public MeanVarianceValues getPredicted() {
		return predicted;
	}
	
	public static ValidationResponse fromValidator(Validator validator) {
		return new ValidationResponse(validator.getRMSE(), validator.getStandardScores(), validator.getObserved(), validator.getPredicted());
	}
	
}
