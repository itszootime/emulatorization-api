package org.uncertweb.et.value;


public class Sample implements Value {

	private double[] members;
	
	public Sample(double[] members) {
		this.members = members;
	}
	
	public double[] getMembers() {
		return members;
	}
	
	public double getStandardDeviation() {
		return Math.sqrt(getVariance());
	}
	
	public double getVariance() {
		double mean = getMean();
		double var = 0.0;
		for (double v : members) {
			var += Math.pow(v - mean, 2);
		}
		return var / (members.length - 1);
	}
	
	public double getMean() {
		double sum = 0.0;
		for (double v : members) {
			sum += v;
		}
		return sum / members.length;
	}
	
}
