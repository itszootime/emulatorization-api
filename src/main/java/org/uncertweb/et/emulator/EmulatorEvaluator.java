package org.uncertweb.et.emulator;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.Config;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLArray;
import org.uncertweb.matlab.value.MLCell;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLString;
import org.uncertweb.matlab.value.MLValue;

public class EmulatorEvaluator {

	private static final Logger logger = LoggerFactory.getLogger(EmulatorEvaluator.class);

	public static EmulatorEvaluationResult run(Emulator emulator, Design design) throws EmulatorEvaluatorException {
		// check if emulator has more than 1 output, unsupported
		if (emulator.getOutputs().size() > 1) {
			throw new EmulatorEvaluatorException("Emulators with more than one output are currently unsupported.");
		}

		// filter out fixed
		Design variableDesign = new Design(design.getSize());
		for (Input input : emulator.getInputs()) {
			if (input instanceof VariableInput) {
				String identifier = input.getIdentifier();
				variableDesign.addPoints(identifier, design.getPoints(identifier));
			}
		}

		// do we need to normalise?
		try {
			if (emulator.getDesignMean() != null) {
				// normalise
				variableDesign = NormalisedDesign.fromDesign(variableDesign, emulator.getDesignMean(), emulator.getDesignStdDev());
			}

			// create matlab request
			MLRequest request = new MLRequest("run_emulator", 2);
			request.addParameter(new MLString((String)Config.getInstance().get("matlab", "gpml_path")));

			// add points to evaluate against		
			request.addParameter(new MLMatrix(variableDesign.getPoints()));

			// training data
			Double[][] xtrn = emulator.getDesign().getPoints();
			Double[][] ytrn = emulator.getEvaluationResult().getResults();
			request.addParameter(new MLMatrix(xtrn));
			request.addParameter(new MLMatrix(ytrn));

			// setup covariance function
			MLValue covfname;
			double[] covfpar;
			if (emulator.getCovarianceFunction().equals("squared_exponential")) {
				covfname = new MLString("covSEiso");
			}
			else {
				covfname = new MLString("covMatern3iso");
			}
			if (emulator.getNuggetVariance() != null) {
				covfname = new MLCell(new MLValue[] {
					new MLString("covSum"),
					new MLCell(new MLValue[] { covfname, new MLString("covNoise") }) });
				covfpar = new double[] { emulator.getLengthScale(), Math.sqrt(emulator.getProcessVariance()), emulator.getNuggetVariance() };
			}
			else {
				covfpar = new double[] { emulator.getLengthScale(), Math.sqrt(emulator.getProcessVariance()) };
			}
			request.addParameter(covfname);		
			request.addParameter(new MLArray(covfpar));

			// setup mean function
			String meanfname;
			double[] meanfpar;
			if (emulator.getMeanFunction().equals("zero")) {
				meanfname = "";
				meanfpar = new double[] {};
			}
			else {
				meanfname = "mean_poly";
				meanfpar = new double[1];
				if (emulator.getMeanFunction().equals("constant")) {
					meanfpar[0] = 0;
				}
				else if (emulator.getMeanFunction().equals("linear")) {
					meanfpar[0] = 1;
				}
				else {
					meanfpar[0] = 2;
				}
			}
			request.addParameter(new MLString(meanfname));
			request.addParameter(new MLArray(meanfpar));

			// run emulator
			// run with matlab
			logger.info("Evaluating emulator over " + design.getSize() + " input points...");
			logger.debug("meanfname=" + meanfname + ", meanfpar=" + Arrays.toString(meanfpar) + ", covfname=" + covfname + ", covfpar=" + Arrays.toString(covfpar));
			MLResult result = MATLAB.sendRequest(request);

			// read output
			double[] predictedMean;
			double[] predictedCovariance;
			if (design.getSize() > 1) {
				predictedMean = result.getResult(0).getAsArray().getArray();
				predictedCovariance = result.getResult(1).getAsArray().getArray();
			}
			else {
				predictedMean = new double[] { result.getResult(0).getAsScalar().getScalar() };
				predictedCovariance = new double[] { result.getResult(1).getAsScalar().getScalar() };
			}
			
			// create result
			EmulatorEvaluationResult er = new EmulatorEvaluationResult();
			er.addResults(emulator.getOutputs().get(0).getIdentifier(), predictedMean, predictedCovariance);

			// un-normalise mean and std dev
			if (emulator.getDesignMean() != null) {
				er = er.unnormalise(emulator.getEvaluationResultMean(), emulator.getEvaluationResultStdDev());
			}

			// return result
			return er;
		}
		catch (MLException e) {
			throw new EmulatorEvaluatorException("Couldn't run emulator. " + e.getMessage());
		}
		catch (IOException e) {
			throw new EmulatorEvaluatorException("Couldn't connect to MATLAB.");
		}
		catch (ConfigException e) {
			throw new EmulatorEvaluatorException("Couldn't load MATLAB config details.");
		}
	}

}
