package org.uncertweb.et.response;

import org.uncertweb.et.learning.LearningResult;

public class LearningResponse extends Response {

	private LearningResult result;
	
	public LearningResponse(LearningResult result) {
		this.result = result;
	}
	
	public LearningResult getResult() {
		return result;
	}
	
}
