package org.uncertweb.et;

import org.junit.Test;
import org.uncertweb.et.request.ValidationRequest;
import org.uncertweb.et.response.Response;
import org.uncertweb.et.response.ValidationResponse;
import org.uncertweb.et.test.TestData;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class EmulatorizationTest {
	
	@Test
	public void validationEnsembles() throws Exception {
		ValidationRequest request = TestData.getValidationRequestEnsembles();
		Response response = Emulatorization.process(request);
		
		// check
		assertThat(response, notNullValue());
		assertThat(response, instanceOf(ValidationResponse.class));
	}
	
	@Test
	public void validationEmulatorSimulator() throws Exception {
		ValidationRequest request = TestData.getValidationRequestEmulatorSimulator();
		Response response = Emulatorization.process(request);
		
		// check
		assertThat(response, notNullValue());
		assertThat(response, instanceOf(ValidationResponse.class));
	}
	
	@Test
	public void validationEmulatorValues() throws Exception {
		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
		Response response = Emulatorization.process(request);
		
		// check
		assertThat(response, notNullValue());
		assertThat(response, instanceOf(ValidationResponse.class));
	}

}
