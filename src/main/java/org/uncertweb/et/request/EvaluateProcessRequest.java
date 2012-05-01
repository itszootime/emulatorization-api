package org.uncertweb.et.request;

import java.util.List;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;

public class EvaluateProcessRequest extends Request {

	private String serviceURL;
	private String processIdentifier;
	private Design design;
	private List<Input> inputs;	
	private List<Output> outputs;	
	
	public EvaluateProcessRequest() {
		
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public String getProcessIdentifier() {
		return processIdentifier;
	}
	
	public Design getDesign() {
		return design;
	}
	
	public List<Input> getInputs() {
		return inputs;
	}
	
	public List<Output> getOutputs() {
		return outputs;
	}
	
}
