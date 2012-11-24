package org.uncertweb.et.value;

public class Numeric extends Value {

	private double number;
	
	public Numeric(double number) {
		this.number = number;
	}
	
	public double getNumber() {
		return number;
	}
	
}
