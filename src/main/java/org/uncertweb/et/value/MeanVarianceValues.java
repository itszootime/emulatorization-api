package org.uncertweb.et.value;

public class MeanVarianceValues extends Values<MeanVariance> {
	
	public void add(double mean, double variance) {
		add(new MeanVariance(mean, variance));
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
