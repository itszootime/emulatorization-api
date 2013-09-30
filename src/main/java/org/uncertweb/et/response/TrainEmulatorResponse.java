package org.uncertweb.et.response;

import org.uncertweb.et.training.TrainingResult;

public class TrainEmulatorResponse extends Response {

	private TrainingResult result;
	
	public TrainEmulatorResponse(TrainingResult result) {
		this.result = result;
	}
	
	public TrainingResult getResult() {
		return result;
	}
	
}
