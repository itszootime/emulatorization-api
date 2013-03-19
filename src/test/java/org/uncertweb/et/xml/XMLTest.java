package org.uncertweb.et.xml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.design.NormalisedDesign;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.parameter.ConstantInput;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.parameter.ParameterDescription;
import org.uncertweb.et.parameter.ParameterDescription.DataType;
import org.uncertweb.et.parameter.VariableInput;
import org.uncertweb.et.process.NormalisedProcessEvaluationResult;
import org.uncertweb.et.process.ProcessEvaluationResult;

public class XMLTest {

	private XML xml;
	private Emulator parsedEmulator;
	
	@Before
	public void before() {
		xml = new XML();
		parsedEmulator = parseEmulator();
	}
	
	@Test
	public void parseEmulatorNotNull() {
		assertThat(parsedEmulator, notNullValue());
	}
	
	@Test
	public void parseEmulatorInputCount() {
		List<Input> inputs = parsedEmulator.getInputs();
		assertThat(inputs.size(), equalTo(2));
	}
	
	@Test
	public void parseEmulatorInput() {
		List<Input> inputs = parsedEmulator.getInputs();
		for (Input input : inputs) {
			if (input.getIdentifier().equals("A")) {
				ParameterDescription desc = input.getDescription();
				assertThat(desc.getDataType(), equalTo(DataType.Numeric));
				assertThat(desc.getEncodingType(), equalTo("double"));
				break;
			}
		}
	}
	
	@Test
	public void parseEmulatorInputVariable() {
		List<Input> inputs = parsedEmulator.getInputs();
		for (Input input : inputs) {
			if (input.getIdentifier().equals("A")) {
				assertThat(input, instanceOf(VariableInput.class));
				VariableInput variableInput = (VariableInput)input;
				assertThat(variableInput.getMin(), equalTo(100.0));
				assertThat(variableInput.getMax(), equalTo(1000.0));
				break;
			}
		}
	}
	
	@Test
	public void parseEmulatorInputFixed() {
		List<Input> inputs = parsedEmulator.getInputs();
		for (Input input : inputs) {
			if (input.getIdentifier().equals("B")) {
				assertThat(input, instanceOf(ConstantInput.class));
				ConstantInput fixedInput = (ConstantInput)input;
				assertThat(fixedInput.getValue(), equalTo(10.0));
				break;
			}
		}
	}
	
	@Test
	public void parseEmulatorOutputCount() {
		List<Output> outputs = parsedEmulator.getOutputs();
		assertThat(outputs.size(), equalTo(1));
	}
	
	@Test
	public void parseEmulatorOutput() {
		List<Output> outputs = parsedEmulator.getOutputs();
		for (Output output : outputs) {
			if (output.getIdentifier().equals("Result")) {
				ParameterDescription desc = output.getDescription();
				assertThat(desc.getDataType(), equalTo(DataType.Numeric));
				assertThat(desc.getEncodingType(), equalTo("double"));
				break;
			}
		}
	}
	
	@Test
	public void parseDesignNotNull() {
		assertThat(parsedEmulator.getDesign(), notNullValue());
	}
	
	@Test
	public void parseDesignSize() {
		Design design = parsedEmulator.getDesign();
		assertThat(design.getSize(), equalTo(19));
	}
	
	@Test
	public void parseDesignInputs() {
		Design design = parsedEmulator.getDesign();
		List<String> identifiers = design.getInputIdentifiers();
		assertThat(identifiers.size(), equalTo(2));
	}
	
	@Test
	public void parseDesignPoints() {
		Design design = parsedEmulator.getDesign();
		Double[] points = design.getPoints("A");
		assertThat(points.length, equalTo(19));
		assertThat(points[0], equalTo(0.044105134037608434));
		assertThat(points[18], equalTo(0.676766681540267));
	}
	
	@Test
	public void parseDesignNormalised() {
		Design design = parsedEmulator.getDesign();
		assertThat(design, instanceOf(NormalisedDesign.class));
		NormalisedDesign norm = (NormalisedDesign)design;
		assertThat(norm.getMean("A"), equalTo(609.2421260105268));
		assertThat(norm.getStdDev("A"), equalTo(255.94192335091222));
	}
	
	@Test
	public void parseEvalResultNotNull() {
		ProcessEvaluationResult result = parsedEmulator.getEvaluationResult();
		assertThat(result, notNullValue());
	}
	
	@Test
	public void parseEvalResultOutputs() {
		ProcessEvaluationResult result = parsedEmulator.getEvaluationResult();
		List<String> identifiers = result.getOutputIdentifiers();
		assertThat(identifiers.size(), equalTo(1));
	}
	
	@Test
	public void parseEvalResultResults() {
		ProcessEvaluationResult result = parsedEmulator.getEvaluationResult();
		Double[] results = result.getResults("Result");
		assertThat(results.length, equalTo(19));
		assertThat(results[0], equalTo(-0.018569090941013357));
		assertThat(results[18], equalTo(0.6991839058872024));
	}
	
	@Test
	public void parseEvalResultNormalised() {
		ProcessEvaluationResult result = parsedEmulator.getEvaluationResult();
		assertThat(result, instanceOf(NormalisedProcessEvaluationResult.class));
		NormalisedProcessEvaluationResult norm = (NormalisedProcessEvaluationResult)result;
		assertThat(norm.getMean("Result"), equalTo(1981.6037046519125));
		assertThat(norm.getStdDev("Result"), equalTo(778.2534230469081));
	}
	
	private Emulator parseEmulator() {
		Reader reader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("polyfun-emulator.xml"));
		Emulator emulator = xml.parse(reader, Emulator.class);
		try {
			reader.close();
		}
		catch (IOException e) {
			// not too much of a problem
		}
		return emulator;
	}
	
	
}
