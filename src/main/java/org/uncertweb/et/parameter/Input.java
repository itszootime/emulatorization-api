package org.uncertweb.et.parameter;

public class Input extends Parameter {
	
	public Input(String identifier) {
		super(identifier);
	}
	
	public Input(String identifier, ParameterDescription description) {
		super(identifier, description);
	}
	
	public boolean isVariableInput() {
		return (this instanceof VariableInput);
	}
	
	public boolean isConstantInput() {
		return (this instanceof ConstantInput);
	}
	
	public VariableInput getAsVariableInput() {
		return (VariableInput) this;
	}
	
	public ConstantInput getAsConstantInput() {
		return (ConstantInput) this;
	}
	
}
