package org.uncertweb.et.validation;

import org.uncertweb.et.value.Numeric;
import org.uncertweb.et.value.Values;

public class ValidatorResult {

	private Values<Numeric> standardScores;
	private double rmse;
	
	private Values<?> observed;
	private Values<?> simulated;
	
	public ValidatorResult(Values<Numeric> standardScores, double rmse) {
		this.standardScores = standardScores;
		this.rmse = rmse;
	}
	
	public Values<?> getObserved() {
		return observed;
	}
	
	public Values<?> getSimulated() {
		return simulated;
	}
	
	public Values<Numeric> getStandardScores() {
		return standardScores;
	}
	
	public double getRMSE() {
		return rmse;
	}
	
}
