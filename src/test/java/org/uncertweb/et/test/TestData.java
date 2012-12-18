package org.uncertweb.et.test;

import org.junit.Ignore;
import org.uncertweb.et.emulator.EmulatorEvaluatorException;
import org.uncertweb.et.process.ProcessEvaluatorException;
import org.uncertweb.et.request.ValidationRequest;
import org.uncertweb.et.validation.Validator;
import org.uncertweb.et.validation.ValidatorException;
import org.uncertweb.et.value.DistributionValues;
import org.uncertweb.et.value.SampleValues;
import org.uncertweb.et.value.ScalarValues;

/**
 * This class shouldn't really do any JSON parsing or use other functions to derive data, but it's
 * quicker for now.
 * 
 * @author Richard Jones
 *
 */
@Ignore
public class TestData {
	
	public static DistributionValues getCbPredicted() {
		return TestHelper.parseJSON("cb-predicted-distribution-values.json", DistributionValues.class);
	}
	
	public static ScalarValues getCbObserved() {
		return TestHelper.parseJSON("cb-observed-scalar-values.json", ScalarValues.class);
	}
	
	public static DistributionValues getPfPredicted() {
		return TestHelper.parseJSON("pf-predicted-distribution-values.json", DistributionValues.class);
	}
	
	public static ScalarValues getPfObserved() {
		return TestHelper.parseJSON("pf-observed-scalar-values.json", ScalarValues.class);
	}
	
	public static ScalarValues getNuObserved() {
		return TestHelper.parseJSON("nu-observed-scalar-values.json", ScalarValues.class);
	}

	public static SampleValues getNuPredicted() {
		return TestHelper.parseJSON("nu-predicted-sample-values.json", SampleValues.class);
	}

	public static ValidationRequest getValidationRequestSamples() {
		return TestHelper.parseJSON("validation-request-samples.json", ValidationRequest.class);
	}

	public static ValidationRequest getValidationRequestEmulatorSimulator() {
		return TestHelper.parseJSON("validation-request-emulator-simulator.json", ValidationRequest.class);
	}

	public static ValidationRequest getValidationRequestEmulatorValues() {
		return TestHelper.parseJSON("validation-request-emulator-values.json", ValidationRequest.class);
	}
	
	public static Validator getValidatorEmulatorValues() {
		ValidationRequest request = getValidationRequestEmulatorValues();
		Validator validator = null;
		try {
			validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
		}
		catch (EmulatorEvaluatorException e) { }
		catch (ProcessEvaluatorException e) { }
		catch (ValidatorException e) { }
		return validator;
	}

}
