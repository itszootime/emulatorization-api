package org.uncertweb.et.emulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmulatorEvaluationResult {

	protected List<String> outputIdentifiers;
	protected Map<String, Double[]> meanMap;
	protected Map<String, Double[]> covarianceMap;

	public EmulatorEvaluationResult() {
		outputIdentifiers = new ArrayList<String>();
		meanMap = new HashMap<String, Double[]>();
		covarianceMap = new HashMap<String, Double[]>();
	}
	
	/**
	 * this list defines the order of returned matrices. if something is removed from here...
	 * 
	 * @return
	 */
	public List<String> getOutputIdentifiers() {
		return outputIdentifiers;
	}

	public void addResults(String outputIdentifier, Double[] meanResults, Double[] covarianceResults) {
		if (!outputIdentifiers.contains(outputIdentifier)) {
			outputIdentifiers.add(outputIdentifier);
		}
		meanMap.put(outputIdentifier, meanResults);
		covarianceMap.put(outputIdentifier, covarianceResults);
	}
	
	public void addResults(String outputIdentifier, double[] meanResults, double[] covarianceResults) {
		// TODO: bit inefficient, should check if identifier is present before casting
		Double[] meanCasted = new Double[meanResults.length];
		for (int i = 0; i < meanResults.length; i++) {
			meanCasted[i] = meanResults[i];
		}
		Double[] covarianceCasted = new Double[covarianceResults.length];
		for (int i = 0; i < covarianceResults.length; i++) {
			covarianceCasted[i] = covarianceResults[i];
		}
		addResults(outputIdentifier, meanCasted, covarianceCasted);
	}

	public Double[] getMeanResults(String outputIdentifier) {
		return meanMap.get(outputIdentifier);
	}
	
	public Double[] getCovarianceResults(String outputIdentifier) {
		return covarianceMap.get(outputIdentifier);
	}
// not necessary?
	
//	public Double[][] getMeanResults() {
//		Double[][] resultsMatrix = new Double[getSize()][];
//		for (int i = 0; i < getSize(); i++) {
//			resultsMatrix[i] = new Double[outputIdentifiers.size()];
//			// get point for each input
//			for (int j = 0; j < outputIdentifiers.size(); j++) {
//				String outputIdentifier = outputIdentifiers.get(j);
//				Double[] results = getMeanResults(outputIdentifier);
//				resultsMatrix[i][j] = results[i];
//			}
//		}
//		return resultsMatrix;
//	}
//	
	public int getSize() {
		return meanMap.get(outputIdentifiers.get(0)).length;
	}
	
}
