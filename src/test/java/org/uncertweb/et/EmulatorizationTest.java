package org.uncertweb.et;

import org.junit.Test;
import org.uncertweb.et.test.TestData;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.validation.ValidatorResult;
import org.uncertweb.et.value.Ensemble;
import org.uncertweb.et.value.Numeric;
import org.uncertweb.et.value.Values;

public class EmulatorizationTest {
	
	@Test
	public void validateObservedSimulated() {
		Values<Numeric> observed = TestData.getObservedValues();
		Values<Ensemble> simulated = TestData.getSimulatedEnsembleValues();
		ValidatorResult output = Validator.validate(observed, simulated);
	}

}
