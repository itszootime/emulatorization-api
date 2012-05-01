package org.uncertweb.et.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessEvaluationResult {

	protected List<String> outputIdentifiers;
	protected Map<String, Double[]> resultsMap;

	public ProcessEvaluationResult() {
		outputIdentifiers = new ArrayList<String>();
		resultsMap = new HashMap<String, Double[]>();
	}

	/**
	 * this list defines the order of returned matrices. if something is removed from here...
	 * 
	 * @return
	 */
	public List<String> getOutputIdentifiers() {
		return outputIdentifiers;
	}

	public void addResults(String outputIdentifier, Double[] results) {
		if (!outputIdentifiers.contains(outputIdentifier)) {
			outputIdentifiers.add(outputIdentifier);
		}
		resultsMap.put(outputIdentifier, results);
	}
	
	public void addResults(String outputIdentifier, double[] results) {
		// TODO: bit inefficient, should check if identifier is present before casting
		Double[] casted = new Double[results.length];
		for (int i = 0; i < results.length; i++) {
			casted[i] = results[i];
		}
		addResults(outputIdentifier, casted);
	}

	public Double[] getResults(String outputIdentifier) {
		return resultsMap.get(outputIdentifier);
	}

	public Double[][] getResults() {
		Double[][] resultsMatrix = new Double[getSize()][];
		for (int i = 0; i < getSize(); i++) {
			resultsMatrix[i] = new Double[outputIdentifiers.size()];
			// get point for each input
			for (int j = 0; j < outputIdentifiers.size(); j++) {
				String outputIdentifier = outputIdentifiers.get(j);
				Double[] results = getResults(outputIdentifier);
				resultsMatrix[i][j] = results[i];
			}
		}
		return resultsMatrix;
	}

	public int getSize() {
		return resultsMap.get(outputIdentifiers.get(0)).length;
	}
	
}
