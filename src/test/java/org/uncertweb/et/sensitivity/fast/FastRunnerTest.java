package org.uncertweb.et.sensitivity.fast;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.BeforeClass;
import org.junit.Test;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RserveException;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.model.SimulatorExample;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.sensitivity.AnalysisOutputResult;

public class FastRunnerTest {
	private static List<VariableInput> inputs;
	private static int sampleSize;
	private static String outputIdentifier;
	private static FastRunner runner;

	@BeforeClass
	public static void setUp() throws REngineException, REXPMismatchException, ConfigException {
		// set inputs
		inputs = new ArrayList<VariableInput>();
		for (Input input : SimulatorExample.INPUTS) {
			// they should all be variable
			inputs.add((VariableInput)input);
		}
		
		// set sample size
		sampleSize = 100;
		
		// set output
		outputIdentifier = SimulatorExample.OUTPUTS.get(0).getIdentifier();
		
		// construct
		runner = new FastRunner(inputs, sampleSize);
	}

	@Test
	public void getX() {		
		// check values
		Design x = runner.getX();
		assertEquals(sampleSize * inputs.size(), x.getSize());
		for (VariableInput input : inputs) {
			Double[] points = x.getPoints(input.getIdentifier());
			for (Double point : points) {
				assertThat(input.getMin(), is(lessThanOrEqualTo(point)));
				assertThat(input.getMax(), is(greaterThanOrEqualTo(point)));
			}
		}
	}
	
	@Test
	public void setY() throws REngineException {
		// evaluate simulator
		ProcessEvaluationResult result = SimulatorExample.evaluate(runner.getX());
		
		// set
		runner.setY(result.getResults(outputIdentifier));
	}
	
	@Test
	public void getResult() throws RserveException, REXPMismatchException {
		// get result
		AnalysisOutputResult result = runner.getResult(true);
	}
}
