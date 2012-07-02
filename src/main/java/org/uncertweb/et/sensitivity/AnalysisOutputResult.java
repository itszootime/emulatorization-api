package org.uncertweb.et.sensitivity;

import java.awt.image.BufferedImage;
import java.util.List;

public class AnalysisOutputResult {

	private String outputIdentifier;
	private List<AnalysisInputResult> inputResults;
	private BufferedImage plot;
	
	public AnalysisOutputResult(String outputIdentifier, List<AnalysisInputResult> inputResults) {
		this(outputIdentifier, inputResults, null);
	}
	
	public AnalysisOutputResult(String outputIdentifier, List<AnalysisInputResult> inputResults, BufferedImage plot) {
		this.outputIdentifier = outputIdentifier;
		this.inputResults = inputResults;
		this.plot = plot;
	}

	public String getOutputIdentifier() {
		return outputIdentifier;
	}

	public List<AnalysisInputResult> getInputResults() {
		return inputResults;
	}

	public BufferedImage getPlot() {
		return plot;
	}

	public void setPlot(BufferedImage plot) {
		this.plot = plot;
	}
	
}
