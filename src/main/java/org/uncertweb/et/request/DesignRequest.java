package org.uncertweb.et.request;

import java.util.List;

import org.uncertweb.et.parameter.Input;

public class DesignRequest extends Request {

	private List<Input> inputs;
	private int size;
	
	public DesignRequest() {
		
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public int getSize() {
		return size;
	}	
	
}
