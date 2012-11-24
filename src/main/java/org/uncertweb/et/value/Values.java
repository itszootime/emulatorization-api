package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Values<T extends Value> implements Iterable<T> {
	
	// each value can have an identifier?
	
	private List<T> values;
	
	private Values() {
		values = new ArrayList<T>();
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
		values.add(value);
	}
	
	public List<T> getValues() {
		return values;
	}
	
	/**
	 * Each row should contain a mean and variance.
	 * 
	 * @param matrix
	 * @return
	 */
	public static Values<MeanVariance> fromMeanVarianceMatrix(double[][] matrix) {
		Values<MeanVariance> v = new Values<MeanVariance>();
		for (int row = 0; row < matrix.length; row++) {
			v.add(new MeanVariance(matrix[row][0], matrix[row][1]));
		}
		return v;
	}
	
	/**
	 * Standard numeric values.
	 * 
	 * @param array
	 * @return
	 */
	public static Values<Numeric> fromArray(double[] array) {
		Values<Numeric> v = new Values<Numeric>();
		for (int i = 0; i < array.length; i++) {
			v.add(new Numeric(array[i]));
		}
		return v;
	}

	@Override
	public Iterator<T> iterator() {
		return values.iterator();
	}

}
