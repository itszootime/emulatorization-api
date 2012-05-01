package org.uncertweb.et.response;

import org.uncertweb.et.validation.ValidatorResult;

public class ValidationResponse extends Response {
	
	private ValidatorResult result;

	public ValidationResponse(ValidatorResult result) {
		this.result = result;
	}
	
	public ValidatorResult getResult() {
		return result;
	}
	
}
