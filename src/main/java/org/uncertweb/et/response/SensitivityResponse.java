package org.uncertweb.et.response;

import java.util.List;

import org.uncertweb.et.sensitivity.SobolOutputResult;

public class SensitivityResponse extends Response {

	private List<SobolOutputResult> results;

	public SensitivityResponse(List<SobolOutputResult> results) {
		this.results = results;
	}

	public List<SobolOutputResult> getResults() {
		return results;
	}
	
}
