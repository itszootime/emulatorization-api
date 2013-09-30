package org.uncertweb.et.training;

import java.io.IOException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.Config;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;
import org.uncertweb.et.process.NormalisedProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.util.EmulatorUtil;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLString;
import org.uncertweb.matlab.value.MLValue;

public class Training {

	private static final Logger logger = LoggerFactory.getLogger(Training.class);

	public static TrainingResult learn(Design design, ProcessEvaluationResult evaluationResult, String selectedOutputIdentifier, String covarianceFunction, double lengthScaleMultiplier, Double nuggetVariance, String meanFunction, boolean normalisation) throws TrainingException {
		// setup x
		Design x = design;

		// setup y
		ProcessEvaluationResult y = evaluationResult;

		// normalise?
		if (normalisation) {
			logger.info("Normalising inputs and outputs...");
			x = NormalisedDesign.fromDesign(x);
			y = NormalisedProcessEvaluationResult.fromProcessEvaluationResult(y);
		}

		// setup request
		MLRequest request = new MLRequest("learn_emulator", 3);
		request.addParameter(new MLString((String)Config.getInstance().get("matlab", "gpml_path")));

		// add x
		request.addParameter(new MLMatrix(x.getPoints()));

		// add y
		// select output and transpose
		Double[] results = y.getResults(selectedOutputIdentifier);
		double[][] yt = new double[results.length][];
		for (int i = 0; i < results.length; i++) {
			yt[i] = new double[] { results[i] };
		}
		request.addParameter(new MLMatrix(yt));

		// add covariance function params
		EmulatorUtil.addCovarianceFunction(request, x, covarianceFunction, lengthScaleMultiplier, nuggetVariance);

		// setup mean function
		EmulatorUtil.addMeanFunction(request, meanFunction);

		// call matlab to: create training set, setup gp, predict, optimise
		logger.info("Learning emulator...");
		for (String identifier : design.getInputIdentifiers()) {
			DescriptiveStatistics stats = new DescriptiveStatistics(ArrayUtils.toPrimitive(x.getPoints(identifier)));
			logger.debug("identifier='" + identifier + "', min=" + stats.getMin() + ", max=" + stats.getMax());
		}
		logger.debug("meanfname=" + request.getParameter(5).toString() + ", covfname=" + request.getParameter(3).toString());

		// send
		try {
			MLResult result = MATLAB.sendRequest(request);

			double[] predictedMean = result.getResult(0).getAsArray().getArray();
			double[] predictedCovariance = result.getResult(1).getAsArray().getArray();
			
			MLValue optCovParams = result.getResult(2);
			double[] optLengthScales = new double[x.getInputIdentifiers().size()];
			Double optNuggetVariance = null;
			if (optCovParams.isScalar()) {
				optLengthScales[0] = optCovParams.getAsScalar().getScalar();
			}
			else {
				double[] optCovParamsArr = optCovParams.getAsArray().getArray();
				for (int i = 0; i < optLengthScales.length; i++) {
					optLengthScales[i] = optCovParamsArr[i];
				}
				if (nuggetVariance != null) {
					optNuggetVariance = Math.pow(Math.exp(optCovParamsArr[optCovParamsArr.length - 1]), 2);
				}
			}			

			return new TrainingResult(predictedMean, predictedCovariance, x, y, optLengthScales, optNuggetVariance);
		}
		catch (MLException e) {
			throw new TrainingException("Couldn't perform learning: " + e.getMessage());
		}
		catch (IOException e) {
			throw new TrainingException("Couldn't connect to MATLAB.");
		}
		catch (ConfigException e) {
			throw new TrainingException("Couldn't load MATLAB config details.");
		}
	}

}
