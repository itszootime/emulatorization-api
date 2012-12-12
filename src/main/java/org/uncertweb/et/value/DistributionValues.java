package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DistributionValues implements Values, Iterable<Distribution> {
	
	private List<Distribution> list;
	
	public DistributionValues() {
		list = new ArrayList<Distribution>();
	}
	
	public void add(double mean, double variance) {
		list.add(new Distribution(mean, variance));
	}
	
	public void add(Distribution meanVariance) {
		list.add(meanVariance);
	}
	
	public Distribution get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	@Override
	public Iterator<Distribution> iterator() {
		return list.iterator();
	}
	
	public double[][] toMatrix() {
		double[][] matrix = new double[size()][2];
		for (int i = 0; i < matrix.length; i++) {
			Distribution mv = this.get(i);
			matrix[i][0] = mv.getMean();
			matrix[i][1] = mv.getVariance();
		}
		return matrix;
	}
	
	/**
	 * Each row should contain a mean and variance.
	 * 
	 * @param matrix
	 * @return
	 */
	public static DistributionValues fromMatrix(double[][] matrix) {
		DistributionValues v = new DistributionValues();
		for (int row = 0; row < matrix.length; row++) {
			v.add(new Distribution(matrix[row][0], matrix[row][1]));
		}
		return v;
	}
	
	public static DistributionValues fromArrays(double[] means, double[] variances) {
		DistributionValues v = new DistributionValues();
		for (int i = 0; i < means.length; i++) {
			v.add(new Distribution(means[i], variances[i]));
		}
		return v;
	}
	
}
