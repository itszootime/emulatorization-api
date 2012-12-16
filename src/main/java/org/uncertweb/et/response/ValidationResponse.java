package org.uncertweb.et.response;

import org.uncertweb.et.plot.PlotData;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;

public class ValidationResponse extends Response {
	
	private double rmse;
	private PlotData standardScorePlotData;
	private PlotData meanResidualHistogramData;
	private PlotData meanResidualQQPlotData;
	private PlotData medianResidualHistogramData;
	private PlotData medianResidualQQPlotData;
	private PlotData reliabilityDiagramData;

	private ScalarValues observed;
	private Values predicted;
	
	public ValidationResponse() {
		super();
	}
	
	public void setObserved(ScalarValues observed) {
		this.observed = observed;
	}

	public void setPredicted(Values predicted) {
		this.predicted = predicted;
	}	
	
	public ScalarValues getObserved() {
		return observed;
	}

	public Values getPredicted() {
		return predicted;
	}
	
	public void setRMSE(double rmse) {
		this.rmse = rmse;
	}

	public void setStandardScorePlotData(PlotData data) {
		this.standardScorePlotData = data;
	}

	public void setMeanResidualHistogramData(PlotData data) {
		this.meanResidualHistogramData = data;
	}
	
	public void setMeanResidualQQPlotData(PlotData data) {
		this.meanResidualQQPlotData = data;
	}

	public void setMedianResidualHistogramData(PlotData data) {
		this.medianResidualHistogramData = data;
	}
	
	public void setMedianResidualQQPlotData(PlotData data) {
		this.medianResidualQQPlotData = data;
	}

	public void setReliabilityDiagramData(PlotData data) {
		this.reliabilityDiagramData = data;
	}
	
	public double getRMSE() {
		return rmse;
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

	public PlotData getReliabilityDiagramData() {
		return reliabilityDiagramData;
	}

	public static ValidationResponse fromValidator(Validator validator) {
		ValidationResponse response = new ValidationResponse();
		response.setObserved(validator.getObserved());
		response.setPredicted(validator.getPredicted());
		response.setRMSE(validator.getRMSE());
		response.setStandardScorePlotData(validator.getStandardScorePlotData());
		response.setMeanResidualHistogramData(validator.getMeanResidualHistogramData());
		response.setMeanResidualQQPlotData(validator.getMeanResidualQQPlotData());
		response.setMedianResidualHistogramData(validator.getMedianResidualHistogramData());
		response.setMedianResidualQQPlotData(validator.getMedianResidualQQPlotData());
		response.setReliabilityDiagramData(validator.getReliabilityDiagramData());
		return response;
	}
	
}
