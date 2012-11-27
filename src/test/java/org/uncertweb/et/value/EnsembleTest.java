package org.uncertweb.et.value;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.junit.Before;
import org.junit.Test;
import org.uncertweb.et.test.TestData;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class EnsembleTest {
	
	private Ensemble ensemble;
	private DescriptiveStatistics stats;
	
	@Before
	public void before() {
		ensemble = TestData.getNuPredicted().get(0);
		stats = new DescriptiveStatistics(ensemble.getMembers());
	}

	@Test
	public void mean() {
		assertThat(ensemble.getMean(), closeTo(stats.getMean(), 0.0001));
	}
	
	@Test
	public void variance() {
		assertThat(ensemble.getVariance(), closeTo(stats.getVariance(), 0.0001));
	}
	
	@Test
	public void standardDeviation() {
		assertThat(ensemble.getStandardDeviation(), closeTo(stats.getStandardDeviation(), 0.0001));
	}
	
}
