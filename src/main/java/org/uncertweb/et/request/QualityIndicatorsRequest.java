package org.uncertweb.et.request;

import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;

import java.util.List;
import java.util.Map;

public class QualityIndicatorsRequest extends Request {

	private ScalarValues observed;
	private Values predicted;
	private double learningPercentage;
	private Map<String, List<String>> indicators;

	public QualityIndicatorsRequest() {

	}

	public ScalarValues getObserved() {
		return observed;
	}

	public Values getPredicted() {
		return predicted;
	}

	public double getLearningPercentage() {
		return learningPercentage;
	}

	public Map<String, List<String>> getIndicators() {
		return indicators;
	}

}
