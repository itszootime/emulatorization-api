package org.uncertweb.et.value;

public class MeanCovariance extends Value {

	private double mean;
	private double covariance;
	
	public MeanCovariance(double mean, double covariance) {
		this.mean = mean;
		this.covariance = covariance;
	}
	
	public double getMean() {
		return mean;
	}
	
	public double getCovariance() {
		return covariance;
	}
	
}
