package org.uncertweb.et.parameter;

public class ConstantInput extends Input {

	private double value;
	
	public ConstantInput(String identifier, double value) {
		super(identifier);
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}

}
