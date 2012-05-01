package org.uncertweb.et.description;

import java.util.ArrayList;
import java.util.List;

public class ProcessDescription {

	private String identifier;
	private String detail;
	private List<ParameterDescription> inputDescriptions;
	private List<ParameterDescription> outputDescriptions;
	
	public ProcessDescription(String identifier) {
		this.identifier = identifier;
		inputDescriptions = new ArrayList<ParameterDescription>();
		outputDescriptions = new ArrayList<ParameterDescription>();
	}
	
	public ProcessDescription(String identifier, String detail) {
		this(identifier);
		this.detail = detail;
	}	
	
	public void addInputDescription(ParameterDescription inputDescription) {
		inputDescriptions.add(inputDescription);
	}
	
	public void addOutputDescription(ParameterDescription outputDescription) {
		outputDescriptions.add(outputDescription);
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getDetail() {
		return detail;
	}
	
}
