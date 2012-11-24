package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Values<T extends Value> implements Iterable<T> {
	
	// each value can have an identifier?
	
	private List<T> list;
	
	private Values() {
		list = new ArrayList<T>();
	}
	
	/**
	 * Each row should contain an ensemble.
	 * 
	 * @param matrix
	 * @return
	 */
	public static Values<Ensemble> fromEnsembleMatrix(double[][] matrix) {
		Values<Ensemble> v = new Values<Ensemble>();
		for (int row = 0; row < matrix.length; row++) {
			v.add(new Ensemble(matrix[row]));
		}
		return v;
	}
	
	public void add(T value) {
		list.add(value);
	}
	
	public T get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}
	
	public List<T> getValues() {
		return list;
	}
	
	public static double[] toNumericArray(Values<Numeric> values) {
		double[] array = new double[values.size()];
		for (int i = 0; i < array.length; i++) {
			Numeric n = values.get(i);
			array[i] = n.getNumber();
		}
		return array;
	}
	
	/**
	 * Each row should contain a mean and covariance.
	 * 
	 * @param matrix
	 * @return
	 */
	public static Values<MeanCovariance> fromMeanCovarianceMatrix(double[][] matrix) {
		Values<MeanCovariance> v = new Values<MeanCovariance>();
		for (int row = 0; row < matrix.length; row++) {
			v.add(new MeanCovariance(matrix[row][0], matrix[row][1]));
		}
		return v;
	}
	
	/**
	 * Standard numeric values.
	 * 
	 * @param array
	 * @return
	 */
	public static Values<Numeric> fromNumericArray(double[] array) {
		Values<Numeric> v = new Values<Numeric>();
		for (int i = 0; i < array.length; i++) {
			v.add(new Numeric(array[i]));
		}
		return v;
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

}
