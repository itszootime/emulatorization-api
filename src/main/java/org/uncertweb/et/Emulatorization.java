package org.uncertweb.et;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.description.ProcessDescription;
import org.uncertweb.et.description.ServiceDescription;
import org.uncertweb.et.description.parser.ServiceDescriptionParser;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.LHSDesign;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.emulator.EmulatorEvaluator;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluator;
import org.uncertweb.et.quality.QualityIndicators;
import org.uncertweb.et.request.*;
import org.uncertweb.et.response.DesignResponse;
import org.uncertweb.et.response.EvaluateEmulatorResponse;
import org.uncertweb.et.response.EvaluateProcessResponse;
import org.uncertweb.et.response.GetProcessDescriptionResponse;
import org.uncertweb.et.response.GetProcessIdentifiersResponse;
import org.uncertweb.et.response.TrainEmulatorResponse;
import org.uncertweb.et.response.ScreeningResponse;
import org.uncertweb.et.response.SensitivityResponse;
import org.uncertweb.et.response.StatusResponse;
import org.uncertweb.et.screening.Screening;
import org.uncertweb.et.sensitivity.AnalysisOutputResult;
import org.uncertweb.et.sensitivity.fast.Fast;
import org.uncertweb.et.sensitivity.sobol.Sobol;
import org.uncertweb.et.training.Training;
import org.uncertweb.et.training.TrainingResult;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;

public class Emulatorization {

	private static final Logger logger = LoggerFactory.getLogger(Emulatorization.class);

	private Emulatorization() {

	}

