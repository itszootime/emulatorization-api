package org.uncertweb.et.value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.junit.Before;
import org.junit.Test;
import org.uncertweb.et.test.TestData;

public class NumericValuesTest {
	
	private NumericValues values;
	private DescriptiveStatistics stats;
	
	@Before
	public void before() {
		values = TestData.getNuObserved();
		stats = new DescriptiveStatistics(values.toArray());
	}
	
	@Test
	public void mean() {
		assertThat(values.getMean(), closeTo(stats.getMean(), 0.0001));
	}

	@Test
	public void standardDeviation() {
		assertThat(values.getStandardDeviation(), closeTo(stats.getStandardDeviation(), 0.0001));
	}
	
	@Test
	public void variance() {
		assertThat(values.getVariance(), closeTo(stats.getVariance(), 0.0001));
	}
	
}
