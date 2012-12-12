package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class NumericValues implements Values, Iterable<Numeric> {

	private List<Numeric> list;
	
	public NumericValues() {
		list = new ArrayList<Numeric>();
	}
	
	public void add(double number) {
		list.add(new Numeric(number));
	}
	
	public void add(Numeric numeric) {
		list.add(numeric);
	}
	
	public Numeric get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	public double[][] toMatrix() {
		double variance = getVariance();
		double[][] matrix = new double[size()][2];
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][0] = get(i).getNumber();
			matrix[i][1] = variance;
		}
		return matrix;
	}
	
	@Override
	public Iterator<Numeric> iterator() {
		return list.iterator();
	}
	
	public double getStandardDeviation() {
		return Math.sqrt(getVariance());
	}
	
	public double getVariance() {
		double mean = getMean();
		double var = 0.0;
		for (Numeric v : this) {
			var += Math.pow(v.getNumber() - mean, 2);
		}
		return var / (size() - 1);
	}
	
	public double getMean() {
		double sum = 0.0;
		for (Numeric v : this) {
			sum += v.getNumber();
		}
		return sum / size();
	}
	
	/**
	 * Standard numeric values.
	 * 
	 * @param array
	 * @return
	 */
	public static NumericValues fromArray(double[] array) {
		NumericValues v = new NumericValues();
		for (int i = 0; i < array.length; i++) {
			v.add(new Numeric(array[i]));
		}
		return v;
	}	
	
	public double[] toArray() {
		double[] array = new double[size()];
		for (int i = 0; i < size(); i++) {
			Numeric numeric = get(i);
			array[i] = numeric.getNumber();
		}
		return array;
	}
	
}
