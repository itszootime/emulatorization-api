package org.uncertweb.et.response;

import org.uncertweb.et.description.ProcessDescription;

public class GetProcessDescriptionResponse extends Response {

	private ProcessDescription processDescription;
	
	public GetProcessDescriptionResponse(ProcessDescription processDescription) {
		this.processDescription = processDescription;
	}

	public ProcessDescription getProcessDescription() {
		return processDescription;
	}	
	
}
