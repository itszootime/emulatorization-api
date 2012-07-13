package org.uncertweb.et.sensitivity.fast;

import org.uncertweb.et.sensitivity.AnalysisInputResult;

public class FastInputResult extends AnalysisInputResult {

	private double mainEffect;
	private double interactions;
	private double variance;
	
	public FastInputResult(String inputIdentifier, double mainEffect, double interactions, double variance) {
		super(inputIdentifier);
		this.mainEffect = mainEffect;
		this.interactions = interactions;
		this.variance = variance;
	}

	/**
	 * the estimations of Variances of the Conditional Expectations (VCE) with respect
	 * to each factor
	 * 
	 * @return
	 */
	public double getMainEffect() {
		return mainEffect;
	}

	/**
	 * the estimations of VCE with respect to each factor complementary set of factors
	 * ("all but Xi")
	 * 
	 * @return
	 */
	public double getInteractions() {
		return interactions;
	}	
	
	/**
	 * the estimation of variance
	 * 
	 * @return
	 */
	public double getVariance() {
		return variance;
	}
	
}
