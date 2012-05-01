package org.uncertweb.et.request;

import java.util.List;

import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;

public class SensitivityRequest extends Request {
	
	private boolean plot;
	private int designSize;
	private int numBoot;
	private double confidenceLevel;
	
	// has one of these
	private Emulator emulator;
	
	// or these
	private String serviceURL;
	private String processIdentifier;
	private List<Input> inputs;
	private List<Output> outputs;
	
	public SensitivityRequest() {
		
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public double getConfidenceLevel() {
		return confidenceLevel;
	}

	public void setConfidenceLevel(double confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

	public List<Output> getOutputs() {
		return outputs;
	}

	public Emulator getEmulator() {
		return emulator;
	}

	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

	public void setOutputs(List<Output> outputs) {
		this.outputs = outputs;
	}

	public void setEmulator(Emulator emulator) {
		this.emulator = emulator;
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public String getProcessIdentifier() {
		return processIdentifier;
	}

	public boolean isPlot() {
		return plot;
	}

	public void setPlot(boolean plot) {
		this.plot = plot;
	}

	public int getDesignSize() {
		return designSize;
	}

	public int getNumBoot() {
		return numBoot;
	}
	
}
