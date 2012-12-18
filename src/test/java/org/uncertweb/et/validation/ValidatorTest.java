package org.uncertweb.et.validation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.uncertweb.et.plot.PlotData;
import org.uncertweb.et.test.TestData;

public class ValidatorTest {
	
	private static Validator validator;
	
	@BeforeClass
	public static void beforeClass() throws ValidatorException {
		validator = new Validator(TestData.getCbObserved(), TestData.getCbPredicted());
	}
	
	@Test
	public void rmse() {
		assertThat(validator.getRMSE(), closeTo(0.2079, 0.0001));
	}
	
	@Test
	public void meanResidualHistogram() {
		PlotData data = validator.getMeanResidualHistogramData();
		double[] expectedX = new double[] { -0.556475038049856,-0.514864577028613,-0.473254116007369,-0.431643654986126,-0.390033193964883,-0.348422732943639,-0.306812271922396,-0.265201810901153,-0.223591349879909,-0.181980888858666,-0.140370427837422,-0.0987599668161791,-0.0571495057949358,-0.0155390447736924,0.0260714162475510,0.0676818772687944,0.109292338290038,0.150902799311281,0.192513260332525,0.234123721353768,0.275734182375011,0.317344643396255,0.358955104417498,0.400565565438741,0.442176026459985,0.483786487481228,0.525396948502471,0.567007409523715,0.608617870544958,0.650228331566201 };
		double[] expectedY = new double[] { 3,1,3,3,6,4,7,16,21,23,24,24,32,33,22,36,27,24,25,19,15,11,9,4,4,2,0,1,0,1 };
		
		testArrayCloseTo(data.getX(), expectedX, 0.0001);
		testArrayCloseTo(data.getY(), expectedY, 0.0001);
	}

//	@Test
//	public void meanResidualQQPlot() {
//		PlotData data = validator.getMeanResidualQQPlotData();
//		double[] expectedX = new double[] { };
//		double[] expectedY = new double[] { };
//		
//		testArrayCloseTo(data.getX(), expectedX, 0.0001);
//		testArrayCloseTo(data.getY(), expectedY, 0.0001);
//	}
	
	@Test
	public void medianResidualHistogram() {
		PlotData data = validator.getMedianResidualHistogramData();
		double[] expectedX = new double[] { -0.543172657963770,-0.502721689327886,-0.462270720692002,-0.421819752056119,-0.381368783420235,-0.340917814784351,-0.300466846148468,-0.260015877512584,-0.219564908876700,-0.179113940240817,-0.138662971604933,-0.0982120029690495,-0.0577610343331659,-0.0173100656972822,0.0231409029386014,0.0635918715744851,0.104042840210369,0.144493808846252,0.184944777482136,0.225395746118020,0.265846714753903,0.306297683389787,0.346748652025671,0.387199620661554,0.427650589297438,0.468101557933322,0.508552526569205,0.549003495205089,0.589454463840972,0.629905432476856 };
		double[] expectedY = new double[] { 3,2,4,4,3,5,6,20,21,20,25,23,27,28,31,30,30,23,21,20,17,15,5,7,5,3,0,0,1,1 };
		
		testArrayCloseTo(data.getX(), expectedX, 0.0001);
		testArrayCloseTo(data.getY(), expectedY, 0.0001);
	}
	
//	@Test
//	public void medianResidualQQPlot() {
//		PlotData data = validator.getMedianResidualQQPlotData();
//		double[] expectedX = new double[] { };
//		double[] expectedY = new double[] { };
//		
//		testArrayCloseTo(data.getX(), expectedX, 0.0001);
//		testArrayCloseTo(data.getY(), expectedY, 0.0001);
//	}
	
	@Test
	public void reliabilityDiagram() {
		PlotData data = validator.getReliabilityDiagramData();
		double[] expectedX = new double[] { 0,0.0338219895287959,0.147575757575758,0.249044117647059,0.348518518518519,0.453274336283186,0.548526315789474,0.640701754385965,0.728000000000000 };
		double[] expectedY = new double[] { 0,0.0471204188481675,0.131313131313131,0.301470588235294,0.348148148148148,0.469026548672566,0.505263157894737,0.614035087719298,0.400000000000000 };
		double[] expectedN = new double[] { 0,573,198,136,135,113,190,114,10 };
		
		testArrayCloseTo(data.getX(), expectedX, 0.0001);
		testArrayCloseTo(data.getY(), expectedY, 0.0001);
		assertThat(data.getN(), equalTo(expectedN));
	}
	
