package org.uncertweb.et.response;

import org.uncertweb.et.process.ProcessEvaluationResult;

public class EvaluateProcessResponse extends Response {
	
	private ProcessEvaluationResult evaluationResult;
	
	public EvaluateProcessResponse(ProcessEvaluationResult evaluationResult) {
		this.evaluationResult = evaluationResult;
	}
	
	public ProcessEvaluationResult getResult() {
		return evaluationResult;
	}

}
