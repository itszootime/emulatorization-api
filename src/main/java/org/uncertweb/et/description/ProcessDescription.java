package org.uncertweb.et.description;

import java.util.ArrayList;
import java.util.List;

import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;

public class ProcessDescription {

	private String identifier;
	private String detail;
	private List<Input> inputs;
	private List<Output> outputs;
	
	public ProcessDescription(String identifier) {
		this.identifier = identifier;
		this.inputs = new ArrayList<Input>();
		this.outputs = new ArrayList<Output>();
	}
	
	public ProcessDescription(String identifier, String detail) {
		this(identifier);
		this.detail = detail;
	}	
	
	public void addInput(Input input) {
		inputs.add(input);
	}
	
	public void addOutput(Output output) {
		outputs.add(output);
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getDetail() {
		return detail;
	}
	
}
