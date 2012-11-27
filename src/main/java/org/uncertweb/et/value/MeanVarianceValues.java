package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeanVarianceValues implements Values, Iterable<MeanVariance> {
	
	private List<MeanVariance> list;
	
	public MeanVarianceValues() {
		list = new ArrayList<MeanVariance>();
	}
	
	public void add(double mean, double variance) {
		list.add(new MeanVariance(mean, variance));
	}
	
	public void add(MeanVariance meanVariance) {
		list.add(meanVariance);
	}
	
	public MeanVariance get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	@Override
	public Iterator<MeanVariance> iterator() {
		return list.iterator();
	}
	
	/**
	 * Each row should contain a mean and variance.
	 * 
	 * @param matrix
	 * @return
	 */
	public static MeanVarianceValues fromMatrix(double[][] matrix) {
		MeanVarianceValues v = new MeanVarianceValues();
		for (int row = 0; row < matrix.length; row++) {
			v.add(new MeanVariance(matrix[row][0], matrix[row][1]));
		}
		return v;
	}
	
	public static MeanVarianceValues fromArrays(double[] means, double[] variances) {
		MeanVarianceValues v = new MeanVarianceValues();
		for (int i = 0; i < means.length; i++) {
			v.add(new MeanVariance(means[i], variances[i]));
		}
		return v;
	}
	
}
