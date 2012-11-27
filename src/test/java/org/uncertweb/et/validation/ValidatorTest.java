package org.uncertweb.et.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.uncertweb.et.design.DesignException;
import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.process.ProcessEvaluatorException;
import org.uncertweb.et.request.ValidationRequest;
import org.uncertweb.et.test.TestData;
import org.uncertweb.et.value.NumericValues;

public class ValidatorTest {

//	@Test
//	public void validateEnsembles() {
//		ValidationRequest request = TestData.getValidationRequestEnsembles();
//		//Validator result = new Validator(request.getObserved(), request.getPredicted());
//		
//		// check
//		//assertThat(result, notNullValue());
//	}
//	
	@Test
	public void simulatorAndEmulatorNotNull() throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorSimulator();
		Validator validator = Validator.usingSimulatorAndEmulator(request.getServiceURL(), request.getProcessIdentifier(), request.getEmulator(), request.getDesignSize());
		
		// check
		assertThat(validator, notNullValue());
	}
	
	@Test
	public void simulatorAndEmulatorRMSE() throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorSimulator();
		Validator validator = Validator.usingSimulatorAndEmulator(request.getServiceURL(), request.getProcessIdentifier(), request.getEmulator(), request.getDesignSize());
		
		// check
		assertThat(validator.getRMSE(), closeTo(0.1, 0.05));
	}
	
	@Test
	public void predictionsAndEmulatorNotNull() throws ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
		Validator validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
		
		// check
		assertThat(validator, notNullValue());
	}
	
	@Test
	public void predictionsAndEmulatorRMSE() throws ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
		Validator validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
		
		// check
		assertThat(validator.getRMSE(), closeTo(0.1547, 0.0001));
	}
	
	@Test
	public void predictionsAndEmulatorStandardScores() throws ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
		Validator validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
		NumericValues scores = validator.getStandardScores();
		
		// check		
		assertThat(scores.size(), equalTo(request.getEvaluationResult().getSize()));
		assertThat(scores.get(0).getNumber(), closeTo(0.097, 0.0001));
		assertThat(scores.get(2).getNumber(), closeTo(-0.6012, 0.0001));
	}
	
}
