package org.uncertweb.et.parameter;

public abstract class Parameter {

	private String identifier;
	private ParameterDescription description;
	
	public Parameter(String identifier) {
		this.identifier = identifier;
	}
	
	public Parameter(String identifier, ParameterDescription description) {
		this(identifier);
		this.description = description;
	}

	public String getIdentifier() {
		return identifier;
	}

	public ParameterDescription getDescription() {
		return description;
	}
	
	public void setDescription(ParameterDescription description) {
		this.description = description;
	}
	
}
