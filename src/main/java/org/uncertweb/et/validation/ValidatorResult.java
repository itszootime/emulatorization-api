package org.uncertweb.et.validation;

import java.util.List;

public class ValidatorResult {

	private List<ValidatorOutputResult> outputResults;
	private long emulatorTime;
	
	public ValidatorResult(List<ValidatorOutputResult> outputResults, long emulatorTime) {
		super();
		this.outputResults = outputResults;
		this.emulatorTime = emulatorTime;
	}
	
	public List<ValidatorOutputResult> getOutputResults() {
		return outputResults;
	}
	
	public long getEmulatorTime() {
		return emulatorTime;
	}
	
}
