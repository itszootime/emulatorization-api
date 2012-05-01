package org.uncertweb.et.design;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLArray;
import org.uncertweb.matlab.value.MLScalar;

public class LHSDesign extends Design {
	
	private static final Logger logger = LoggerFactory.getLogger(LHSDesign.class);
	
	private LHSDesign(int size) {
		super(size);
	}

	public static LHSDesign create(List<Input> inputs, int numSamples) throws DesignException {
		if (numSamples > 0) {
			try {
				// filter variable inputs
				List<VariableInput> varInputs = new ArrayList<VariableInput>();
				for (Input input : inputs) {
					if (input.isVariableInput()) {
						varInputs.add(input.getAsVariableInput());
					}
				}
				
				// get min/max arrays for matlab function
				double[] minArray = new double[varInputs.size()];
				double[] maxArray = new double[varInputs.size()];
				for (int i = 0; i < varInputs.size(); i++) {
					minArray[i] = varInputs.get(i).getMin();
					maxArray[i] = varInputs.get(i).getMax();
				}

				// perform lhs with matlab
				logger.info("Creating " + numSamples + " point LHS design for " + varInputs.size() + " variable inputs.");
				MLRequest request = new MLRequest("design", 1);
				request.addParameter(new MLArray(minArray));
				request.addParameter(new MLArray(maxArray));
				request.addParameter(new MLScalar(numSamples));
				MLResult result = MATLAB.sendRequest(request);

				double[][] samples = result.getResult(0).getAsMatrix().getAsMatrix().getMatrix();

				// get result
				LHSDesign design = new LHSDesign(numSamples);
				int i = 0; // design index
				for (Input input : inputs) {
					if (input.isVariableInput()) {
						Double[] points = new Double[numSamples];
						for (int s = 0; s < numSamples; s++) {
							points[s] = samples[s][i];
						}
						design.addPoints(input.getIdentifier(), points);
						i++;						
					}
					else {
						design.addPoint(input.getIdentifier(), input.getAsConstantInput().getValue());
					}
				}
				
				return design;
			}
			catch (MLException e) {
				throw new DesignException("Couldn't create design: " + e.getMessage());
			}
			catch (IOException e) {
				throw new DesignException("Couldn't connect to MATLAB.");
			}
			catch (ConfigException e) {
				throw new DesignException("Couldn't load MATLAB config details.");
			}
		}
		else {
			throw new IllegalArgumentException("Number of samples must be > 0.");
		}
	}

}
