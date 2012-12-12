package org.uncertweb.et.response;

import org.uncertweb.et.plot.PlotData;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;

public class ValidationResponse extends Response {
	
	private double rmse;
	private PlotData standardScoreData;
	private PlotData meanResidualData;
	private PlotData medianResidualData;
	private PlotData reliabilityData;

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

	public void setStandardScoreData(PlotData standardScoreData) {
		this.standardScoreData = standardScoreData;
	}

	public void setMeanResidualData(PlotData meanResidualData) {
		this.meanResidualData = meanResidualData;
	}

	public void setMedianResidualData(PlotData medianResidualData) {
		this.medianResidualData = medianResidualData;
	}

	public void setReliabilityData(PlotData reliabilityData) {
		this.reliabilityData = reliabilityData;
	}
	
	public double getRMSE() {
		return rmse;
	}

	public PlotData getStandardScoreData() {
		return standardScoreData;
	}

	public PlotData getMeanResidualData() {
		return meanResidualData;
	}

	public PlotData getMedianResidualData() {
		return medianResidualData;
	}

	public PlotData getReliabilityData() {
		return reliabilityData;
	}

	public static ValidationResponse fromValidator(Validator validator) {
		ValidationResponse response = new ValidationResponse();
		response.setObserved(validator.getObserved());
		response.setPredicted(validator.getPredicted());
		response.setRMSE(validator.getRMSE());
		response.setStandardScoreData(validator.getStandardScoreData());
		response.setMeanResidualData(validator.getMeanResidualData());
		response.setMedianResidualData(validator.getMedianResidualData());
		response.setReliabilityData(validator.getReliabilityData());
		return response;
	}
	
}
