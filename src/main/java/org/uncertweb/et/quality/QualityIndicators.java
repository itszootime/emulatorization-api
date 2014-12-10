package org.uncertweb.et.quality;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.plot.PlotData;
import org.uncertweb.et.response.Include;
import org.uncertweb.et.response.Respondable;
import org.uncertweb.et.value.DistributionValues;
import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLScalar;
import org.uncertweb.matlab.value.MLStruct;
import org.uncertweb.matlab.value.MLValue;

public class QualityIndicators implements Respondable {

	private static final Logger logger = LoggerFactory.getLogger(QualityIndicators.class);

	private Map<String, List<String>> metrics;

	@Include private ScalarValues reference;
	@Include private Values observed;

	@Include private double meanBias;
	@Include private double meanMAE;
	@Include private double meanRMSE;
	@Include private double meanCorrelation;
	@Include private double brierScore;

	@Include private PlotData vsObservedMeanPlotData;
	@Include private PlotData standardScorePlotData;
	@Include private PlotData meanResidualHistogramData;
	@Include private PlotData meanResidualQQPlotData;
	@Include private PlotData rankHistogramData;
	@Include private PlotData reliabilityDiagramData;
	@Include private PlotData coveragePlotData;

	@Include private QualityIndicatorsResult qualityIndicatorsResult;

	public QualityIndicators() {
		metrics = new HashMap<String, List<String>>();
		metrics.put("distribution", Arrays.asList("normal"));
		metrics.put("statistics", Arrays.asList("correlation", "mean", "stdev", "skewness", "kurtosis", "median", "quantiles"));
	}

