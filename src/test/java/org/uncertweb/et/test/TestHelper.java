package org.uncertweb.et.test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Ignore;
import org.uncertweb.et.json.JSON;

@Ignore
public class TestHelper {
	
	private static final DecimalFormat FORMAT = new DecimalFormat("#.####");
	
	public static double to3DP(double number) {
		return Double.valueOf(FORMAT.format(number));
	}
	
	public static void assertArrayEquals(Double[] expected, Double[] actual, double delta) {
		Assert.assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals(expected[i], actual[i], delta);
		}
	}	
	
	public static <T> T parseJSON(String filename, Class<T> type) {
		InputStream is = TestHelper.class.getClassLoader().getResourceAsStream(filename);
		InputStreamReader reader = new InputStreamReader(is);
		return new JSON().parse(reader, type);
	}

}
