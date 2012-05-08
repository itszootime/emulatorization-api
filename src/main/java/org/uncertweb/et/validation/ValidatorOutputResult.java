package org.uncertweb.et.validation;


public class ValidatorOutputResult {

	private String outputIdentifier;
	private Double[] zScores;
	private Double[] simulator;
	private Double[] emulatorMean;
	private Double[] emulatorVariance;
	private double rmse;
	
	public ValidatorOutputResult(String outputIdentifier, Double[] zScores, Double[] simulator,
		Double[] emulatorMean, Double[] emulatorVariance, double rmse) {
		this.outputIdentifier = outputIdentifier;
		this.zScores = zScores;
		this.simulator = simulator;
		this.emulatorMean = emulatorMean;
		this.emulatorVariance = emulatorVariance;
		this.rmse = rmse;
	}

	public String getOutputIdentifier() {
		return outputIdentifier;
	}

	public Double[] getZScores() {
		return zScores;
	}
	
	public Double[] getSimulator() {
		return simulator;
	}

	public Double[] getEmulatorMean() {
		return emulatorMean;
	}

	public Double[] getEmulatorVariance() {
		return emulatorVariance;
	}

	public double getRmse() {
		return rmse;
	}
	
}
