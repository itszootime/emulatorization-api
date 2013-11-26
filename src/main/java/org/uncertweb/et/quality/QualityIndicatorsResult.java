package org.uncertweb.et.quality;

public class QualityIndicatorsResult {

	private double mean;
	private double variance;

	public QualityIndicatorsResult(double mean, double variance) {
		this.mean = mean;
		this.variance = variance;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getMean() {
		return mean;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public double getVariance() {
		return variance;
	}

}
