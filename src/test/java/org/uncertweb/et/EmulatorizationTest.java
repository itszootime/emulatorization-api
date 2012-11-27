package org.uncertweb.et;

import org.junit.Test;
import org.uncertweb.et.test.TestData;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.validation.ValidatorResult;
import org.uncertweb.et.value.EnsembleValues;
import org.uncertweb.et.value.NumericValues;

public class EmulatorizationTest {
	
	@Test
	public void validateObservedSimulated() {
		NumericValues observed = TestData.getObservedValues();
		EnsembleValues simulated = TestData.getSimulatedEnsembleValues();
		ValidatorResult output = Validator.validate(observed, simulated);
	}

}
