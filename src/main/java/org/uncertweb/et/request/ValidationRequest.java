package org.uncertweb.et.request;

import java.util.List;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class ValidationRequest extends Request {
	
	private Emulator emulator;
	
	// can have this
	private String serviceURL;
	private String processIdentifier;
	private List<Input> inputs;
	private List<Output> outputs;
	private int designSize;
	
	// or these
	private Design design;
	private ProcessEvaluationResult evaluationResult;
	
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
	
	public Design getDesign() {
		return design;
	}
	
	public int getDesignSize() {
		return designSize;
	}

	public ProcessEvaluationResult getEvaluationResult() {
		return evaluationResult;
	}

}
