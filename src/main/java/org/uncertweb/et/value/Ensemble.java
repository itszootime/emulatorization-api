package org.uncertweb.et.value;

public class Ensemble extends Value {

	private double[] members;
	
	public Ensemble(double[] members) {
		this.members = members;
	}
	
	public double[] getMembers() {
		return members;
	}
	
}
