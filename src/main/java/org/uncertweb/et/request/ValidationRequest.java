package org.uncertweb.et.request;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;

public class ValidationRequest extends Request {

	// emulator + simulator url, input spec
	// emulator + simulator results
	// simulator results + simulator results

	private Emulator emulator;

	// can have this
	private String serviceURL;
	private String processIdentifier;
	private int designSize;

	// or these
	private Design design;
	private ProcessEvaluationResult evaluationResult;

	// or these
	private ScalarValues observed;
	private Values predicted;

	public ValidationRequest() {

	}

	public ScalarValues getObserved() {
		return observed;
	}

	public Values getPredicted() {
		return predicted;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public String getProcessIdentifier() {
		return processIdentifier;
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
