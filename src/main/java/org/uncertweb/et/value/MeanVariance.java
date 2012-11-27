package org.uncertweb.et.value;

public class MeanVariance implements Value {

	private double mean;
	private double variance;
	
	public MeanVariance(double mean, double variance) {
		this.mean = mean;
		this.variance = variance;
	}
	
	public double getMean() {
		return mean;
	}
	
	public double getVariance() {
		return variance;
	}
	
}
