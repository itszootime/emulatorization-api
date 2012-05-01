package org.uncertweb.et;

public class ConfigException extends Exception {
	
	// FIXME: this needs to be handled better
	
	private static final long serialVersionUID = 1L;

	public ConfigException() {
		super();
	}
	
	public ConfigException(String message) {
		super(message);
	}
}
