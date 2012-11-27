package org.uncertweb.et.value;

public class NumericValues extends Values<Numeric> {

	public void add(double number) {
		add(new Numeric(number));
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
			Numeric n = get(i);
			array[i] = n.getNumber();
		}
		return array;
	}
	
}
