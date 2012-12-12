package org.uncertweb.et.value;

public class Scalar implements Value {

	private double scalar;
	
	public Scalar(double scalar) {
		this.scalar = scalar;
	}
	
	public double getScalar() {
		return scalar;
	}
	
}
