package org.uncertweb.et.emulator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.Config;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;
import org.uncertweb.et.process.NormalisedProcessEvaluationResult;
import org.uncertweb.et.util.EmulatorUtil;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLString;

public class EmulatorEvaluator {

	private static final Logger logger = LoggerFactory.getLogger(EmulatorEvaluator.class);

	public static EmulatorEvaluationResult run(Emulator emulator, Design design) throws EmulatorEvaluatorException {
		// check if emulator has more than 1 output, unsupported
		if (emulator.getOutputs().size() > 1) {
			throw new EmulatorEvaluatorException("Emulators with more than one output are currently unsupported.");
		}

		// don't filter out fixed
		// an emulator shouldn't have any fixed inputs anyway??

		// do we need to normalise?
		try {
			Design runDesign = design;
			
			if (emulator.getDesign() instanceof NormalisedDesign) {
				// normalise
				NormalisedDesign norm = (NormalisedDesign)emulator.getDesign();
				runDesign = NormalisedDesign.fromDesign(design, norm.getMeans(), norm.getStdDevs());
			}

			// create matlab request
			MLRequest request = new MLRequest("run_emulator", 2);
			request.addParameter(new MLString((String)Config.getInstance().get("matlab", "gpml_path")));

			// add points to evaluate against		
			request.addParameter(new MLMatrix(runDesign.getPoints()));

			// training data
			Double[][] xtrn = emulator.getDesign().getPoints();
			Double[][] ytrn = emulator.getEvaluationResult().getResults();
			request.addParameter(new MLMatrix(xtrn));
			request.addParameter(new MLMatrix(ytrn));

			// setup covariance function
			EmulatorUtil.addCovarianceFunction(request, emulator.getDesign(), emulator.getCovarianceFunction(),
				emulator.getLengthScaleMultiplier(), emulator.getNuggetVariance());
			
			// setup mean function
			EmulatorUtil.addMeanFunction(request, emulator.getMeanFunction());

			// run emulator
			// run with matlab
			logger.info("Evaluating emulator over " + design.getSize() + " input points...");
			logger.debug("meanfname=" + request.getParameter(6).toString() + ", covfname=" + request.getParameter(4).toString());
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
			String outputIdentifier = emulator.getOutputs().get(0).getIdentifier();
			EmulatorEvaluationResult er = new EmulatorEvaluationResult();
			er.addResults(outputIdentifier, predictedMean, predictedCovariance);

			// un-normalise mean and std dev
			if (emulator.getEvaluationResult() instanceof NormalisedProcessEvaluationResult) {
				NormalisedProcessEvaluationResult nper = (NormalisedProcessEvaluationResult)emulator.getEvaluationResult();
				er = er.unnormalise(nper.getMean(outputIdentifier), nper.getStdDev(outputIdentifier));
			}

			// return result
			return er;
		}
		catch (MLException e) {
			throw new EmulatorEvaluatorException("Couldn't evaluate emulator. " + e.getMessage());
		}
		catch (IOException e) {
			throw new EmulatorEvaluatorException("Couldn't connect to MATLAB.");
		}
		catch (ConfigException e) {
			throw new EmulatorEvaluatorException("Couldn't load MATLAB config details.");
		}
	}

}
