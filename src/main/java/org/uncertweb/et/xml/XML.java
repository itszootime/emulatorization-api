package org.uncertweb.et.xml;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.process.ProcessEvaluationResult;

import uk.ac.aston.uncertws.emulatorization.EmulatorDocument;
import uk.ac.aston.uncertws.emulatorization.EmulatorType;
import uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs;

public class XML {

	public <T> T parse(Reader reader, Class<T> type) {
		// TODO: obviously need to handle exceptions, but at the moment we mimic
		// the lack of exceptions in JSON class
		try {
			if (type.equals(Emulator.class)) {
				return type.cast(parseEmulator(reader));
			}
		}
		catch (XmlException e) {
			// ignore
		}
		catch (IOException e) {
			// ignore
		}		
		return null;
	}

	private Emulator parseEmulator(Reader reader) throws XmlException, IOException {
		EmulatorType emulator = EmulatorDocument.Factory.parse(reader).getEmulator();
		List<Input> inputs = parseInputs(emulator.getInputs());
		List<Output> outputs = null;
		Design design = null;
		ProcessEvaluationResult evaluationResult = null;
		String meanFunction = null;
		String covarianceFunction = null;
		double[] lengthScales = new double[0];
		Double nuggetVariance = null;
		return new Emulator(inputs, outputs, design, evaluationResult, meanFunction, covarianceFunction, lengthScales, nuggetVariance);
	}
	
	private List<Input> parseInputs(Inputs inputs) {
		List<Input> parsedInputs = new ArrayList<Input>();
		
		return parsedInputs;
	}

}
