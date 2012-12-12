package org.uncertweb.et.value;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.junit.Before;
import org.junit.Test;
import org.uncertweb.et.test.TestData;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class SampleTest {
	
	private Sample sample;
	private DescriptiveStatistics stats;
	
	@Before
	public void before() {
		sample = TestData.getNuPredicted().get(0);
		stats = new DescriptiveStatistics(sample.getMembers());
	}

	@Test
	public void mean() {
		assertThat(sample.getMean(), closeTo(stats.getMean(), 0.0001));
	}
	
	@Test
	public void variance() {
		assertThat(sample.getVariance(), closeTo(stats.getVariance(), 0.0001));
	}
	
	@Test
	public void standardDeviation() {
		assertThat(sample.getStandardDeviation(), closeTo(stats.getStandardDeviation(), 0.0001));
	}
	
}
