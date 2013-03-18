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
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.parameter.ConstantInput;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.parameter.ParameterDescription;
import org.uncertweb.et.parameter.ParameterDescription.DataType;
import org.uncertweb.et.parameter.VariableInput;

public class XMLTest {

	private XML xml;
	
	@Before
	public void before() {
		xml = new XML(); 
	}
	
	@Test
	public void parseEmulatorNotNull() {
		Emulator emulator = parseEmulator();
		assertThat(emulator, notNullValue());
	}
	
	@Test
	public void parseEmulatorInputCount() {
		Emulator emulator = parseEmulator();
		List<Input> inputs = emulator.getInputs();
		assertThat(inputs.size(), equalTo(2));
	}
	
	@Test
	public void parseEmulatorInput() {
		Emulator emulator = parseEmulator();
		Input input = emulator.getInputs().get(0);
		assertThat(input.getIdentifier(), equalTo("A"));
		ParameterDescription desc = input.getDescription();
		assertThat(desc.getDataType(), equalTo(DataType.Numeric));
		assertThat(desc.getEncodingType(), equalTo("double"));
	}
	
	@Test
	public void parseEmulatorInputVariable() {
		Emulator emulator = parseEmulator();
		Input input = emulator.getInputs().get(0);
		assertThat(input, instanceOf(VariableInput.class));
		VariableInput variableInput = (VariableInput)input;
		assertThat(variableInput.getMin(), equalTo(100.0));
		assertThat(variableInput.getMax(), equalTo(1000.0));
	}
	
	@Test
	public void parseEmulatorInputFixed() {
		Emulator emulator = parseEmulator();
		Input input = emulator.getInputs().get(0);
		assertThat(input, instanceOf(ConstantInput.class));
		ConstantInput fixedInput = (ConstantInput)input;
		assertThat(fixedInput.getValue(), equalTo(10.0));
	}
	
	@Test
	public void parseEmulatorOutputCount() {
		Emulator emulator = parseEmulator();
		List<Output> outputs = emulator.getOutputs();
		assertThat(outputs.size(), equalTo(1));
	}
	
	@Test
	public void parseEmulatorOutput() {
		Emulator emulator = parseEmulator();
		Output output = emulator.getOutputs().get(0);
		assertThat(output.getIdentifier(), equalTo("Result"));
		ParameterDescription desc = output.getDescription();
		assertThat(desc.getDataType(), equalTo(DataType.Numeric));
		assertThat(desc.getEncodingType(), equalTo("double"));
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
