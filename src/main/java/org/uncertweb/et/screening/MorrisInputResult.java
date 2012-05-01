package org.uncertweb.et.screening;

/**
 * A Morris result for an input.
 * 
 * @author Richard Jones
 *
 */
public class MorrisInputResult {

	private String inputIdentifier;
	private double meanEE;
	private double meanStarEE;
	private double stdEE;
	private double[] ee;
	
	public MorrisInputResult(String inputIdentifier, double meanEE, double meanStarEE, double stdEE, double[] ee) {
		this.inputIdentifier = inputIdentifier;
		this.meanEE = meanEE;
		this.meanStarEE = meanStarEE;
		this.stdEE = stdEE;
		this.ee = ee;
	}

	public String getInputIdentifier() {
		return inputIdentifier;
	}

	public double getMeanEE() {
		return meanEE;
	}

	public double getMeanStarEE() {
		return meanStarEE;
	}

	public double getStdEE() {
		return stdEE;
	}

	public double[] getEE() {
		return ee;
	}
	
}
