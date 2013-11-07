package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class ScalarValues implements Values, Iterable<Scalar> {

	private List<Scalar> list;

	public ScalarValues() {
		list = new ArrayList<Scalar>();
	}

	public void add(double number) {
		list.add(new Scalar(number));
	}

	public void add(Scalar numeric) {
		list.add(numeric);
	}

	public Scalar get(int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}

	public double[][] toMatrix() {
		double variance = getVariance();
		double[][] matrix = new double[size()][2];
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][0] = get(i).getScalar();
			matrix[i][1] = variance;
		}
		return matrix;
	}

	@Override
	public Iterator<Scalar> iterator() {
		return list.iterator();
	}

	public double getStandardDeviation() {
		return Math.sqrt(getVariance());
	}

	public double getVariance() {
		double mean = getMean();
		double var = 0.0;
		for (Scalar v : this) {
			var += Math.pow(v.getScalar() - mean, 2);
		}
		return var / (size() - 1);
	}

	public double getMean() {
		double sum = 0.0;
		for (Scalar v : this) {
			sum += v.getScalar();
		}
		return sum / size();
	}

	/**
	 * Standard numeric values.
	 *
	 * @param array
	 * @return
	 */
	public static ScalarValues fromArray(double[] array) {
		ScalarValues v = new ScalarValues();
		for (int i = 0; i < array.length; i++) {
			v.add(new Scalar(array[i]));
		}
		return v;
	}

	public double[] toArray() {
		double[] array = new double[size()];
		for (int i = 0; i < size(); i++) {
			Scalar numeric = get(i);
			array[i] = numeric.getScalar();
		}
		return array;
	}

    /**
     * Returns a primitive array of this instance that has been
     * shuffled using the Knuth-Fisher-Yates algorithm
     * @return
     */
    public double[] toShuffledArray() {
        double[] array = toArray();
        Random rng = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int rand = rng.nextInt(i + 1);
            // swap
            double tmp = array[rand];
            array[rand] = array[i];
            array[i] = tmp;
        }
        return array;
    }

}
