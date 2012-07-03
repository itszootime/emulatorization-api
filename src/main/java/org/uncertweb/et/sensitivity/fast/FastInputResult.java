package org.uncertweb.et.sensitivity.fast;

import org.uncertweb.et.sensitivity.AnalysisInputResult;

public class FastInputResult extends AnalysisInputResult {

	private double d1;
	private double dt;
	
	public FastInputResult(String inputIdentifier, double d1, double dt) {
		super(inputIdentifier);
		this.d1 = d1;
		this.dt = dt;
	}

	public double getD1() {
		return d1;
	}

	public double getDt() {
		return dt;
	}	
	
}
