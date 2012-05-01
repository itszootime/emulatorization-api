package org.uncertweb.et.parameter;

public class VariableInput extends Input {

	private double min;
	private double max;
	
	public VariableInput(String identifier, double min, double max) {
		super(identifier);
		this.min = min;
		this.max = max;
	}
	
	public double getMin() {
		return min;
	}
	
	public double getMax() {
		return max;
	}

}
