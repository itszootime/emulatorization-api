package org.uncertweb.et.response;

import java.util.List;

public class GetProcessIdentifiersResponse extends Response {

	private List<String> processIdentifiers;
	
	public GetProcessIdentifiersResponse(List<String> processIdentifiers) {
		this.processIdentifiers = processIdentifiers;
	}

	public List<String> getProcessIdentifiers() {
		return processIdentifiers;
	}
	
}
