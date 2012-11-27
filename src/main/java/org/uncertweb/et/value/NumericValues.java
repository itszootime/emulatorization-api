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
	
	@Override
	public Iterator<Numeric> iterator() {
		return list.iterator();
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
