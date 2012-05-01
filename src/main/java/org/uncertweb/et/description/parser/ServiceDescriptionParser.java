package org.uncertweb.et.description.parser;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.description.ServiceDescription;

public class ServiceDescriptionParser {

	private static final Logger logger = LoggerFactory.getLogger(ServiceDescriptionParser.class);
	private static Map<String, ServiceDescription> serviceDescriptionMap = Collections.synchronizedMap(new HashMap<String, ServiceDescription>());	

	public static ServiceDescription parse(String serviceURL) throws ServiceDescriptionParserException {
		logger.info("Parsing service description for " + serviceURL + "...");
		if (serviceDescriptionMap.containsKey(serviceURL)) {
			logger.info("Found cached service description.");
			return serviceDescriptionMap.get(serviceURL);
		}
		else {
			try {
				SAXBuilder builder = new SAXBuilder();
				Element root = builder.build(new URL(serviceURL + "?wsdl").openStream()).getRootElement();
				AbstractServiceDescriptionParser parser;
				if (root.getName().equals("definitions")) {
					// ps
					parser = new PSDescriptionParser(serviceURL);
				}
				else {
					// wps
					parser = new WPSDescriptionParser(serviceURL);
				}
				logger.info("Using " + parser.getClass().getSimpleName() + " to parse service description.");
				ServiceDescription description = parser.parse();
				serviceDescriptionMap.put(serviceURL, description);
				return description;
			}
			catch (IOException e) {
				throw new ServiceDescriptionParserException("Couldn't connect to service.");
			}
			catch (JDOMException e) {
				throw new ServiceDescriptionParserException("Couldn't read XML description from service.");
			}
		}
	}

}
