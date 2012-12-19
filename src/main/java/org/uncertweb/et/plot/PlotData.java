package org.uncertweb.et.plot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class PlotData {

	private double[] x;
	private double[] y;
	private double[] n;
	//private double[][] xRange;
	private double[][] yRange;
	
	public PlotData(double[] x, double[] y) {
		this(x, y, null, null);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param yRange array of [min, max]
	 */
	public PlotData(double[] x, double[] y, double[][] yRange) {
		this(x, y, yRange, null);
	}
	
	public PlotData(double[] x, double[] y, double[] n) {
		this(x, y, null, n);
	}
	
	public PlotData(double[] x, double[] y, double[][] yRange, double[] n) {
		// filter nan, we can't plot these!
		List<Double> xList = new ArrayList<Double>();
		List<Double> yList = new ArrayList<Double>();
		List<Double[]> yRangeList = new ArrayList<Double[]>();
		List<Double> nList = new ArrayList<Double>();
		for (int i = 0; i < x.length; i++) {
			double xVal = x[i];
			double yVal = y[i];
			if (!Double.isNaN(xVal) && !Double.isNaN(yVal)) {
				xList.add(xVal);
				yList.add(yVal);
				if (yRange != null) {
					yRangeList.add(ArrayUtils.toObject(yRange[i]));
				}
				if (n != null) {
					nList.add(n[i]);
				}
			}
		}
		
		this.x = ArrayUtils.toPrimitive(xList.toArray(new Double[0]));
		this.y = ArrayUtils.toPrimitive(yList.toArray(new Double[0]));
		if (yRangeList.size() != 0) {
			double[][] yRangeArray = new double[yRangeList.size()][];
			for (int i = 0; i < yRangeArray.length; i++) {
				yRangeArray[i] = ArrayUtils.toPrimitive(yRangeList.get(i));
			}
			this.yRange = yRangeArray;
		}
		if (nList.size() != 0) {
			this.n = ArrayUtils.toPrimitive(nList.toArray(new Double[0]));
		}
	}

	public double[] getX() {
		return x;
	}

	public double[] getY() {
		return y;
	}
	
	public double[][] getYRange() {
		return yRange;
	}

	public double[] getN() {
		return n;
	}
	
}
