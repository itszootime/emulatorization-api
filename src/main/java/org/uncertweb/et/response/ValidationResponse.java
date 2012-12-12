package org.uncertweb.et.response;

import org.uncertweb.et.plot.PlotData;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.value.NumericValues;
import org.uncertweb.et.value.Values;

public class ValidationResponse extends Response {
	
	private double rmse;
	private NumericValues standardScores;
	private PlotData meanResidualHistogram;
	private PlotData medianResidualHistogram;
	private PlotData reliabilityDiagram;

	private NumericValues observed;
	private Values predicted;
	
	public ValidationResponse() {
		super();
	}
	
	public void setObserved(NumericValues observed) {
		this.observed = observed;
	}

	public void setPredicted(Values predicted) {
		this.predicted = predicted;
	}	
	
	public NumericValues getObserved() {
		return observed;
	}

	public Values getPredicted() {
		return predicted;
	}
	
	public void setRMSE(double rmse) {
		this.rmse = rmse;
	}

	public void setStandardScores(NumericValues standardScores) {
		this.standardScores = standardScores;
	}

	public void setMeanResidualHistogram(PlotData meanResidualHistogram) {
		this.meanResidualHistogram = meanResidualHistogram;
	}

	public void setMedianResidualHistogram(PlotData medianResidualHistogram) {
		this.medianResidualHistogram = medianResidualHistogram;
	}

	public void setReliabilityDiagram(PlotData reliabilityDiagram) {
		this.reliabilityDiagram = reliabilityDiagram;
	}
	
	public double getRMSE() {
		return rmse;
	}

	public NumericValues getStandardScores() {
		return standardScores;
	}

	public PlotData getMeanResidualHistogram() {
		return meanResidualHistogram;
	}

	public PlotData getMedianResidualHistogram() {
		return medianResidualHistogram;
	}

	public PlotData getReliabilityDiagram() {
		return reliabilityDiagram;
	}

	public static ValidationResponse fromValidator(Validator validator) {
		ValidationResponse response = new ValidationResponse();
		response.setObserved(validator.getObserved());
		response.setPredicted(validator.getPredicted());
		response.setRMSE(validator.getRMSE());
		response.setStandardScores(validator.getStandardScores());
		response.setMeanResidualHistogram(validator.getMeanResidualHistogram());
		response.setMedianResidualHistogram(validator.getMedianResidualHistogram());
		response.setReliabilityDiagram(validator.getReliabilityDiagram());
		return response;
	}
	
}
