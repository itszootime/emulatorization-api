package org.uncertweb.et.screening;

import java.util.List;

/**
 * A Morris result for an output.
 * 
 * @author Richard Jones
 *
 */
public class MorrisOutputResult {

	private String outputIdentifier;
	private List<MorrisInputResult> inputResults;
	
	public MorrisOutputResult(String outputIdentifier, List<MorrisInputResult> results) {
		this.outputIdentifier = outputIdentifier;
		this.inputResults = results;
	}

	public String getOutputIdentifier() {
		return outputIdentifier;
	}

	public List<MorrisInputResult> getResults() {
		return inputResults;
	}
	
}