	// TODO: request object validation.
	// TODO: if no outputs are passed to screening you get null pointer
	// handler: pass request objects -> delegates to correct classes -> returns response
	public static Object process(Request request) throws Exception {
		try {
			if (request instanceof GetProcessIdentifiersRequest) {
				GetProcessIdentifiersRequest gpiRequest = (GetProcessIdentifiersRequest) request;

				ServiceDescription description = ServiceDescriptionParser.parse(gpiRequest.getServiceURL());
				List<String> processIdentifiers = description.getProcessIdentifiers();

				return new GetProcessIdentifiersResponse(processIdentifiers);
			}
			else if (request instanceof GetProcessDescriptionRequest) {
				GetProcessDescriptionRequest gpdRequest = (GetProcessDescriptionRequest) request;

				ServiceDescription description = ServiceDescriptionParser.parse(gpdRequest.getServiceURL());
				ProcessDescription processDescription = description.getProcessDescription(gpdRequest.getProcessIdentifier());

				// FIXME: will return blank response if unsupported data type, should be an exception

				return new GetProcessDescriptionResponse(processDescription);
			}
			else if (request instanceof ScreeningRequest) {
				ScreeningRequest sRequest = (ScreeningRequest) request;

				int numTraj = sRequest.getNumTrajectories();
				int discretisationLevel = sRequest.getDiscretisationLevel();

				Screening screening;
				if (numTraj == 0 && discretisationLevel == 0) {
					screening = new Screening(sRequest.getServiceURL(), sRequest.getProcessIdentifier(), sRequest.getInputs(), sRequest.getOutputs());
				}
				else {
					screening = new Screening(sRequest.getServiceURL(), sRequest.getProcessIdentifier(), sRequest.getInputs(), sRequest.getOutputs(), numTraj, discretisationLevel);
				}

				return new ScreeningResponse(screening.run());
			}
			else if (request instanceof DesignRequest) {
				DesignRequest dRequest = (DesignRequest) request;

				Design design = LHSDesign.create(dRequest.getInputs(), dRequest.getSize());

				return new DesignResponse(design);
			}
			else if (request instanceof EvaluateProcessRequest) {
				EvaluateProcessRequest eRequest = (EvaluateProcessRequest) request;

				ProcessEvaluationResult result = ProcessEvaluator.evaluate(eRequest.getServiceURL(), eRequest.getProcessIdentifier(), eRequest.getInputs(), eRequest.getOutputs(), eRequest.getDesign());

				return new EvaluateProcessResponse(result);
			}
			else if (request instanceof TrainEmulatorRequest) {
				TrainEmulatorRequest lRequest = (TrainEmulatorRequest) request;

				TrainingResult result = Training.learn(lRequest.getDesign(), lRequest.getEvaluationResult(), lRequest.getSelectedOutputIdentifier(), lRequest.getCovarianceFunction(), lRequest.getLengthScaleMultiplier(), lRequest.getNuggetVariance(), lRequest.getMeanFunction(), lRequest.isNormalisation());

				return new TrainEmulatorResponse(result);
			}
			else if (request instanceof ValidationRequest) {
				ValidationRequest vRequest = (ValidationRequest) request;

				Validator validator;
				if (vRequest.getEmulator() == null) {
					validator = new Validator(vRequest.getObserved(), vRequest.getPredicted());
				}
				else {
					if (vRequest.getDesign() != null) {
						validator = Validator.usingPredictionsAndEmulator(vRequest.getDesign(), vRequest.getEvaluationResult(), vRequest.getEmulator());
					}
					else {
						validator = Validator.usingSimulatorAndEmulator(vRequest.getServiceURL(), vRequest.getProcessIdentifier(), vRequest.getEmulator(), vRequest.getDesignSize());
					}
				}

				return validator;
			}
			else if (request instanceof QualityIndicatorsRequest) {
				QualityIndicatorsRequest qRequest = (QualityIndicatorsRequest) request;

				QualityIndicators qualityIndicators = new QualityIndicators();
				qualityIndicators.compute(qRequest.getReference(), qRequest.getObserved(), qRequest.getLearningPercentage(), qRequest.getMetrics());

				return qualityIndicators;
			}
			else if (request instanceof EvaluateEmulatorRequest) {
				EvaluateEmulatorRequest eRequest = (EvaluateEmulatorRequest) request;

				EmulatorEvaluationResult result = EmulatorEvaluator.run(eRequest.getEmulator(), eRequest.getDesign());

				return new EvaluateEmulatorResponse(result);
			}
			else if (request instanceof SensitivityRequest) {
				SensitivityRequest sRequest = (SensitivityRequest) request;

				List<AnalysisOutputResult> results;
				if (sRequest.getMethod().equals("sobol")) {
					if (sRequest.getEmulator() != null) {
						results = Sobol.run(sRequest.getEmulator(), sRequest.isPlot(), sRequest.getDesignSize(), sRequest.getNumBoot(), sRequest.getConfidenceLevel());
					}
					else {
						results = Sobol.run(sRequest.getInputs(), sRequest.getOutputs(), sRequest.getServiceURL(), sRequest.getProcessIdentifier(), sRequest.isPlot(), sRequest.getDesignSize(), sRequest.getNumBoot(), sRequest.getConfidenceLevel());
					}
				}
				else {
					if (sRequest.getEmulator() != null) {
						results = Fast.run(sRequest.getEmulator(), sRequest.isPlot(), sRequest.getDesignSize());
					}
					else {
						results = Fast.run(sRequest.getInputs(), sRequest.getOutputs(), sRequest.getServiceURL(), sRequest.getProcessIdentifier(), sRequest.isPlot(), sRequest.getDesignSize());
					}
				}

				return new SensitivityResponse(results);
			}
			else if (request instanceof StatusRequest) {
				// specify the timeout in seconds
				int timeout = 5;
				TimeUnit timeoutUnit = TimeUnit.SECONDS;
				// thread pool for testing MATLAB and Rserve
				ExecutorService executor = Executors.newFixedThreadPool(2);

				// MATLAB task
				boolean matlabOK = false;
				String matlabMessage;
				Callable<MLResult> check_matlab = new Callable<MLResult>() {
					public MLResult call() throws MLException, IOException, ConfigException {
						MLRequest mlRequest = new MLRequest("matlab_version", 1);
						return MATLAB.sendRequest(mlRequest);
					}
				};

				// RServe task
				boolean rserveOK = false;
				String rserveMessage;
				RConnection c = null;
				Callable<RConnection> check_rserve = new Callable<RConnection>() {
					public RConnection call() throws RserveException, ConfigException {
						return R.getConnection();
					}
				};

				// submit tasks ready for execution
				Future<MLResult> matlab_result = executor.submit(check_matlab);
				Future<RConnection> rserve_result = executor.submit(check_rserve);

				try {
					// the request is successful if the MATLAB version is returned
					MLResult result = matlab_result.get(timeout, timeoutUnit);
					matlabOK = true;
					matlabMessage = "Ready (version " + result.getResult(0).getAsString().getString() + ")";
				}
				// thrown if the task doesn't complete during the timeout period
				catch (TimeoutException e) {
					matlabMessage = "Request to MATLAB timed out";
				}
				// thrown if the Callable throws an exception
				catch (ExecutionException e) {
					Throwable t = e.getCause();
					if (t instanceof MLException) {
						matlabMessage = "Couldn't get version information from MATLAB";
					}
					else if (t instanceof IOException) {
						matlabMessage = "Couldn't connect to MATLAB";
					}
					else {
						matlabMessage = t.getMessage();
					}
				}

				try {
					// the request is successful if a connection to Rserve is opened
					c = rserve_result.get(timeout, timeoutUnit);
					rserveOK = true;
					rserveMessage = "Ready (version " + c.eval("R.Version()$version.string").asString() + ")";
				}
				catch (TimeoutException e) {
					rserveMessage = "Request to R timed out";
				}
				catch (ExecutionException e) {
					Throwable t = e.getCause();
					if (t instanceof RserveException) {
						rserveMessage = "Couldn't connect to R";
					}
					else {
						rserveMessage = t.getMessage();
					}
				}
				finally {
					if (c != null) {
						c.close();
					}
				}

				executor.shutdownNow();

				// respond with the status of the API and its components
				return new StatusResponse("Ready", matlabOK, matlabMessage, rserveOK, rserveMessage);
			}
			else {
				// shouldn't ever happen
				return null;
			}
		}
		catch (Exception e) {
			// log it first
			logger.error(e.getMessage(), e);

			// then throw
			throw e;
		}
	}

}
