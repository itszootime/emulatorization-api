package org.uncertweb.et.request;

import java.util.List;

import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;

public class ValidationRequest extends Request {
	
	private String serviceURL;
	private String processIdentifier;
	private List<Input> inputs;
	private List<Output> outputs;
	private Emulator emulator;
	private int designSize;
	
	public ValidationRequest() {
		
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public String getProcessIdentifier() {
		return processIdentifier;
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public List<Output> getOutputs() {
		return outputs;
	}

	public Emulator getEmulator() {
		return emulator;
	}
	
	public int getDesignSize() {
		return designSize;
	}

}
