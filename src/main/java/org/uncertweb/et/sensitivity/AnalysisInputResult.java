package org.uncertweb.et.sensitivity;

public abstract class AnalysisInputResult {
	
	private String inputIdentifier;
	
	public AnalysisInputResult(String inputIdentifier) {
		this.inputIdentifier = inputIdentifier;
	}

	public String getInputIdentifier() {
		return inputIdentifier;
	}

}
