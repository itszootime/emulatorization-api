package org.uncertweb.et.design;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.uncertweb.et.test.TestHelper;

public class NormalisedDesignTest {

	private Design odesign;
	private NormalisedDesign ndesign1;

	@Before
	public void setUp() {
		// create original design
		odesign = new Design(3);
		odesign.addPoints("A", new Double[] { 2d, 1d, 8d });
		odesign.addPoints("B", new Double[] { 6d, 5d, 4d });
		odesign.addPoints("C", new Double[] { 9d, 3d, 7d });
		
		// normalise it
		ndesign1 = NormalisedDesign.fromDesign(odesign);
	}

	@Test
	public void mean() {
		// check values
		assertEquals(3.6667, ndesign1.getMean("A"), 0.0001);
		assertEquals(5.0000, ndesign1.getMean("B"), 0.0001);
		assertEquals(6.3333, ndesign1.getMean("C"), 0.0001);
	}

	@Test
	public void stdDev() {
		// check values
		assertEquals(3.7859, ndesign1.getStdDev("A"), 0.0001);
		assertEquals(1.0000, ndesign1.getStdDev("B"), 0.0001);
		assertEquals(3.0551, ndesign1.getStdDev("C"), 0.0001);
	}

	@Test
	public void normalisedValues() {
		// get points
		Double[] aPoints = ndesign1.getPoints("A");
		Double[] bPoints = ndesign1.getPoints("B");
		Double[] cPoints = ndesign1.getPoints("C");
		
		// check them
		TestHelper.assertArrayEquals(new Double[] { -0.4402, -0.7044, 1.1445 }, aPoints, 0.0001);
		TestHelper.assertArrayEquals(new Double[] { 1.0000, 0.0, -1.0000 }, bPoints, 0.0001);
		TestHelper.assertArrayEquals(new Double[] { 0.8729, -1.0911, 0.2182 }, cPoints, 0.0001);
	}
	
	@Test
	public void unnormalisedValues() {
		// unnormalised
		Design udesign1 = ndesign1.unnormalise();
		
		// get points
		Double[] aPoints = udesign1.getPoints("A");
		Double[] bPoints = udesign1.getPoints("B");
		Double[] cPoints = udesign1.getPoints("C");
		
		// check them
		TestHelper.assertArrayEquals(new Double[] { 2.0, 1.0, 8.0 }, aPoints, 0.0001);
		TestHelper.assertArrayEquals(new Double[] { 6.0, 5.0, 4.0 }, bPoints, 0.0001);
		TestHelper.assertArrayEquals(new Double[] { 9.0, 3.0, 7.0 }, cPoints, 0.0001);
	}

}
