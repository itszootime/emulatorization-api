package org.uncertweb.et.test;

import java.text.DecimalFormat;
import static org.junit.Assert.*;

public class TestHelper {
	
	private static final DecimalFormat FORMAT = new DecimalFormat("#.####");
	
	public static double to3DP(double number) {
		return Double.valueOf(FORMAT.format(number));
	}
	
	public static void assertArrayEquals(Double[] expected, Double[] actual, double delta) {
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i], delta);
		}
	}

}
