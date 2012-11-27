package org.uncertweb.et.test;

import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.process.ProcessEvaluatorException;
import org.uncertweb.et.request.ValidationRequest;
import org.uncertweb.et.response.ValidationResponse;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.value.EnsembleValues;
import org.uncertweb.et.value.MeanVarianceValues;
import org.uncertweb.et.value.NumericValues;

/**
 * This class shouldn't really do any JSON parsing or use other functions to derive data, but it's
 * quicker for now.
 * 
 * @author Richard Jones
 *
 */
public class TestData {
	
	public static MeanVarianceValues getPfPredicted() {
		return TestHelper.parseJSON("pf-predicted-mean-variance-values.json", MeanVarianceValues.class);
	}
	
	public static NumericValues getPfObserved() {
		return TestHelper.parseJSON("pf-observed-numeric-values.json", NumericValues.class);
	}
	
	public static NumericValues getNuObserved() {
		return TestHelper.parseJSON("nu-observed-numeric-values.json", NumericValues.class);
	}

	public static EnsembleValues getNuPredicted() {
		return TestHelper.parseJSON("nu-predicted-ensemble-values.json", EnsembleValues.class);
	}

	public static ValidationRequest getValidationRequestEnsembles() {
		return TestHelper.parseJSON("validation-request-ensembles.json", ValidationRequest.class);
	}

	public static ValidationRequest getValidationRequestEmulatorSimulator() {
		return TestHelper.parseJSON("validation-request-emulator-simulator.json", ValidationRequest.class);
	}

	public static ValidationRequest getValidationRequestEmulatorValues() {
		return TestHelper.parseJSON("validation-request-emulator-values.json", ValidationRequest.class);
	}
	
	public static ValidationResponse getValidationResponseEmulatorValues() {
		ValidationRequest request = getValidationRequestEmulatorValues();
		Validator validator = null;
		try {
			validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
		}
		catch (EmulatorEvaluatorException e) {	}
		catch (ProcessEvaluatorException e) {	}
		return ValidationResponse.fromValidator(validator);
	}

}
