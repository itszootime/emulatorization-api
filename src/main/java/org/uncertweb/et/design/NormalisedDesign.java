package org.uncertweb.et.design;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

public class NormalisedDesign extends Design {

	private Map<String, Double> meanMap;
	private Map<String, Double> stdDevMap;
	
	public NormalisedDesign(int size) {
		super(size);
		this.meanMap = new HashMap<String, Double>();
		this.stdDevMap = new HashMap<String, Double>();
	}
	
	public void addPoints(String inputIdentifier, Double[] points, Double mean, Double stdDev) {
		if (!inputIdentifierList.contains(inputIdentifier)) {
			inputIdentifierList.add(inputIdentifier);
		}
		map.put(inputIdentifier, new MultiplePoint(points));
		meanMap.put(inputIdentifier, mean);
		stdDevMap.put(inputIdentifier, stdDev);
	}

	private NormalisedDesign(Design original, Map<String, Double> means, Map<String, Double> stdDevs) {
		// construct
		this(original.getSize());

		// get identifiers
		List<String> identifiers = original.getInputIdentifiers();

		// normalise
		for (int i = 0; i < identifiers.size(); i++) {
			// current input identifier
			String currentIdentifier = identifiers.get(i);
			
			// get mean and std dev, store in map
			Double currentMean = means.get(currentIdentifier);
			Double currentStdDev = stdDevs.get(currentIdentifier);
			this.meanMap.put(currentIdentifier, currentMean);			
			this.stdDevMap.put(currentIdentifier, currentStdDev);
			
			// get points
			Double[] points = original.getPoints(currentIdentifier);
			
			// normalise and add
			Double[] normalised = new Double[points.length];
			for (int j = 0; j < normalised.length; j++) {
				normalised[j] = (points[j] - currentMean) / currentStdDev;
			}
			this.addPoints(currentIdentifier, normalised);
		}
	}

	public static NormalisedDesign fromDesign(Design design) {
		List<String> identifiers = design.getInputIdentifiers();

		// compute mean and stddev
		Map<String, Double> means = new HashMap<String, Double>();
		Map<String, Double> stdDevs = new HashMap<String, Double>();
		for (int i = 0; i < identifiers.size(); i++) {
			String identifier = identifiers.get(i);
			DescriptiveStatistics stats = new DescriptiveStatistics(ArrayUtils.toPrimitive(design.getPoints(identifiers.get(i))));
			means.put(identifier, stats.getMean());
			stdDevs.put(identifier, stats.getStandardDeviation());
		}

		// return
		return new NormalisedDesign(design, means, stdDevs);
	}

	public static NormalisedDesign fromDesign(Design design, Map<String, Double> means, Map<String, Double> stdDevs) {
		return new NormalisedDesign(design, means, stdDevs);
	}

	public double getMean(String inputIdentifier) {
		return meanMap.get(inputIdentifier);
	}

	public double getStdDev(String inputIdentifier) {
		return stdDevMap.get(inputIdentifier);
	}

	public Design unnormalise() {
		// create design
		Design design = new Design(this.getSize());

		// get identifiers
		List<String> identifiers = this.getInputIdentifiers();

		// unnormalise each
		for (int i = 0; i < identifiers.size(); i++) {
			String current = identifiers.get(i);
			Double currentMean = this.meanMap.get(current);
			Double currentStdDev = this.stdDevMap.get(current);
			Double[] normalised = this.getPoints(current);
			Double[] points = new Double[normalised.length];
			for (int j = 0; j < points.length; j++) {
				points[j] = normalised[j] * currentStdDev + currentMean;
			}
			design.addPoints(current, points);
		}

		return design;
	}
	
	public Map<String, Double> getMeans() {
		return meanMap;
	}
	
	public Map<String, Double> getStdDevs() {
		return stdDevMap;
	}

}
