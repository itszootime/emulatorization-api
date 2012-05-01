package org.uncertweb.et.process;

import java.util.List;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;

public abstract class AbstractProcessEvaluator {

	protected String serviceURL;
	protected String processIdentifier;
	protected List<Input> inputs;
	protected List<Output> outputs;
	protected Design design;

	public AbstractProcessEvaluator(String serviceURL, String processIdentifier, List<Input> inputs, List<Output> outputs, Design design) {
		this.serviceURL = serviceURL;
		this.processIdentifier = processIdentifier;
		this.inputs = inputs;
		this.outputs = outputs;
		this.design = design;
	}
	
	public abstract ProcessEvaluationResult evaluate() throws ProcessEvaluatorException;
	
}
