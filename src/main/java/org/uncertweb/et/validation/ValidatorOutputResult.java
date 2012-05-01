package org.uncertweb.et.validation;


public class ValidatorOutputResult {

	private String outputIdentifier;
	private Double[] zScores;
	private double rmse;
	
	public ValidatorOutputResult(String outputIdentifier, Double[] zScores, double rmse) {
		this.outputIdentifier = outputIdentifier;
		this.zScores = zScores;
		this.rmse = rmse;
	}

	public String getOutputIdentifier() {
		return outputIdentifier;
	}

	public Double[] getZScores() {
		return zScores;
	}

	public double getRmse() {
		return rmse;
	}
	
}
