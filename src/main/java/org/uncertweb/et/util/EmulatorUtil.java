package org.uncertweb.et.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.uncertweb.et.design.Design;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.value.MLArray;
import org.uncertweb.matlab.value.MLCell;
import org.uncertweb.matlab.value.MLString;
import org.uncertweb.matlab.value.MLValue;

public class EmulatorUtil {
	
	public static void addCovarianceFunction(MLRequest request, Design x, String covarianceFunction,
		double lengthScales[], Double nuggetVariance) {
		MLValue covfname;
		List<Double> covfparList = new ArrayList<Double>();
		
		if (covarianceFunction.equals("squared_exponential")) {
			covfname = new MLString("covSEardUnit");
		}
		else {
			covfname = new MLString("covMatern3ardUnit");
		}		
		
		for (int i = 0 ; i < lengthScales.length; i++) {
			covfparList.add(lengthScales[i]);
		}
		
		if (nuggetVariance != null) {
			covfname = new MLCell(new MLValue[] {
				new MLString("covSum"),
				new MLCell(new MLValue[] { covfname, new MLString("covNoise") }) });
			covfparList.add(Math.sqrt(nuggetVariance));
		}

		Double[] covfpar = covfparList.toArray(new Double[0]);

		request.addParameter(covfname);		
		request.addParameter(new MLArray(covfpar));
	}

	public static void addCovarianceFunction(MLRequest request, Design x, String covarianceFunction,
		double lengthScaleMultiplier, Double nuggetVariance) {
		// calculate length scales
		StandardDeviation sd = new StandardDeviation();
		double[] lengthScales = new double[x.getInputIdentifiers().size()];
		for (int i = 0 ; i < lengthScales.length; i++) {
			String inputIdentifier = x.getInputIdentifiers().get(i);
			lengthScales[i] = lengthScaleMultiplier * sd.evaluate(ArrayUtils.toPrimitive(x.getPoints(inputIdentifier)));
		}
		
		addCovarianceFunction(request, x, covarianceFunction, lengthScales, nuggetVariance);
	}
	
	public static void addMeanFunction(MLRequest request, String meanFunction) {
		String meanfname;
		double[] meanfpar;
		if (meanFunction.equals("zero")) {
			meanfname = "";
			meanfpar = new double[] {};
		}
		else {
			meanfname = "mean_poly";
			meanfpar = new double[1];
			if (meanFunction.equals("constant")) {
				meanfpar[0] = 0;
			}
			else if (meanFunction.equals("linear")) {
				meanfpar[0] = 1;
			}
			else {
				meanfpar[0] = 2;
			}
		}
		request.addParameter(new MLString(meanfname));
		request.addParameter(new MLArray(meanfpar));
	}
	
}
