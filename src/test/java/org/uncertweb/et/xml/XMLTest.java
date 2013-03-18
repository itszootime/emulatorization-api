package org.uncertweb.et.xml;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;
import org.uncertweb.et.emulator.Emulator;

public class XMLTest {

	private XML xml;
	
	@Before
	public void before() {
		xml = new XML(); 
	}
	
	@Test
	public void parseEmulatorNotNull() {
		
	}
	
	@Test
	public void parseEmulatorInputs() {
		Emulator emulator = parseEmulator();
	}
	
	@Test
	public void parseEmulatorOutputs() {
		
	}
	
	private Emulator parseEmulator() {
		Reader reader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("emulator.xml"));
		Emulator emulator = xml.parse(reader, Emulator.class);
		try {
			reader.close();
		}
		catch (IOException e) {
			// not too much of a problem
		}
		return emulator;
	}
	
	
}
