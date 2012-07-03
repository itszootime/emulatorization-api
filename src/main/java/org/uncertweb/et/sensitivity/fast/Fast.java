package org.uncertweb.et.sensitivity.fast;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.uncertweb.et.ConfigException;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.DesignException;
import org.uncertweb.et.design.LHSDesign;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.emulator.EmulatorEvaluationResult;
import org.uncertweb.et.emulator.EmulatorEvaluator;
import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluator;
import org.uncertweb.et.process.ProcessEvaluatorException;
import org.uncertweb.et.sensitivity.AnalysisInputResult;
import org.uncertweb.et.sensitivity.AnalysisOutputResult;
import org.uncertweb.et.sensitivity.sobol.SobolException;
import org.uncertweb.et.sensitivity.sobol.SobolRunner;

// FIXME: lots of repeated code from Sobol
public class Fast {

	public static List<AnalysisOutputResult> run(List<Input> inputs, List<Output> outputs, String serviceURL, String processIdentifier, boolean plot, int sampleSize) throws DesignException, ProcessEvaluatorException, FastException {
		// filter variable/fixed inputs
		List<VariableInput> variableInputs = new ArrayList<VariableInput>();
		for (Input input : inputs) {
			if (input instanceof VariableInput) {
				variableInputs.add((VariableInput)input);
			}
		}
		
		FastRunner runner = null;
		try {
			// create a fast runner
			runner = new FastRunner(variableInputs, sampleSize);

			// get design
			Design design = runner.getX();

			// run simulator using design
			ProcessEvaluationResult processResult = ProcessEvaluator.evaluate(serviceURL, processIdentifier, inputs, outputs, design);

			// generate results
			List<AnalysisOutputResult> results = new ArrayList<AnalysisOutputResult>();
			for (Output output : outputs) {
				try {
					// set y, triggers sa
					runner.setY(processResult, output.getIdentifier());
	
					// get sa results
					results.add(runner.getResult(plot));
				}
				catch (REngineException e) {
					// FIXME: repeated below, plus useful for all R interaction
					String message = (runner != null ? runner.getLastError() : null);
					if (message == null) {
						message = "Problem evaluating R expression.";
					}
					else {
						message = "From R: " + message;
					}
					// very hacky
			        BufferedImage img = new BufferedImage(500, 100, BufferedImage.TYPE_INT_ARGB);
			        Graphics2D g2d = img.createGraphics();
			        g2d.setPaint(Color.red);
			        g2d.setFont(new Font("Serif", Font.BOLD, 20));
			        FontMetrics fm = g2d.getFontMetrics();
			        int x = 5;
			        int y = fm.getHeight() + 5;
			        g2d.drawString(message, x, y);
			        g2d.dispose();
					results.add(new AnalysisOutputResult(output.getIdentifier(), new ArrayList<AnalysisInputResult>(), img));
					
					//throw new FastException(message);
				}
			}
			
			return results;
		}
		catch (REngineException e) {
			String message = (runner != null ? runner.getLastError() : null);
			if (message == null) {
				message = "Problem evaluating R expression.";
			}
			else {
				message = "From R: " + message;
			}
			throw new FastException(message);
		}
		catch (REXPMismatchException e) {
			throw new FastException("Error receiving data from R: " + e.getMessage());
		}
		catch (ConfigException e) {
			throw new FastException("Couldn't load Rserve config details.");
		}
		finally {
			runner.done();
		}
	}

	public static List<AnalysisOutputResult> run(Emulator emulator, boolean plot, int sampleSize) throws DesignException, EmulatorEvaluatorException, FastException {
		// strip fixed inputs, even though they will be during the evaluation stage anyway
		List<VariableInput> variableInputs = new ArrayList<VariableInput>();
		for (Input input : emulator.getInputs()) {
			if (input instanceof VariableInput) {
				variableInputs.add((VariableInput)input);
			}
		}
		
		FastRunner r = null;
		try {
			// create a fast runner
			r = new FastRunner(variableInputs, sampleSize);

			// get design
			Design design = r.getX();

			// run emulator using design
			EmulatorEvaluationResult emulatorResult = EmulatorEvaluator.run(emulator, design);

			// tell sobol results
			// emulators only have one output for now
			r.setY(emulatorResult);

			// get sa results
			AnalysisOutputResult result = r.getResult(plot);
			return Arrays.asList(new AnalysisOutputResult[] { result });
		}
		catch (REngineException e) {
			String message = (r != null ? r.getLastError() : null);
			if (message == null) {
				message = "Problem evaluating R expression.";
			}
			else {
				message = "From R: " + message;
			}
			throw new FastException(message);
		}
		catch (REXPMismatchException e) {
			throw new FastException("Error receiving data from R: " + e.getMessage());
		}
		catch (ConfigException e) {
			throw new FastException("Couldn't load Rserve config details.");
		}
		finally {
			r.done();
		}
	}
	
}
