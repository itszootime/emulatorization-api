package org.uncertweb.et.screening;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.MATLAB;
import org.uncertweb.et.design.MorrisDesign;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.matlab.MLException;
import org.uncertweb.matlab.MLRequest;
import org.uncertweb.matlab.MLResult;
import org.uncertweb.matlab.value.MLArray;
import org.uncertweb.matlab.value.MLMatrix;
import org.uncertweb.matlab.value.MLScalar;
import org.uncertweb.matlab.value.MLValue;

/**
 * Morris for first output.
 * 
 * @author Richard Jones
 *
 */
public class Morris {
	
	private static final Logger logger = LoggerFactory.getLogger(Morris.class);

	// inputs
	private MorrisDesign design;
	private ProcessEvaluationResult evaluationResults;

	// outputs
	private List<MorrisOutputResult> morrisResults;

	public Morris(MorrisDesign design, ProcessEvaluationResult evaluationResults) throws ScreeningException {
		this.design = design;
		this.evaluationResults = evaluationResults;

		run();
	}

	private void run() throws ScreeningException {
		// gather morris inputs
		MLScalar r = new MLScalar(design.getR());
		MLMatrix ballTraj = new MLMatrix(design.getPoints());
		MLScalar delta = new MLScalar(design.getDelta());
		MLMatrix linearTransformation = new MLMatrix(design.getLinearTransformation());
		MLMatrix pStarTraj = new MLMatrix(design.getPStarTraj());
		
		// for each output, run morris
		morrisResults = new ArrayList<MorrisOutputResult>();
		for (String outputIdentifier : evaluationResults.getOutputIdentifiers()) {
			// get results for output
			MLArray funcEvalArray = new MLArray(evaluationResults.getResults(outputIdentifier));
			
			/*
			System.out.println(r.toMLString());
			System.out.println(ballTraj.toMLString());
			System.out.println(delta.toMLString());
			System.out.println(linearTransformation.toMLString());
			System.out.println(pStarTraj.toMLString());
			System.out.println(funcEvalArray.toMLString());
			*/
			
			// create morris request
			MLRequest request = new MLRequest("Morris", 4);
			request.addParameter(r);
			request.addParameter(ballTraj);
			request.addParameter(funcEvalArray);
			request.addParameter(delta);
			request.addParameter(linearTransformation);
			request.addParameter(pStarTraj);

			try {
				// send request
				logger.info("Running Morris for output " + outputIdentifier + "...");
				MLResult mlresult = MATLAB.sendRequest(request);

				// if there's only one input these will be scalars
				MLValue meanEEValue = mlresult.getResult(0);
				MLValue meanStarEEValue = mlresult.getResult(1);
				MLValue stdEEValue = mlresult.getResult(2);
				// this will always be a matrix
				MLMatrix ee = mlresult.getResult(3).getAsMatrix();

				// add to results
				List<MorrisInputResult> inputResults = new ArrayList<MorrisInputResult>();
				if (design.getInputIdentifiers().size() == 1) {
					double[][] eeMatrix = ee.getMatrix();
					double[] eeArray = new double[eeMatrix.length];
					for (int j = 0; j < eeArray.length; j++) {
						eeArray[j] = eeMatrix[j][0];
					}
					MorrisInputResult result = new MorrisInputResult(design.getInputIdentifiers().get(0), meanEEValue.getAsScalar().getScalar(), meanStarEEValue.getAsScalar().getScalar(), stdEEValue.getAsScalar().getScalar(), eeArray);
					inputResults.add(result);
				}
				else {
					// cast values
					MLArray meanEE = meanEEValue.getAsArray();
					MLArray meanStarEE = meanStarEEValue.getAsArray();
					MLArray stdEE = stdEEValue.getAsArray();
					
					// ordering of inputs should be guaranteed by MorrisDesign to be the same as the values in the design matrices
					for (int i = 0; i < design.getInputIdentifiers().size(); i++) {
						String inputIdentifier = design.getInputIdentifiers().get(i);
	
						// get ee array
						double[][] eeMatrix = ee.getMatrix();
						double[] eeArray = new double[eeMatrix.length];
						for (int j = 0; j < eeArray.length; j++) {
							eeArray[j] = eeMatrix[j][i];
						}
	
						// create result and add
						MorrisInputResult result = new MorrisInputResult(inputIdentifier, meanEE.getArray()[i], meanStarEE.getArray()[i], stdEE.getArray()[i], eeArray);
						inputResults.add(result);
					}
				}
				morrisResults.add(new MorrisOutputResult(outputIdentifier, inputResults));
			}
			catch (IOException e) {
				throw new ScreeningException("Couldn't connect to MATLAB.");
			}
			catch (MLException e) {
				throw new ScreeningException("Couldn't perform Morris: " + e.getMessage());
			}
			catch (ConfigException e) {
				throw new ScreeningException("Couldn't load MATLAB config details.");
			}			
		}
	}

	public List<MorrisOutputResult> getResults() {
		return morrisResults;
	}	

}
