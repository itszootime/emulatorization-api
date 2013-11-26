package org.uncertweb.et.request;

import org.uncertweb.et.value.ScalarValues;
import org.uncertweb.et.value.Values;

public class QualityIndicatorsRequest extends Request {

	private ScalarValues observed;
	private Values predicted;
	private double learningPercentage;

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

}
