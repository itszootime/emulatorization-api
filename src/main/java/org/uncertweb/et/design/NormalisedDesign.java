package org.uncertweb.et.design;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

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
		Mean m = new Mean();
		StandardDeviation sd = new StandardDeviation();
		double[] mean = new double[design.getSize()];
		double[] stdDev = new double[design.getSize()];
		for (int i = 0; i < identifiers.size(); i++) {
			double[] primitive = ArrayUtils.toPrimitive(design.getPoints(identifiers.get(i)));
			mean[i] = m.evaluate(primitive);
			stdDev[i] = sd.evaluate(primitive);
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

}