	public void compute(ScalarValues reference, Values observed, double learningPercentage, Map<String, List<String>> requestedMetrics) throws QualityIndicatorsException {
		// fraction of data to be used for learning: expressed as a percentage
		double modifier = (learningPercentage / 100);

		// convert to primitive arrays
		double[] referenceArray = reference.toArray();
		double[] observedArray = ((ScalarValues) observed).toArray();

		if (referenceArray.length != observedArray.length) {
			throw new QualityIndicatorsException("Reference and observed arrays must be the same length (reference: " + referenceArray.length + ", observed:" + observedArray.length + ")");
		}

		// shuffle both arrays using the Knuth-Fisher-Yates algorithm
		Random rng = new Random();
		for (int i = referenceArray.length - 1; i > 0; i--) {
			int rand = rng.nextInt(i + 1);
			double tmp;
			// reference value
			tmp = referenceArray[rand];
			referenceArray[rand] = referenceArray[i];
			referenceArray[i] = tmp;
			// observed value
			tmp = observedArray[rand];
			observedArray[rand] = observedArray[i];
			observedArray[i] = tmp;
		}

		// splice the reference values to be used for learning and validation into separate arrays
		int referenceIndex = (int) Math.round(referenceArray.length * modifier);
		double[] referenceLearning = Arrays.copyOfRange(referenceArray, 0, referenceIndex);
		double[] referenceValidation = Arrays.copyOfRange(referenceArray, referenceIndex, referenceArray.length);

		// splice the observed values to be used for learning and validation into separate arrays
		int observedIndex = (int) Math.round(observedArray.length * modifier);
		double[] observedLearning = Arrays.copyOfRange(observedArray, 0, observedIndex);
		double[] observedValidation = Arrays.copyOfRange(observedArray, observedIndex, observedArray.length);

		// create a new struct that will contain all of the computed quality metrics
		MLStruct qi = new MLStruct();

		// initialize the QI struct
		for (Map.Entry<String, List<String>> metric : metrics.entrySet()) {
			boolean keyExists = requestedMetrics.containsKey(metric.getKey());
			MLStruct parent = new MLStruct();

			for (String field : metric.getValue()) {
				// only compute those metrics that have been requested but also force the computation of a normal distribution
				boolean compute = ((keyExists && requestedMetrics.get(metric.getKey()).contains(field)) ||
									(metric.getKey().compareTo("distribution") == 0 && field.compareTo("normal") == 0));
				MLStruct child = new MLStruct();
				child.setField("compute", new MLScalar(compute ? 1 : 0));
				parent.setField(field, child);
			}

			qi.setField(metric.getKey(), parent);
		}

		// construct a request to call the function that will compute the quality indicators
		MLRequest request = new MLRequest("compute_cts_quality_indicators", 1);
		request.addParameter(new MLMatrix(transposeArray(referenceLearning)));
		request.addParameter(new MLMatrix(transposeArray(observedLearning)));
		request.addParameter(qi);

		try {
			// send the request to MATLAB
			logger.debug("Sending compute quality indicators request to MATLAB...");
			MLResult result = MATLAB.sendRequest(request);
			logger.debug("Finished computing quality indicators in MATLAB.");

			// wrap the returned struct in a new object that will later be serialized as part of the response
			qualityIndicatorsResult = new QualityIndicatorsResult(result.getResult(0).getAsStruct());

			// create a distribution from the observed values that will be used for validation
			DistributionValues observedDistribution = new DistributionValues();
			for (Double value : observedValidation) {
				observedDistribution.add(
						(value - getMetric(qualityIndicatorsResult.getQi(), "distribution.normal.mean")),
						getMetric(qualityIndicatorsResult.getQi(), "distribution.normal.variance")
				);
			}

			this.reference = ScalarValues.fromArray(referenceValidation);
			this.observed = observedDistribution;
		}
		catch (IOException e) {
			throw new QualityIndicatorsException("Couldn't compute quality indicators.", e);
		}
		catch (MLException e) {
			throw new QualityIndicatorsException("Couldn't compute quality indicators. " + e.getMessage(), e);
		}
		catch (ConfigException e) {
			throw new QualityIndicatorsException("Couldn't compute quality indicators.", e);
		}

		// continue with validation
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

	private void calculateMetrics() throws QualityIndicatorsException {
		// build matlab request
		// FIXME: problems if sending ensemble matrix with 2 cols (code will think it's mean/variance)
		MLRequest request = new MLRequest("validate_predictions_geoviqua", 1);
		request.addParameter(new MLMatrix(transposeArray(reference.toArray())));
		request.addParameter(new MLMatrix(observed.toMatrix()));

		// send
		try {
			logger.debug("Sending GeoViQua validation request to MATLAB...");
			MLResult result = MATLAB.sendRequest(request);
			logger.debug("Finished validating in MATLAB.");
			MLStruct metrics = result.getResult(0).getAsStruct();

			meanBias = getMetric(metrics, "mean.bias");
			meanMAE = getMetric(metrics, "mean.mae");
			meanRMSE = getMetric(metrics, "mean.rmse");
			meanCorrelation = getMetric(metrics, "mean.correl");
			brierScore = getMetric(metrics, "bs");

			vsObservedMeanPlotData = getPlotDataWithSD(metrics, "scattermean", "x", "y", "ysd");
			standardScorePlotData = getPlotData(metrics, "zscores");
			meanResidualHistogramData = getPlotData(metrics, "meanresidual.histogram");
			meanResidualQQPlotData = getPlotData(metrics, "meanresidqq");
			rankHistogramData = getPlotData(metrics, "rankhist");
			reliabilityDiagramData = getPlotData(metrics, "reliability");
			coveragePlotData = getPlotData(metrics, "percent", "level", "value");
		}
		catch (IOException e) {
			throw new QualityIndicatorsException("Couldn't perform validation.", e);
		}
		catch (MLException e) {
			throw new QualityIndicatorsException("Couldn't perform validation. "  + e.getMessage(), e);
		}
		catch (ConfigException e) {
			throw new QualityIndicatorsException("Couldn't perform validation.", e);
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

	public ScalarValues getReference() {
		return reference;
	}

	public Values getObserved() {
		return observed;
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

	public double getBrierScore() {
		return brierScore;
	}

	public PlotData getVsObservedMeanPlotData() {
		return vsObservedMeanPlotData;
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

	public PlotData getRankHistogramData() {
		return rankHistogramData;
	}

	public PlotData getReliabilityDiagramData() {
		return reliabilityDiagramData;
	}

	public PlotData getCoveragePlotData() {
		return coveragePlotData;
	}

	public QualityIndicatorsResult qualityIndicatorsResult() {
		return qualityIndicatorsResult;
	}

	@Override
	public String getResponseName() {
		return "QualityIndicators";
	}

}
