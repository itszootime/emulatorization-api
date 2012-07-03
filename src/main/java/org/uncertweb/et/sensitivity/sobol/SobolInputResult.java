package org.uncertweb.et.sensitivity.sobol;

import org.uncertweb.et.sensitivity.AnalysisInputResult;

public class SobolInputResult extends AnalysisInputResult {

	private double firstOriginal;
	private double firstBias;
	private double firstStdError;
	private double firstMinCI;
	private double firstMaxCI;	
	private double totalOriginal;
	private double totalBias;
	private double totalStdError;
	private double totalMinCI;
	private double totalMaxCI;
	
	public SobolInputResult(String inputIdentifier, double firstOriginal, double firstBias, double firstStdError, double firstMinCI,
		double firstMaxCI, double totalOriginal, double totalBias, double totalStdError, double totalMinCI, double totalMaxCI) {
		super(inputIdentifier);
		this.firstOriginal = firstOriginal;
		this.firstBias = firstBias;
		this.firstStdError = firstStdError;
		this.firstMinCI = firstMinCI;
		this.firstMaxCI = firstMaxCI;
		this.totalOriginal = totalOriginal;
		this.totalBias = totalBias;
		this.totalStdError = totalStdError;
		this.totalMinCI = totalMinCI;
		this.totalMaxCI = totalMaxCI;
	}
	
	public double getFirstOriginal() {
		return firstOriginal;
	}

	public double getFirstBias() {
		return firstBias;
	}

	public double getFirstStdError() {
		return firstStdError;
	}

	public double getFirstMinCI() {
		return firstMinCI;
	}

	public double getFirstMaxCI() {
		return firstMaxCI;
	}

	public double getTotalOriginal() {
		return totalOriginal;
	}

	public double getTotalBias() {
		return totalBias;
	}

	public double getTotalStdError() {
		return totalStdError;
	}

	public double getTotalMinCI() {
		return totalMinCI;
	}

	public double getTotalMaxCI() {
		return totalMaxCI;
	}
	
}
