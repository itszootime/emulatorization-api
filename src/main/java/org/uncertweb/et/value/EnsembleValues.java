package org.uncertweb.et.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnsembleValues implements Values, Iterable<Ensemble> {
	
	private List<Ensemble> list;
	
	public EnsembleValues() {
		list = new ArrayList<Ensemble>();
	}
	
	public void add(double[] members) {
		list.add(new Ensemble(members));
	}
	
	public void add(Ensemble ensemble) {
		list.add(ensemble);
	}
	
	public Ensemble get(int index) {
		return list.get(index);
	}
	
	@Override
	public Iterator<Ensemble> iterator() {
		return list.iterator();
	}
	
	public int size() {
		return list.size();
	}
	
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
