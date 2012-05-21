package org.uncertweb.et.learning;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.Config;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLArray;
import org.uncertweb.matlab.value.MLCell;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLScalar;
import org.uncertweb.matlab.value.MLString;
import org.uncertweb.matlab.value.MLValue;

public class Learning {

	private static final Logger logger = LoggerFactory.getLogger(Learning.class);

	public static LearningResult learn(Design design, ProcessEvaluationResult evaluationResult, String selectedOutputIdentifier, int trainingSetSize, String covarianceFunction, double lengthScale, double processVariance, Double nuggetVariance, String meanFunction, boolean normalisation) throws LearningException {
		try {
			// setup x and y
			MLMatrix x = new MLMatrix(design.getPoints());
			Double[] results = evaluationResult.getResults(selectedOutputIdentifier);
			double[][] yMatrix = new double[results.length][];
			for (int i = 0; i < results.length; i++) {
				yMatrix[i] = new double[] { results[i] };
			}
			MLMatrix y = new MLMatrix(yMatrix);

			// from normalisation
			double[] designMean = null;
			double[] designStdDev = null;
			Double evaluationResultMean = null;
			Double evaluationResultStdDev = null;

			// normalise?
			if (normalisation) {				
				// build request
				MLRequest nrequest = new MLRequest("normalise", 3);
				nrequest.addParameter(x);

				// send
				logger.info("Normalising inputs...");
				MLResult nresult = MATLAB.sendRequest(nrequest);
				x = nresult.getResult(0).getAsMatrix();
				if (design.getInputIdentifiers().size() > 1) {
					designMean = nresult.getResult(1).getAsArray().getArray();
					designStdDev = nresult.getResult(2).getAsArray().getArray();
				}
				else {
					designMean = new double[] { nresult.getResult(1).getAsScalar().getScalar() };
					designStdDev = new double[] { nresult.getResult(2).getAsScalar().getScalar() };
				}

				// and for y
				nrequest = new MLRequest("normalise", 3);
				nrequest.addParameter(y);

				// send
				logger.info("Normalising output...");
				nresult = MATLAB.sendRequest(nrequest);
				y = nresult.getResult(0).getAsMatrix();
				evaluationResultMean = nresult.getResult(1).getAsScalar().getScalar();
				evaluationResultStdDev = nresult.getResult(2).getAsScalar().getScalar();
			}

			// setup request
			MLRequest request = new MLRequest("learn_emulator", 5);
			request.addParameter(new MLString((String)Config.getInstance().get("matlab", "gpml_path")));

			// add x
			request.addParameter(x);

			// add y
			request.addParameter(y);

			// add training size
			request.addParameter(new MLScalar(trainingSetSize));

			// setup covariance function
			MLValue covfname;
			double[] covfpar;
			if (covarianceFunction.equals("squared_exponential")) {
				covfname = new MLString("covSEiso");
			}
			else {
				covfname = new MLString("covMatern3iso");
			}
			if (nuggetVariance != null) {
				covfname = new MLCell(new MLValue[] {
					new MLString("covSum"),
					new MLCell(new MLValue[] { covfname, new MLString("covNoise") }) });
				covfpar = new double[] { lengthScale, Math.sqrt(processVariance), nuggetVariance };
			}
			else {
				covfpar = new double[] { lengthScale, Math.sqrt(processVariance) };
			}
			request.addParameter(covfname);		
			request.addParameter(new MLArray(covfpar));

			// setup mean function
			String meanfname;
			double[] meanfpar;
			if (meanFunction.equals("zero")) {
				meanfname = "";
				meanfpar = new double[] {};
			}
			else {
				meanfname = "mean_poly";
				meanfpar = new double[1];
				if (meanFunction.equals("constant")) {
					meanfpar[0] = 0;
				}
				else if (meanFunction.equals("linear")) {
					meanfpar[0] = 1;
				}
				else {
					meanfpar[0] = 2;
				}
			}
			request.addParameter(new MLString(meanfname));
			request.addParameter(new MLArray(meanfpar));

			// call matlab to: create training set, setup gp, predict, optimise
			logger.info("Learning emulator...");
			for (String identifier : design.getInputIdentifiers()) {
				DescriptiveStatistics stats = new DescriptiveStatistics(ArrayUtils.toPrimitive(design.getPoints(identifier)));
				logger.debug("identifier='" + identifier + "', min=" + stats.getMin() + ", max=" + stats.getMax());
			}
			logger.debug("meanfname=" + meanfname + ", meanfpar=" + Arrays.toString(meanfpar) + ", covfname=" + covfname + ", covfpar=" + Arrays.toString(covfpar));


			// send

			MLResult result = MATLAB.sendRequest(request);

			double[] predictedMean = result.getResult(0).getAsArray().getArray();
			double[] predictedCovariance = result.getResult(1).getAsArray().getArray();
			double[][] xtrn = result.getResult(2).getAsMatrix().getMatrix();
			double[][] ytrn = result.getResult(3).getAsMatrix().getMatrix();
			double[] optCovParams = result.getResult(4).getAsArray().getArray();

			Design trainingDesign = new Design(xtrn.length);
			ProcessEvaluationResult trainingEvaluationResult = new ProcessEvaluationResult();

			for (int i = 0; i < design.getInputIdentifiers().size(); i++) {
				// i is our row
				Double[] trn = new Double[xtrn.length];

				// j is the realisation
				for (int j = 0; j < xtrn.length; j++) {
					trn[j] = xtrn[j][i];
				}

				trainingDesign.addPoints(design.getInputIdentifiers().get(i), trn);
			}

			double[] ytrnArr = new double[ytrn.length];
			for (int i = 0; i < ytrn.length; i++) {
				ytrnArr[i] = ytrn[i][0]; // as we are only looking at one output
			}

			trainingEvaluationResult.addResults(selectedOutputIdentifier, ytrnArr);

			if (normalisation) {
				return new LearningResult(predictedMean, predictedCovariance, trainingDesign, trainingEvaluationResult, optCovParams[0], optCovParams[1] * optCovParams[1], designMean, designStdDev, evaluationResultMean, evaluationResultStdDev);
			}
			else {
				return new LearningResult(predictedMean, predictedCovariance, trainingDesign, trainingEvaluationResult, optCovParams[0], optCovParams[1] * optCovParams[1]);
			}
		}
		catch (MLException e) {
			throw new LearningException("Couldn't perform learning: " + e.getMessage());
		}
		catch (IOException e) {
			throw new LearningException("Couldn't connect to MATLAB.");
		}
		catch (ConfigException e) {
			throw new LearningException("Couldn't load MATLAB config details.");
		}
	}

}
