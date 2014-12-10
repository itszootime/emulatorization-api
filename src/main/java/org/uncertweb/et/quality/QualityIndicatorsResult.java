package org.uncertweb.et.quality;

import org.uncertweb.matlab.value.MLStruct;

public class QualityIndicatorsResult {

	private MLStruct qi;

	public QualityIndicatorsResult(MLStruct qi) {

		this.qi = qi;
	}

	public MLStruct getQi() {
		return qi;
	}

}
