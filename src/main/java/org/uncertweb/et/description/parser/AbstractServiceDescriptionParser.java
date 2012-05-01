package org.uncertweb.et.description.parser;

import org.uncertweb.et.description.ServiceDescription;

public abstract class AbstractServiceDescriptionParser {
	
	protected String serviceURL;
	
	public AbstractServiceDescriptionParser(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public abstract ServiceDescription parse() throws ServiceDescriptionParserException;
	
}
