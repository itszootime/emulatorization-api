package org.uncertweb.et.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.uncertweb.et.design.DesignException;
import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.process.ProcessEvaluatorException;
import org.uncertweb.et.request.ValidationRequest;
import org.uncertweb.et.test.TestData;
import org.uncertweb.et.value.NumericValues;

public class ValidatorTest {
	
	@Test
	public void rmseNumeric() {
		NumericValues observed = TestData.getNuObserved();
		Validator validator = new Validator(observed, TestData.getNuObserved());
		
		// check
		assertThat(validator.getRMSE(), equalTo(0.0));
	}
	
	@Test
	public void standardScoresNumeric() {
		NumericValues observed = TestData.getNuObserved();
		Validator validator = new Validator(observed, TestData.getNuObserved());
		
		// get scores
		NumericValues scores = validator.getStandardScores();
		
		// check
		assertThat(scores.size(), equalTo(observed.size()));
		assertThat(scores.get(0).getNumber(), closeTo(0.0, 0.0));
		assertThat(scores.get(2).getNumber(), closeTo(0.0, 0.0));
	}
	
	@Test
	public void rmseEnsembles() {
		Validator validator = new Validator(TestData.getNuObserved(), TestData.getNuPredicted());
				
		// check
		assertThat(validator.getRMSE(), closeTo(1000.0, 0.0));
	}
	
	@Test
	public void standardScoresEnsembles() {
		NumericValues observed = TestData.getNuObserved();
		Validator validator = new Validator(observed, TestData.getNuPredicted());
		
		// get scores
		NumericValues scores = validator.getStandardScores();
		
		// check
		assertThat(scores.size(), equalTo(observed.size()));
		assertThat(scores.get(0).getNumber(), closeTo(1000.0, 0.0));
		assertThat(scores.get(2).getNumber(), closeTo(1000.0, 0.0));
	}
	
	@Test
	public void rmseMeanVariance() throws ProcessEvaluatorException, EmulatorEvaluatorException {
		Validator validator = new Validator(TestData.getPfObserved(), TestData.getPfPredicted());
		
		// check
		assertThat(validator.getRMSE(), closeTo(0.1547, 0.0001));
	}
	
	@Test
	public void standardScoresMeanVariance() throws ProcessEvaluatorException, EmulatorEvaluatorException {
		NumericValues observed = TestData.getPfObserved();
		Validator validator = new Validator(observed, TestData.getPfPredicted());
		
		// get scores
		NumericValues scores = validator.getStandardScores();
		
		// check		
		assertThat(scores.size(), equalTo(observed.size()));
		assertThat(scores.get(0).getNumber(), closeTo(0.097, 0.0001));
		assertThat(scores.get(2).getNumber(), closeTo(-0.6012, 0.0001));
	}
	
	@Test
	public void usingSimulatorAndEmulatorObserved() throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorSimulator();
		Validator validator = Validator.usingSimulatorAndEmulator(request.getServiceURL(), request.getProcessIdentifier(), request.getEmulator(), request.getDesignSize());
		
		// check
		assertThat(validator.getObserved(), notNullValue());
		assertThat(validator.getObserved().size(), equalTo(request.getDesignSize()));
	}
	
	@Test
	public void usingSimulatorAndEmulatorPredicted() throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorSimulator();
		Validator validator = Validator.usingSimulatorAndEmulator(request.getServiceURL(), request.getProcessIdentifier(), request.getEmulator(), request.getDesignSize());
		
		// check
		assertThat(validator.getPredicted(), notNullValue());
		assertThat(validator.getPredicted().size(), equalTo(request.getDesignSize()));
	}
	
	@Test
	public void usingPredictionsAndEmulatorObserved() throws ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
		Validator validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
		
		// check
		assertThat(validator.getObserved(), notNullValue());
		assertThat(validator.getObserved().size(), equalTo(request.getEvaluationResult().getSize()));
	}
	
	@Test
	public void usingPredictionsAndEmulatorPredicted() throws ProcessEvaluatorException, EmulatorEvaluatorException {
		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
		Validator validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
		
		// check
		assertThat(validator.getPredicted(), notNullValue());
		assertThat(validator.getPredicted().size(), equalTo(request.getEvaluationResult().getSize()));
	}
	
}
