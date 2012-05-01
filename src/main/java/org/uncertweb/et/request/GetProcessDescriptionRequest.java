package org.uncertweb.et.request;

public class GetProcessDescriptionRequest extends Request {
	
	private String serviceURL;
	private String processIdentifier;
	
	public GetProcessDescriptionRequest() {
		
	}

	public String getServiceURL() {
		return serviceURL;
	}

	public String getProcessIdentifier() {
		return processIdentifier;
	}

}
