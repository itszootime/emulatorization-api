package org.uncertweb.et.emulator;

import java.util.List;

import org.uncertweb.et.description.ParameterDescription;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class Emulator {
	
	// input and output descriptions
	private List<Input> inputs;
	private List<Output> outputs;
	private ParameterDescription[] inputDescriptions;
	private ParameterDescription[] outputDescriptions;
	// ^ because i'm lazy and don't want to make a gson deserializer for parameterized list/collection
	
	// input and output training sets
	private Design trainingDesign;
	private ProcessEvaluationResult trainingEvaluationResult;
	
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
		
	public ParameterDescription[] getInputDescriptions() {
		return inputDescriptions;
	}
	public ParameterDescription[] getOutputDescriptions() {
		return outputDescriptions;
	}
	public Design getTrainingDesign() {
		return trainingDesign;
	}
	public ProcessEvaluationResult getTrainingEvaluationResult() {
		return trainingEvaluationResult;
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
