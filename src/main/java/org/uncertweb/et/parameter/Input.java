package org.uncertweb.et.parameter;

// FIXME: bit hacky
public class Input {

	private String identifier;
	private boolean included;
	
	public Input(String identifier) {
		this.identifier = identifier;
		included = true;
	}
	
	public Input(String identifier, boolean included) {
		this.identifier = identifier;
		this.included = included;
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

	public String getIdentifier() {
		return identifier;
	}
	
	public boolean isIncluded() {
		return included;
	}
	
}
