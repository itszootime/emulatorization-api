package org.uncertweb.et.parameter;

public class Output {
	
	private String identifier;
	private boolean included;
	
	public Output(String identifier) {
		this.identifier = identifier;
		included = true;
	}
	
	public Output(String identifier, boolean included) {
		this.identifier = identifier;
		this.included = included;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public boolean isIncluded() {
		return included;
	}

}
