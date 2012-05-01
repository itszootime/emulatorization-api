package org.uncertweb.et.response;

import org.uncertweb.et.emulator.EmulatorEvaluationResult;

public class EvaluateEmulatorResponse extends Response {

	private EmulatorEvaluationResult emulatorResult;
	
	public EvaluateEmulatorResponse(EmulatorEvaluationResult emulatorResult) {
		this.emulatorResult = emulatorResult;
	}
	
	public EmulatorEvaluationResult getResult() {
		return emulatorResult;
	}
	
}
