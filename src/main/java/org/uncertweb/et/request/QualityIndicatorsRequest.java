package org.uncertweb.et.request;

import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;

import java.util.List;
import java.util.Map;

public class QualityIndicatorsRequest extends Request {

	private ScalarValues reference;
	private Values observed;
	private double learningPercentage;
	private Map<String, List<String>> metrics;

	public QualityIndicatorsRequest() {

	}

	public ScalarValues getReference() {
		return reference;
	}

	public Values getObserved() {
		return observed;
	}

	public double getLearningPercentage() {
		return learningPercentage;
	}

	public Map<String, List<String>> getMetrics() {
		return metrics;
	}

}
