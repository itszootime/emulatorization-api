package org.uncertweb.et.validation;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.DesignException;
import org.uncertweb.et.design.LHSDesign;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.emulator.EmulatorEvaluator;
import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.plot.PlotData;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluator;
import org.uncertweb.et.process.ProcessEvaluatorException;
import org.uncertweb.et.response.Include;
import org.uncertweb.et.response.Respondable;
import org.uncertweb.et.value.DistributionValues;
import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLStruct;
import org.uncertweb.matlab.value.MLValue;

public class Validator implements Respondable {

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);

	@Include private ScalarValues observed;
	@Include private Values predicted;
	
	@Include private double meanBias;
	@Include private double meanMAE;
	@Include private double meanRMSE;
	@Include private double meanCorrelation;
	@Include private double medianBias;
	@Include private double medianMAE;
	@Include private double medianRMSE;
	@Include private double medianCorrelation;
	@Include private double brierScore;
	@Include private double crpsReliability;
	@Include private double crpsResolution;
	@Include private double crpsUncertainty;
	@Include private double ignReliability;
	@Include private double ignResolution;
	@Include private double ignUncertainty;
	
	@Include private PlotData vsPredictedMeanPlotData;
	@Include private PlotData vsPredictedMedianPlotData;
	@Include private PlotData standardScorePlotData;
	@Include private PlotData meanResidualHistogramData;	
	@Include private PlotData meanResidualQQPlotData;
	@Include private PlotData medianResidualHistogramData;
	@Include private PlotData medianResidualQQPlotData;
	@Include private PlotData rankHistogramData;
	@Include private PlotData reliabilityDiagramData;
	@Include private PlotData coveragePlotData;
	
	public Validator(ScalarValues observed, Values predicted) throws ValidatorException {
		this.observed = observed;
		this.predicted = predicted;
		calculateMetrics();
	}
	
	private double[][] transposeArray(double[] array) {
		// transpose array to matrix
		double[][] transpose = new double[array.length][1];
		for (int i = 0; i < transpose.length; i++) {
			transpose[i] = new double[] { array[i] };
		}
		return transpose;
	}
	
	private double[] transposeMatrix(double[][] matrix) {
		// transpose matrix to array
		double[] transpose = new double[matrix.length];
		for (int i = 0; i < transpose.length; i++) {
			transpose[i] = matrix[i][0];
		}
		return transpose;
	}

	private void calculateMetrics() throws ValidatorException {
		// build matlab request
		// FIXME: problems if sending ensemble matrix with 2 cols (code will think it's mean/variance)
		MLRequest request = new MLRequest("validate_predictions", 1);
		request.addParameter(new MLMatrix(transposeArray(observed.toArray())));
		request.addParameter(new MLMatrix(predicted.toMatrix()));
		
		// send
		try {
			MLResult result = MATLAB.sendRequest(request);
			MLStruct metrics = result.getResult(0).getAsStruct();
			
			meanBias = getMetric(metrics, "mean.bias");
			meanMAE = getMetric(metrics, "mean.mae");
			meanRMSE = getMetric(metrics, "mean.rmse");
			meanCorrelation = getMetric(metrics, "mean.correl");
			medianBias = getMetric(metrics, "median.bias");
			medianMAE = getMetric(metrics, "median.mae");
			medianRMSE = getMetric(metrics, "median.rmse");
			medianCorrelation = getMetric(metrics, "median.correl");
			brierScore = getMetric(metrics, "bs");
			crpsReliability = getMetric(metrics, "crps.rel");
			crpsResolution = getMetric(metrics, "crps.res");
			crpsUncertainty = getMetric(metrics, "crps.unc");
			ignReliability = getMetric(metrics, "ign.rel");
			ignResolution = getMetric(metrics, "ign.res");
			ignUncertainty = getMetric(metrics, "ign.unc");
			
			vsPredictedMeanPlotData = getPlotDataWithSD(metrics, "scattermean", "x", "y", "ysd");
			vsPredictedMedianPlotData = getPlotDataWithRange(metrics, "scattermedian", "x", "y", "yrange25", "yrange75");
			standardScorePlotData = getPlotData(metrics, "zscores");
			meanResidualHistogramData = getPlotData(metrics, "meanresidual.histogram");
			meanResidualQQPlotData = getPlotData(metrics, "meanresidqq");
			medianResidualHistogramData = getPlotData(metrics, "medianresidual.histogram");
			medianResidualQQPlotData = getPlotData(metrics, "medianresidqq");
			rankHistogramData = getPlotData(metrics, "rankhist");
			reliabilityDiagramData = getPlotData(metrics, "reliability");
			coveragePlotData = getPlotData(metrics, "percent", "level", "value");
		}
		catch (IOException e) {
			throw new ValidatorException("Couldn't perform validation.", e);
		}
		catch (MLException e) {
			throw new ValidatorException("Couldn't perform validation.", e);
		}
		catch (ConfigException e) {
			throw new ValidatorException("Couldn't perform validation.", e);
		}
	}
	
	private double getMetric(MLStruct metrics, String path) {
		// navigate
		MLValue current = metrics;
		for (String p : path.split("\\.")) {
			current = current.getAsStruct().getField(p);
		}
		
		// return
		return current.getAsScalar().getScalar();
	}
	
	private PlotData getPlotData(MLStruct metrics, String path) {
		return getPlotData(metrics, path, "x", "y");
	}

	private PlotData getPlotData(MLStruct metrics, String path, String xFieldName, String yFieldName) {
		// navigate
		MLStruct current = metrics;
		for (String p : path.split("\\.")) {
			current = current.getField(p).getAsStruct();
		}
		
		// build
		double[] x = getValueAsArray(current.getField(xFieldName));
		double[] y = getValueAsArray(current.getField(yFieldName));
		MLValue n = current.getField("n");
		
		if (n != null) {
			return new PlotData(x, y, getValueAsArray(n));
		}
		else {
			return new PlotData(x, y);
		}
	}
	
	private PlotData getPlotDataWithSD(MLStruct metrics, String path, String xFieldName, String yFieldName, String ySDFieldName) {
		// navigate
		MLStruct current = metrics;
		for (String p : path.split("\\.")) {
			current = current.getField(p).getAsStruct();
		}
		
		// build
		double[] x = getValueAsArray(current.getField(xFieldName));
		double[] y = getValueAsArray(current.getField(yFieldName));
		double[] ySD = getValueAsArray(current.getField(ySDFieldName));
		MLValue n = current.getField("n");
		
		double[][] yRange = new double[ySD.length][2];
		for (int i = 0; i < yRange.length; i++) {
			yRange[i] = new double[] { y[i] - ySD[i], y[i] + ySD[i] };
		}
		if (n != null) {
			return new PlotData(x, y, yRange, getValueAsArray(n));
		}
		else {
			return new PlotData(x, y, yRange);
		}
	}
	
	private PlotData getPlotDataWithRange(MLStruct metrics, String path, String xFieldName, String yFieldName, String yRangeMinName, String yRangeMaxName) {
		// navigate
		MLStruct current = metrics;
		for (String p : path.split("\\.")) {
			current = current.getField(p).getAsStruct();
		}
		
		// build
		double[] x = getValueAsArray(current.getField(xFieldName));
		double[] y = getValueAsArray(current.getField(yFieldName));
		double[] yRangeMin = getValueAsArray(current.getField(yRangeMinName));
		double[] yRangeMax = getValueAsArray(current.getField(yRangeMaxName));
		MLValue n = current.getField("n");
		
		double[][] yRange = new double[yRangeMin.length][2];
		for (int i = 0; i < yRange.length; i++) {
			yRange[i] = new double[] { yRangeMin[i], yRangeMax[i] };
		}
		if (n != null) {
			return new PlotData(x, y, yRange, getValueAsArray(n));
		}
		else {
			return new PlotData(x, y, yRange);
		}
	}
	
	private double[] getValueAsArray(MLValue field) {
		double[] array;
		if (field.isMatrix()) {
			array = transposeMatrix(field.getAsMatrix().getMatrix());
		}
		else {
			array = field.getAsArray().getArray();
		}
		return array;
	}

	public static Validator usingSimulatorAndEmulator(String serviceURL, String processIdentifier, Emulator emulator, int designSize) throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
		// get inputs and outputs from emulator
		List<Input> inputs = emulator.getInputs();
		List<Output> outputs = emulator.getOutputs();

		// create design for evaluation
		Design design = LHSDesign.create(inputs, designSize);

		// evaluate process
		long time = System.nanoTime();
		ProcessEvaluationResult processResult = ProcessEvaluator.evaluate(serviceURL, processIdentifier, inputs, outputs, design);
		long processDuration = System.nanoTime() - time;
		logger.info("Took " + (double)processDuration / 1000000000.0 + "s to evaluate process.");

		// validate
		return Validator.usingPredictionsAndEmulator(design, processResult, emulator);
	}

	public static Validator usingPredictionsAndEmulator(Design design, ProcessEvaluationResult processResult, Emulator emulator) throws ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
		// evaluate emulator
		long time = System.nanoTime();
		EmulatorEvaluationResult emulatorResult = EmulatorEvaluator.run(emulator, design);
		long emulatorDuration = System.nanoTime() - time;
		logger.info("Took " + (double)emulatorDuration / 1000000000.0 + "s to evaluate emulator.");

		// fetch results
		// FIXME: emulators can only be trained for one output... should be more elegant here!
		String outputId = emulator.getOutputs().get(0).getIdentifier();
		Double[] processResults = processResult.getResults(outputId);
		Double[] meanResults = emulatorResult.getMeanResults(outputId);
		Double[] covarianceResults = emulatorResult.getCovarianceResults(outputId);

		// for test REMOVE!
		// NormalisedProcessEvaluationResult nper = NormalisedProcessEvaluationResult.fromProcessEvaluationResult(processResult, ((NormalisedProcessEvaluationResult)emulator.getEvaluationResult()).getMeans(), ((NormalisedProcessEvaluationResult)emulator.getEvaluationResult()).getStdDevs());

		//		for (int i = 0; i < design.getSize(); i++) {
		//			System.out.println(processResults[i] + ", " + meanResults[i] + " with " + covarianceResults[i] + " covariance");
		//			System.out.println(processResults[i] - meanResults[i] + " difference");
		//		}

		// build values for now
		ScalarValues simulated = ScalarValues.fromArray(ArrayUtils.toPrimitive(processResults));
		DistributionValues emulated = DistributionValues.fromArrays(ArrayUtils.toPrimitive(meanResults), ArrayUtils.toPrimitive(covarianceResults));

		// return
		return new Validator(simulated, emulated);
	}
	
	public ScalarValues getObserved() {
		return observed;
	}

	public Values getPredicted() {
		return predicted;
	}
	
	public double getMeanBias() {
		return meanBias;
	}

	public double getMeanMAE() {
		return meanMAE;
	}

	public double getMeanRMSE() {
		return meanRMSE;
	}

	public double getMeanCorrelation() {
		return meanCorrelation;
	}

	public double getMedianBias() {
		return medianBias;
	}

	public double getMedianMAE() {
		return medianMAE;
	}

	public double getMedianRMSE() {
		return medianRMSE;
	}

	public double getMedianCorrelation() {
		return medianCorrelation;
	}

	public double getBrierScore() {
		return brierScore;
	}

	public double getCRPSReliability() {
		return crpsReliability;
	}

	public double getCRPSResolution() {
		return crpsResolution;
	}

	public double getCRPSUncertainty() {
		return crpsUncertainty;
	}

	public double getIGNReliability() {
		return ignReliability;
	}

	public double getIGNResolution() {
		return ignResolution;
	}

	public double getIGNUncertainty() {
		return ignUncertainty;
	}

	public PlotData getVsPredictedMeanPlotData() {
		return vsPredictedMeanPlotData;
	}
	
	public PlotData getVsPredictedMedianPlotData() {
		return vsPredictedMedianPlotData;
	}

	public PlotData getStandardScorePlotData() {
		return standardScorePlotData;
	}

	public PlotData getMeanResidualHistogramData() {
		return meanResidualHistogramData;
	}
	
	public PlotData getMeanResidualQQPlotData() {
		return meanResidualQQPlotData;
	}

	public PlotData getMedianResidualHistogramData() {
		return medianResidualHistogramData;
	}
	
	public PlotData getMedianResidualQQPlotData() {
		return medianResidualQQPlotData;
	}
	
	public PlotData getRankHistogramData() {
		return rankHistogramData;
	}
	
	public PlotData getReliabilityDiagramData() {
		return reliabilityDiagramData;
	}
	
	public PlotData getCoveragePlotData() {
		return coveragePlotData;
	}

	@Override
	public String getResponseName() {
		return "Validation";
	}

}