	private void testArrayCloseTo(double[] actual, double[] expected, double error) {
		// should go in a matcher
		for (int i = 0; i < actual.length; i++) {
			double a = actual[i];
			double e = expected[i];
			assertThat(a, anyOf(closeTo(e, error), equalTo(e)));
		}
	}
	
	private void testPlotData(PlotData data, double[] expectedX, double[] expectedY) {
		testArrayCloseTo(data.getX(), expectedX, 0.0001);
		testArrayCloseTo(data.getY(), expectedY, 0.0001);
	}
	
//	@Test
//	public void standardScoresMeanVariance() throws ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
//		NumericValues observed = TestData.getPfObserved();
//		Validator validator = new Validator(observed, TestData.getPfPredicted());
//		
//		// get scores
//		NumericValues scores = validator.getStandardScores();
//		
//		// check		
//		assertThat(scores.size(), equalTo(observed.size()));
//		assertThat(scores.get(0).getNumber(), closeTo(0.097, 0.0001));
//		assertThat(scores.get(2).getNumber(), closeTo(-0.6012, 0.0001));
//	}
	
//	@Test
//	public void rmseEnsembles() {
//		Validator validator = new Validator(TestData.getNuObserved(), TestData.getNuPredicted());
//				
//		// check
//		assertThat(validator.getRMSE(), closeTo(20.7213, 0.0001));
//	}
//	
//	@Test
//	public void standardScoresEnsembles() {
//		NumericValues observed = TestData.getNuObserved();
//		Validator validator = new Validator(observed, TestData.getNuPredicted());
//		
//		// get scores
//		NumericValues scores = validator.getStandardScores();
//		
//		// check
//		assertThat(scores.size(), equalTo(observed.size()));
//		assertThat(scores.get(0).getNumber(), closeTo(-8.3571, 0.0001));
//		assertThat(scores.get(2).getNumber(), closeTo(-2.783, 0.0001));
//	}
//	
//	@Test
//	public void rmseNumeric() {
//		NumericValues observed = TestData.getNuObserved();
//		Validator validator = new Validator(observed, TestData.getNuObserved());
//		
//		// check
//		assertThat(validator.getRMSE(), equalTo(0.0));
//	}
//	
//	@Test
//	public void standardScoresNumeric() {
//		NumericValues observed = TestData.getNuObserved();
//		Validator validator = new Validator(observed, TestData.getNuObserved());
//		
//		// get scores
//		NumericValues scores = validator.getStandardScores();
//		
//		// check
//		assertThat(scores.size(), equalTo(observed.size()));
//		assertThat(scores.get(0).getNumber(), equalTo(0.0));
//		assertThat(scores.get(2).getNumber(), equalTo(0.0));
//	}
	
//	@Test
//	public void usingSimulatorAndEmulatorObserved() throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
//		ValidationRequest request = TestData.getValidationRequestEmulatorSimulator();
//		Validator validator = Validator.usingSimulatorAndEmulator(request.getServiceURL(), request.getProcessIdentifier(), request.getEmulator(), request.getDesignSize());
//		
//		// check
//		assertThat(validator.getObserved(), notNullValue());
//		assertThat(validator.getObserved().size(), equalTo(request.getDesignSize()));
//	}
//	
//	@Test
//	public void usingSimulatorAndEmulatorPredicted() throws DesignException, ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
//		ValidationRequest request = TestData.getValidationRequestEmulatorSimulator();
//		Validator validator = Validator.usingSimulatorAndEmulator(request.getServiceURL(), request.getProcessIdentifier(), request.getEmulator(), request.getDesignSize());
//		
//		// check
//		assertThat(validator.getPredicted(), notNullValue());
//		assertThat(validator.getPredicted().size(), equalTo(request.getDesignSize()));
//	}
//	
//	@Test
//	public void usingPredictionsAndEmulatorObserved() throws ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
//		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
//		Validator validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
//		
//		// check
//		assertThat(validator.getObserved(), notNullValue());
//		assertThat(validator.getObserved().size(), equalTo(request.getEvaluationResult().getSize()));
//	}
//	
//	@Test
//	public void usingPredictionsAndEmulatorPredicted() throws ProcessEvaluatorException, EmulatorEvaluatorException, ValidatorException {
//		ValidationRequest request = TestData.getValidationRequestEmulatorValues();
//		Validator validator = Validator.usingPredictionsAndEmulator(request.getDesign(), request.getEvaluationResult(), request.getEmulator());
//		
//		// check
//		assertThat(validator.getPredicted(), notNullValue());
//		assertThat(validator.getPredicted().size(), equalTo(request.getEvaluationResult().getSize()));
//	}
	
}
