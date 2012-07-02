package org.uncertweb.et.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class SimulatorExample {
	
	public static final List<Input> INPUTS = Arrays.asList(new Input[] {
		new VariableInput("Rainfall", 0.0, 1.0),
		new VariableInput("MeanAirTemperature", 0.0, 1.0),
		new VariableInput("MaxAirTemperation", 0.0, 1.0),
		new VariableInput("MinAirTemperation", 0.0, 1.0),
		new VariableInput("MeanSoilN", 0.0, 1.0),
		new VariableInput("MaxSoilN", 0.0, 1.0),
		new VariableInput("MinSoilN", 0.0, 1.0),
		new VariableInput("MeanSoilC", 0.0, 1.0),
		new VariableInput("MaxSoilC", 0.0, 1.0),
		new VariableInput("MinSoilC", 0.0, 1.0),
		new VariableInput("MeanSoilTemperature", 0.0, 1.0),
		new VariableInput("MaxSoilTemperation", 0.0, 1.0),
		new VariableInput("MinSoilTemperation", 0.0, 1.0),
		new VariableInput("MeanSoilWettingRate", 0.0, 1.0),
		new VariableInput("MaxSoilWettingRate",  0.0, 1.0),
		new VariableInput("MinSoilWettingRate", 0.0, 1.0),
		new VariableInput("SoilWormDensity", 0.0, 1.0),
		new VariableInput("SurfaceAlbedo", 0.0, 1.0),
		new VariableInput("WheatSystolicPressure", 0.0, 1.0),
		new VariableInput("Evapotranspiration", 0.0, 1.0),
	});
	
	public static final List<Output> OUTPUTS = Arrays.asList(new Output[] {
		new Output("WheatGrowthRate"),
		new Output("FinalYield"),
		new Output("LargestWheatKernelSize")
	});
	
	public static String[] getInputIdentifiers() {
		String[] identifiers = new String[INPUTS.size()];
		for (int i = 0; i < INPUTS.size(); i++) {
			identifiers[i] = INPUTS.get(i).getIdentifier();
		}
		return identifiers;
	}
	
	public static String[] getOutputIdentifiers() {
		String[] identifiers = new String[OUTPUTS.size()];
		for (int i = 0; i < OUTPUTS.size(); i++) {
			identifiers[i] = OUTPUTS.get(i).getIdentifier();
		}
		return identifiers;
	}
	
	public static ProcessEvaluationResult evaluate(Design design) {
		Double[][] points = design.getPoints();
		
		// setup result
		ProcessEvaluationResult result = new ProcessEvaluationResult();
		List<Double> y1 = new ArrayList<Double>();
		List<Double> y2 = new ArrayList<Double>();
		List<Double> y3 = new ArrayList<Double>();
		
		for (Double[] x : points) {
			y1.add(5 * x[11] / (1 + x[0]) + 5 * Math.pow(x[3] - x[19], 2) + x[4] + 40 * Math.pow(x[18], 3) -
				5 * x[18] + 0.05 * x[1] + 0.08 * x[2] - 0.03 * x[5] + 0.03 * x[6] - 0.09 * x[8] - 0.01 *
				x[9] - 0.07 * x[10] + 0.25 * Math.pow(x[12], 2) - 0.04 * x[13] + 0.06 * x[14] - 0.01 * 
				x[16] - 0.03 * x[17]);			
			y2.add(2 * x[11] / (1 + x[0]) + 5 * Math.pow(x[3] - x[19], 2) + x[4] + 40 * Math.pow(x[18], 4) - 
				5 * x[18] + 0.05 * x[1] + 0.08 * x[2] - 0.03 * x[5] + 0.03 * x[6] - 0.09 * x[8] - 0.01 * x[9] - 
				0.07 * x[10] + 0.25 * Math.pow(x[12], 2) - 0.04 * x[13] + 0.06 * x[14] - 0.01 * x[16] - 0.03 * 
				x[17]);
			y3.add(4 * x[11] / (1 + x[0]) + 2 * Math.pow(x[3] - x[19], 2) + x[4] + 40 * x[18] - 5 * x[18] +
				0.05 * x[1] + 0.08 * x[2] - 0.03 * x[5] + 0.03 * x[6] - 0.09 * x[8] - 0.01 * x[9] - 0.07 * 
				x[10] + 0.25 * Math.pow(x[12], 3) - 0.04 * x[13] + 0.01 * x[14] - 0.01 * x[16] - 0.03 * x[17]);
		}
		
		// add results
		result.addResults("WheatGrowthRate", y1.toArray(new Double[0]));
		result.addResults("FinalYield", y2.toArray(new Double[0]));
		result.addResults("LargestWheatKernelSize", y3.toArray(new Double[0]));
		
		return result;
	}

}
