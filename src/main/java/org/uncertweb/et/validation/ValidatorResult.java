package org.uncertweb.et.validation;

import java.util.List;

public class ValidatorResult {

	private List<ValidatorOutputResult> outputResults;
	private long emulatorTime;
	private long processTime;	
	
	public ValidatorResult(List<ValidatorOutputResult> outputResults, long processTime, long emulatorTime) {
		super();
		this.outputResults = outputResults;
		this.processTime = processTime;
		this.emulatorTime = emulatorTime;
	}
	
	public List<ValidatorOutputResult> getOutputResults() {
		return outputResults;
	}
	
	public long getEmulatorTime() {
		return emulatorTime;
	}
	
	public long getProcessTime() {
		return processTime;
	}	
	
}
