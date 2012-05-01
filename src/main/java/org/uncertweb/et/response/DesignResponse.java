package org.uncertweb.et.response;

import org.uncertweb.et.design.Design;

public class DesignResponse extends Response {

	private Design design;
	
	public DesignResponse(Design design) {
		this.design = design;
	}
	
	public Design getDesign() {
		return design;
	}
	
}
