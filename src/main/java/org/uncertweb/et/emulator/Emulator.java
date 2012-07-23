package org.uncertweb.et.emulator;

import java.util.List;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class Emulator {
	
	// input and output descriptions
	private List<Input> inputs;
	private List<Output> outputs;
	
	// input and output training sets
	private Design design;
	private ProcessEvaluationResult evaluationResult;
	
	// function names and parameters
	private String meanFunction; // "zero", "constant", "linear", "quadratic"
	private String covarianceFunction; // "squared_exponential", "matern" -> all isotropic for now
	private double lengthScale;
	private double processVariance;
	private Double nuggetVariance;
	
	// normalisation
	private double[] designMean;
	private double[] designStdDev;
	private double evaluationResultMean;
	private double evaluationResultStdDev;
	
	public List<Input> getInputs() {
		return inputs;
	}
	public List<Output> getOutputs() {
		return outputs;
	}
	
	public Design getDesign() {
		return design;
	}
	
	public ProcessEvaluationResult getEvaluationResult() {
		return evaluationResult;
	}
	
	public String getMeanFunction() {
		return meanFunction;
	}
	
	public String getCovarianceFunction() {
		return covarianceFunction;
	}
	
	public double getLengthScale() {
		return lengthScale;
	}
	
	public double getProcessVariance() {
		return processVariance;
	}
	public Double getNuggetVariance() {
		return nuggetVariance;
	}
	
	public double[] getDesignMean() {
		return designMean;
	}
	
	public double[] getDesignStdDev() {
		return designStdDev;
	}
	
	public double getEvaluationResultMean() {
		return evaluationResultMean;
	}
	
	public double getEvaluationResultStdDev() {
		return evaluationResultStdDev;
	}	
	
}
