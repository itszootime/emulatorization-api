package org.uncertweb.et.value;

public class EnsembleValues extends Values<Ensemble> {
	
	/**
	 * Each row should contain an ensemble.
	 * 
	 * @param matrix
	 * @return
	 */
	public static EnsembleValues fromMatrix(double[][] matrix) {
		EnsembleValues v = new EnsembleValues();
		for (int row = 0; row < matrix.length; row++) {
			v.add(new Ensemble(matrix[row]));
		}
		return v;
	}
	
}
