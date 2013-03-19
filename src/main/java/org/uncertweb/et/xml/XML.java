package org.uncertweb.et.xml;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.parameter.ConstantInput;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.parameter.ParameterDescription;
import org.uncertweb.et.parameter.ParameterDescription.DataType;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.et.process.ProcessEvaluationResult;

import uk.ac.aston.uncertws.emulatorization.DescriptionType;
import uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType;
import uk.ac.aston.uncertws.emulatorization.EmulatorDocument;
import uk.ac.aston.uncertws.emulatorization.EmulatorType;
import uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs;
import uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs;
import uk.ac.aston.uncertws.emulatorization.DesignInputType;
import uk.ac.aston.uncertws.emulatorization.FixedInputType;
import uk.ac.aston.uncertws.emulatorization.OutputType;
import uk.ac.aston.uncertws.emulatorization.VariableInputType;

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
		// main document
		EmulatorType emulator = EmulatorDocument.Factory.parse(reader).getEmulator();
		
		List<Input> inputs = parseInputs(emulator.getInputs());
		List<Output> outputs = parseOutputs(emulator.getOutputs());
		Design design = parseDesign(emulator.getDesign());
		ProcessEvaluationResult evaluationResult = null;
		String meanFunction = null;
		String covarianceFunction = null;
		double[] lengthScales = new double[0];
		Double nuggetVariance = null;
		
		// create and return
		return new Emulator(inputs, outputs, design, evaluationResult, meanFunction, covarianceFunction, lengthScales, nuggetVariance);
	}

	private Design parseDesign(uk.ac.aston.uncertws.emulatorization.EmulatorType.Design design) {
		// get size
		int size = design.getSize().intValue();
		
		// check what sort of design we have
		Design parsedDesign;
		if (design.getDesignInputArray(0).isSetMean()) {
			// normalised
			parsedDesign = new NormalisedDesign(size);
			
			// parse each
			for (DesignInputType di : design.getDesignInputArray()) {
				String identifier = di.getIdentifier();
				Double[] points = parsePointsList(di.getPoints());
				Double mean = di.getMean();
				Double stdDev = di.getStdDev();
				((NormalisedDesign)parsedDesign).addPoints(identifier, points, mean, stdDev);
			}
		}
		else {
			// non-normalised
			parsedDesign = new Design(size);
			
			// parse each
			for (DesignInputType di : design.getDesignInputArray()) {
				String identifier = di.getIdentifier();
				Double[] points = parsePointsList(di.getPoints());
				parsedDesign.addPoints(identifier, points);
			}
		}
		
		return parsedDesign;
	}

	private Double[] parsePointsList(List<?> points) {
		Double[] parsedPoints = new Double[points.size()];
		for (int i = 0; i < points.size(); i++) {
			parsedPoints[i] = (Double)points.get(i);
		}
		return parsedPoints;
	}

	private List<Input> parseInputs(Inputs inputs) {
		List<Input> parsedInputs = new ArrayList<Input>();
		
		// parse fixed
		for (FixedInputType fi : inputs.getFixedInputArray()) {
			ConstantInput input = new ConstantInput(fi.getIdentifier(), fi.getValue());
			ParameterDescription desc = parseParameterDescription(fi.getDescription());
			input.setDescription(desc);
			parsedInputs.add(input);
		}
		
		// parse variable
		for (VariableInputType vi : inputs.getVariableInputArray()) {
			VariableInput input = new VariableInput(vi.getIdentifier(), vi.getMin(), vi.getMax());
			ParameterDescription desc = parseParameterDescription(vi.getDescription());
			input.setDescription(desc);
			parsedInputs.add(input);
		}
		
		return parsedInputs;
	}	
	
	private List<Output> parseOutputs(Outputs outputs) {
		List<Output> parsedOutputs = new ArrayList<Output>();
		for (OutputType o : outputs.getOutputArray()) {
			Output output = new Output(o.getIdentifier());
			ParameterDescription desc = parseParameterDescription(o.getDescription());
			output.setDescription(desc);
			parsedOutputs.add(output);
		}
		return parsedOutputs;
	}

	private ParameterDescription parseParameterDescription(DescriptionType description) {
		// parse required
		DataType dataType = null;
		if (description.getDataType().equals(uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType.NUMERIC)) {
			dataType = DataType.Numeric;
		}
		String encodingType = null;
		if (description.getEncodingType().equals(EncodingType.DOUBLE)) {
			encodingType = "double";
		}
		ParameterDescription pd = new ParameterDescription(dataType, encodingType);
		
		// add optional
		pd.setDetail(description.getDetail());
		pd.setUom(description.getUom());
		return pd;
	}

}
