package org.uncertweb.et.response;

import java.util.List;

import org.uncertweb.et.sensitivity.AnalysisOutputResult;

public class SensitivityResponse extends Response {

	private List<AnalysisOutputResult> results;

	public SensitivityResponse(List<AnalysisOutputResult> results) {
		this.results = results;
	}

	public List<AnalysisOutputResult> getResults() {
		return results;
	}
	
}
