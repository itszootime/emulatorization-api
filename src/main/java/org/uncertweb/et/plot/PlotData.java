package org.uncertweb.et.plot;

public class PlotData {

	private double[] x;
	private double[] y;
	private double[] n;
	
	public PlotData(double[] x, double[] y) {
		this.x = x;
		this.y = y;
	}
	
	public PlotData(double[] x, double[] y, double[] n) {
		this(x, y);
		this.n = n;
	}

	public double[] getX() {
		return x;
	}

	public double[] getY() {
		return y;
	}

	public double[] getN() {
		return n;
	}
	
}
