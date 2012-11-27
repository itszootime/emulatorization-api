package org.uncertweb.et.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.emulator.Emulator;
import org.uncertweb.et.process.ProcessEvaluationResult;
import org.uncertweb.et.request.ValidationRequest;
import org.uncertweb.et.value.EnsembleValues;
import org.uncertweb.et.value.MeanVarianceValues;
import org.uncertweb.et.value.NumericValues;

public class JSONTest {
	
	private JSON json;
	
	@Before
	public void before() {
		json = new JSON();
	}
	
	@Test
	public void encodeNumericValues() {
		fail();
	}
	
	@Test
	public void encodeEnsembleValues() {
		fail();
	}
	
	@Test
	public void encodeMeanVarianceValues() {
		fail();
	}
	
	@Test
	public void parseNumericValues() {
		NumericValues values = parseJSON("numeric-values.json", NumericValues.class);
		assertThat(values.size(), equalTo(3));
		assertThat(values.get(0).getNumber(), equalTo(0.1));
		assertThat(values.get(1).getNumber(), equalTo(0.2));
		assertThat(values.get(2).getNumber(), equalTo(0.4));
	}
	
	@Test
	public void parseEnsembleValues() {
		EnsembleValues values = parseJSON("ensemble-values.json", EnsembleValues.class);
		assertThat(values.size(), equalTo(2));
		assertThat(values.get(0).getMembers(), equalTo(new double[] { 0.1, 0.15, 0.001 }));
		assertThat(values.get(1).getMembers(), equalTo(new double[] { 10.55, 9.78, 12.002 }));
	}
	
	@Test
	public void parseMeanVarianceValues() {
		MeanVarianceValues values = parseJSON("meanvariance-values.json", MeanVarianceValues.class);
		assertThat(values.size(), equalTo(2));
		assertThat(values.get(0).getMean(), equalTo(4d));
		assertThat(values.get(0).getVariance(), equalTo(0.2d));
		assertThat(values.get(1).getMean(), equalTo(0.9d));
		assertThat(values.get(1).getVariance(), equalTo(0.002d));
	}
	
	@Test
	public void parseValidationRequestEnsembles() {
		ValidationRequest request = parseJSON("validation-request-ensembles.json", ValidationRequest.class);
		
		// check
		NumericValues observed = request.getObservedValues();
		EnsembleValues simulated = request.getSimulatedValues();
		assertThat(observed, notNullValue());
		assertThat(simulated, notNullValue());
		assertThat(observed.size(), equalTo(simulated.size()));
	}
	
	@Test
	public void parseValidationRequestEmulatorSimulator() {
		ValidationRequest request = parseJSON("validation-request-emulator-simulator.json", ValidationRequest.class);
		
		// check
		Emulator emulator = request.getEmulator();
		String serviceURL = request.getServiceURL();
		String processIdentifier = request.getProcessIdentifier();
		int designSize = request.getDesignSize();
		assertThat(emulator, notNullValue());
		assertThat(serviceURL, equalTo("http://uncertws.aston.ac.uk:8080/ps"));
		assertThat(processIdentifier, equalTo("Polyfun"));
		assertThat(designSize, equalTo(100));
	}
	
	@Test
	public void parseValidationRequestEmulatorValues() {
		ValidationRequest request = parseJSON("validation-request-emulator-values.json", ValidationRequest.class);
		
		// check
		Emulator emulator = request.getEmulator();
		Design design = request.getDesign();
		ProcessEvaluationResult result = request.getEvaluationResult();
		assertThat(emulator, notNullValue());
		assertThat(design, notNullValue());
		assertThat(result, notNullValue());
	}
	
	private <T> T parseJSON(String filename, Class<T> type) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
		InputStreamReader reader = new InputStreamReader(is);
		return json.parse(reader, type);
	}

}
