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
	}
	
	public void addPoints(String inputIdentifier, Double[] points, Double mean, Double stdDev) {
		if (!inputIdentifierList.contains(inputIdentifier)) {
			inputIdentifierList.add(inputIdentifier);
		}
		map.put(inputIdentifier, new MultiplePoint(points));
		meanMap.put(inputIdentifier, mean);
		stdDevMap.put(inputIdentifier, stdDev);
	}

	private NormalisedDesign(Design original, double[] mean, double[] stdDev) {
		// construct
		super(original.getSize());
		this.meanMap = new HashMap<String, Double>();
		this.stdDevMap = new HashMap<String, Double>();

		// get identifiers
		List<String> identifiers = original.getInputIdentifiers();

		// normalise
		for (int i = 0; i < identifiers.size(); i++) {
			String current = identifiers.get(i);
			Double currentMean = mean[i];
			this.meanMap.put(current, currentMean);
			Double currentStdDev = stdDev[i];
			this.stdDevMap.put(current, currentStdDev);
			Double[] points = original.getPoints(current);
			Double[] normalised = new Double[points.length];
			for (int j = 0; j < normalised.length; j++) {
				normalised[j] = (points[j] - currentMean) / currentStdDev;
			}
			this.addPoints(current, normalised);
		}
	}

	public static NormalisedDesign fromDesign(Design design) {
		List<String> identifiers = design.getInputIdentifiers();

		// compute mean and stddev
		double[] mean = new double[design.getSize()];
		double[] stdDev = new double[design.getSize()];
		for (int i = 0; i < identifiers.size(); i++) {
			DescriptiveStatistics stats = new DescriptiveStatistics(ArrayUtils.toPrimitive(design.getPoints(identifiers.get(i))));
			mean[i] = stats.getMean();
			stdDev[i] = stats.getStandardDeviation();
		}

		// return
		return new NormalisedDesign(design, mean, stdDev);
	}

	public static NormalisedDesign fromDesign(Design design, double[] mean, double[] stdDev) {
		return new NormalisedDesign(design, mean, stdDev);
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
	
	public double[] getMeans() {
		double[] means = new double[inputIdentifierList.size()];
		for (int i = 0; i < inputIdentifierList.size(); i++) {
			means[i] = meanMap.get(inputIdentifierList.get(i));
		}
		return means;
	}
	
	public double[] getStdDevs() {
		double[] stdDevs = new double[inputIdentifierList.size()];
		for (int i = 0; i < inputIdentifierList.size(); i++) {
			stdDevs[i] = stdDevMap.get(inputIdentifierList.get(i));
		}
		return stdDevs;
	}

}
