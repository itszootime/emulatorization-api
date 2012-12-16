package org.uncertweb.et.plot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class PlotData {

	private double[] x;
	private double[] y;
	private double[] n;
	
	public PlotData(double[] x, double[] y) {
		this(x, y, null);
	}
	
	public PlotData(double[] x, double[] y, double[] n) {
		// filter nan, we can't plot these!
		List<Double> xlist = new ArrayList<Double>();
		List<Double> ylist = new ArrayList<Double>();
		List<Double> nlist = new ArrayList<Double>();
		for (int i = 0; i < x.length; i++) {
			double xval = x[i];
			double yval = y[i];
			if (!Double.isNaN(xval) && !Double.isNaN(yval)) {
				xlist.add(xval);
				ylist.add(yval);
				if (n != null) {
					nlist.add(n[i]);
				}
			}
		}
		
		this.x = ArrayUtils.toPrimitive(xlist.toArray(new Double[0]));
		this.y = ArrayUtils.toPrimitive(ylist.toArray(new Double[0]));
		if (nlist.size() != 0) {
			this.n = ArrayUtils.toPrimitive(nlist.toArray(new Double[0]));
		}
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
