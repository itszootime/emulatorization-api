package org.uncertweb.et.response;

import java.util.List;

import org.uncertweb.et.screening.MorrisOutputResult;

public class ScreeningResponse extends Response {

	//meanEE, meanStarEE, stdEE, EE
	// design too??
	
	private List<MorrisOutputResult> results;
	
	public ScreeningResponse(List<MorrisOutputResult> results) {
		this.results = results;
	}

	public List<MorrisOutputResult> getResults() {
		return results;
	}
	
}
