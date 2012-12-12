package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SampleValues implements Values, Iterable<Sample> {
	
	private List<Sample> list;
	
	public SampleValues() {
		list = new ArrayList<Sample>();
	}
	
	public void add(double[] members) {
		list.add(new Sample(members));
	}
	
	public void add(Sample ensemble) {
		list.add(ensemble);
	}
	
	public Sample get(int index) {
		return list.get(index);
	}
	
	@Override
	public Iterator<Sample> iterator() {
		return list.iterator();
	}
	
	public int size() {
		return list.size();
	}
	
	public double[][] toMatrix() {
		double[][] matrix = new double[size()][];
		for (int i = 0; i < matrix.length; i++) {
			Sample e = get(i);
			matrix[i] = e.getMembers();
		}
		return matrix;
	}
	
	/**
	 * Each row should contain an ensemble.
	 * 
	 * @param matrix
	 * @return
	 */
	public static SampleValues fromMatrix(double[][] matrix) {
		SampleValues v = new SampleValues();
		for (int row = 0; row < matrix.length; row++) {
			v.add(new Sample(matrix[row]));
		}
		return v;
	}
	
}
