package org.uncertweb.et.sensitivity;

import java.awt.image.BufferedImage;
import java.util.List;

public class SobolOutputResult {

	private String outputIdentifier;
	private List<SobolInputResult> inputResults;
	private BufferedImage plot;
	
	public SobolOutputResult(String outputIdentifier, List<SobolInputResult> inputResults) {
		this(outputIdentifier, inputResults, null);
	}
	
	public SobolOutputResult(String outputIdentifier, List<SobolInputResult> inputResults, BufferedImage plot) {
		this.outputIdentifier = outputIdentifier;
		this.inputResults = inputResults;
		this.plot = plot;
	}

	public String getOutputIdentifier() {
		return outputIdentifier;
	}

	public List<SobolInputResult> getInputResults() {
		return inputResults;
	}

	public BufferedImage getPlot() {
		return plot;
	}

	public void setPlot(BufferedImage plot) {
		this.plot = plot;
	}
	
}
