package org.uncertweb.et.validation;

public class ValidatorException extends Exception {
	
	private static final long serialVersionUID = -8159964200846764863L;

	public ValidatorException() {
		super();
	}
	
	public ValidatorException(String message) {
		super(message);
	}
	
	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

