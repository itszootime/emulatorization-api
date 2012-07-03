package org.uncertweb.et.sensitivity.fast;

import org.uncertweb.et.sensitivity.AnalysisInputResult;

public class FastInputResult extends AnalysisInputResult {

	private double d1;
	private double dt;
	private double v;
	
	public FastInputResult(String inputIdentifier, double d1, double dt, double v) {
		super(inputIdentifier);
		this.d1 = d1;
		this.dt = dt;
		this.v = v;
	}

	public double getD1() {
		return d1;
	}

	public double getDt() {
		return dt;
	}	
	
	public double getV() {
		return v;
	}
	
}
