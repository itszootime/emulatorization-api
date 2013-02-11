package org.uncertweb.et.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.uncertweb.et.design.NormalisedDesign;

/**
 * ridiculous code duplication from {@link NormalisedDesign}
 * 
 * @author r_jones
 *
 */
public class NormalisedProcessEvaluationResult extends ProcessEvaluationResult {

	private Map<String, Double> meanMap;
	private Map<String, Double> stdDevMap;
	
	public NormalisedProcessEvaluationResult() {
		super();
		this.meanMap = new HashMap<String, Double>();
		this.stdDevMap = new HashMap<String, Double>();
	}
	
	public void addResults(String outputIdentifier, Double[] results, Double mean, Double stdDev) {
		if (!outputIdentifiers.contains(outputIdentifier)) {
			outputIdentifiers.add(outputIdentifier);
		}
		addResults(outputIdentifier, results);
		meanMap.put(outputIdentifier, mean);
		stdDevMap.put(outputIdentifier, stdDev);
	}

	private NormalisedProcessEvaluationResult(ProcessEvaluationResult original, Map<String, Double> means, Map<String, Double> stdDevs) {
		// construct
		this();

		// get identifiers
		List<String> identifiers = original.getOutputIdentifiers();

		// normalise
		for (int i = 0; i < identifiers.size(); i++) {
			// current input identifier
			String currentIdentifier = identifiers.get(i);
			
			// get mean and std dev, store in map
			Double currentMean = means.get(currentIdentifier);
			Double currentStdDev = stdDevs.get(currentIdentifier);
			this.meanMap.put(currentIdentifier, currentMean);			
			this.stdDevMap.put(currentIdentifier, currentStdDev);
			
			// normalise and add
			Double[] results = original.getResults(currentIdentifier);
			Double[] normalised = new Double[results.length];
			for (int j = 0; j < normalised.length; j++) {
				normalised[j] = (results[j] - currentMean) / currentStdDev;
			}
			this.addResults(currentIdentifier, normalised);
		}
	}

	public static NormalisedProcessEvaluationResult fromProcessEvaluationResult(ProcessEvaluationResult result) {
		List<String> identifiers = result.getOutputIdentifiers();

		// compute mean and stddev
		Map<String, Double> means = new HashMap<String, Double>();
		Map<String, Double> stdDevs = new HashMap<String, Double>();
		for (int i = 0; i < identifiers.size(); i++) {
			String identifier = identifiers.get(i);
			DescriptiveStatistics stats = new DescriptiveStatistics(ArrayUtils.toPrimitive(result.getResults(identifiers.get(i))));
			means.put(identifier, stats.getMean());
			stdDevs.put(identifier, stats.getStandardDeviation());
		}

		// return
		return new NormalisedProcessEvaluationResult(result, means, stdDevs);
	}

	public static NormalisedProcessEvaluationResult fromProcessEvaluationResult(ProcessEvaluationResult result, Map<String, Double> means, Map<String, Double> stdDevs) {
		return new NormalisedProcessEvaluationResult(result, means, stdDevs);
	}

	public double getMean(String outputIdentifier) {
		return meanMap.get(outputIdentifier);
	}

	public double getStdDev(String outputIdentifier) {
		return stdDevMap.get(outputIdentifier);
	}

	public ProcessEvaluationResult unnormalise() {
		// create design
		ProcessEvaluationResult result = new ProcessEvaluationResult();

		// get identifiers
		List<String> identifiers = this.getOutputIdentifiers();

		// unnormalise each
		for (int i = 0; i < identifiers.size(); i++) {
			String current = identifiers.get(i);
			Double currentMean = this.meanMap.get(current);
			Double currentStdDev = this.stdDevMap.get(current);
			Double[] normalised = this.getResults(current);
			Double[] results = new Double[normalised.length];
			for (int j = 0; j < results.length; j++) {
				results[j] = normalised[j] * currentStdDev + currentMean;
			}
			result.addResults(current, results);
		}

		return result;
	}
	
	public Map<String, Double> getMeans() {
		return meanMap;
	}
	
	public Map<String, Double> getStdDevs() {
		return stdDevMap;
	}

}
