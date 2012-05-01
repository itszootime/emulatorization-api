package org.uncertweb.et.design;


public class MultiplePoint extends Point {

	private Double[] points;
	
	public MultiplePoint(Double[] points) {
		this.points = points;
	}
	
	public Double[] getPoints() {
		return points;
	}
	
}
