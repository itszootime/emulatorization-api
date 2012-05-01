package org.uncertweb.et.design;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLScalar;

/**
 * A Morris design for a set of variable inputs.
 * 
 * @author Richard Jones
 *
 */
// TODO: need some sort of resrictions on the inputs list here. should it be copied in the constructor to prevent modification? getInputs() should return a copy of the inputs here? this is because the returned matrices rely heavily on the order of this input list
public class MorrisDesign extends Design {
	
	private static final Logger logger = LoggerFactory.getLogger(MorrisDesign.class);

	//private Map<Input, Double[]> ballTrajMap; // not using this
	private Map<String, Double[]> pStarTrajMap;
	private Map<String, Double[]> linearTransformationMap;
	private int r;
	private double delta;
	
	public MorrisDesign(int r, double delta, int size) {
		super(size);	
		
		// create structures and set variables
		//ballTrajMap = new HashMap<Input, Double[]>();
		pStarTrajMap = new HashMap<String, Double[]>();
		linearTransformationMap = new HashMap<String, Double[]>();
		this.r = r;
		this.delta = delta;
	}
	
	public void addPoints(String inputIdentifier, Double[] points, Double[] pStarTraj, Double[] linearTransformation) {
		addPoints(inputIdentifier, points);
		pStarTrajMap.put(inputIdentifier, pStarTraj);
		linearTransformationMap.put(inputIdentifier, linearTransformation);
	}

	public static MorrisDesign create(List<VariableInput> inputs, int r, int p, int deltaP) throws DesignException {
		// create linear transformation
		Double[][] linearTransformation = new Double[inputs.size()][];
		for (int i = 0; i < inputs.size(); i++) {
			linearTransformation[i] = new Double[] { 0d, 1d };
		}
		
		// objects for response
		MLMatrix ballTraj;
		MLMatrix pStarTraj;
		MLScalar delta;
		try {
			// create request
			logger.info("Creating Morris design for " + inputs.size() + " inputs.");
			MLRequest request = new MLRequest("MorrisDesign", 3);
			request.addParameter(new MLScalar(r));
			request.addParameter(new MLScalar(inputs.size()));
			request.addParameter(new MLScalar(p));
			request.addParameter(new MLScalar(deltaP));
			request.addParameter(new MLMatrix(linearTransformation));
			
			// send request
			MLResult result = MATLAB.sendRequest(request);
			ballTraj = result.getResult(0).getAsMatrix();
			pStarTraj = result.getResult(1).getAsMatrix();
			delta = result.getResult(2).getAsScalar();
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
		
		// get matrices
		double[][] ballTrajMatrix = ballTraj.getMatrix();
		double[][] pStarTrajMatrix = pStarTraj.getMatrix();
		
		// create design
		MorrisDesign design = new MorrisDesign(r, delta.getScalar(), ballTrajMatrix.length);
		
		// add to design
		for (int i = 0; i < inputs.size(); i++) {
			// get input
			VariableInput input = inputs.get(i);
			
			// get ballTraj values and rescale
			Double[] ballTrajArray = new Double[ballTrajMatrix.length];
			for (int j = 0; j < ballTrajMatrix.length; j++) {
				// rescale
				double scaled = ((input.getMax() - input.getMin()) * ballTrajMatrix[j][i]) + input.getMin();
				ballTrajArray[j] = scaled;
			}
			
			// get pStarTraj values
			Double[] pStarTrajArray = new Double[pStarTrajMatrix.length];
			for (int j = 0; j < pStarTrajMatrix.length; j++) {
				pStarTrajArray[j] = pStarTrajMatrix[j][i];
			}
			
			// add input
			design.addPoints(input.getIdentifier(), ballTrajArray, pStarTrajArray, linearTransformation[i]);
		}
		
		return design;
	}
	
	public double getDelta() {
		return delta;
	}
	
	public int getR() {
		return r;
	}
	
	public Double[] getLinearTransformation(String inputIdentifier) {
		return linearTransformationMap.get(inputIdentifier);
	}
	
	/**
	 *  same order as inputs
	 * @return
	 */
	public Double[][] getLinearTransformation() {
		Double[][] linearTransformation = new Double[inputIdentifierList.size()][];
		for (int i = 0; i < inputIdentifierList.size(); i++) {
			linearTransformation[i] = getLinearTransformation(inputIdentifierList.get(i));
		}
		return linearTransformation;
	}
	
	public Double[] getPStarTraj(String inputIdentifier) {
		return pStarTrajMap.get(inputIdentifier);
	}
	
	/**
	 *  same order as inputs
	 * @return
	 */
	public Double[][] getPStarTraj() {
		Double[][] pStarTraj = new Double[getR() * inputIdentifierList.size()][];
		for (int i = 0; i < pStarTraj.length; i++) {
			pStarTraj[i] = new Double[inputIdentifierList.size()];
			for (int j = 0; j < inputIdentifierList.size(); j++) {
				pStarTraj[i][j] = getPStarTraj(inputIdentifierList.get(j))[i];
			}
		}
		return pStarTraj;
	}
}
