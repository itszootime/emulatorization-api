package org.uncertweb.et.request;

public class GetProcessIdentifiersRequest extends Request {

	private String serviceURL;
	
	public GetProcessIdentifiersRequest() {
		
	}
	
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public String getServiceURL() {
		return serviceURL;
	}	
	
}
