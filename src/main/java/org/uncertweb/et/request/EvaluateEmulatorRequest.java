package org.uncertweb.et.request;

import org.uncertweb.et.design.Design;
import org.uncertweb.et.emulator.Emulator;

public class EvaluateEmulatorRequest extends Request {

	private Emulator emulator;
	private Design design;
	
	public EvaluateEmulatorRequest() {
		
	}

	public Emulator getEmulator() {
		return emulator;
	}

	public Design getDesign() {
		return design;
	}
	
}
