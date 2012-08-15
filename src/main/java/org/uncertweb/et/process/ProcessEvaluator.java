package org.uncertweb.et.process;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;

public class ProcessEvaluator {

	private static final Logger logger = LoggerFactory.getLogger(ProcessEvaluator.class);

	public static ProcessEvaluationResult evaluate(String serviceURL, String processIdentifier, List<Input> inputs, List<Output> outputs, Design design) throws ProcessEvaluatorException {
		try {
			logger.info("Finding evaluator for " + processIdentifier + " at " + serviceURL + "...");
			SAXBuilder builder = new SAXBuilder();
			Element root = builder.build(new URL(serviceURL + "?wsdl").openStream()).getRootElement();
			AbstractProcessEvaluator evaluator;
			if (root.getName().equals("definitions")) {
				// ps
				evaluator = new PSProcessEvaluator(serviceURL, processIdentifier, inputs, outputs, design);
			}
			else {
				// wps
				evaluator = new WPSProcessEvaluator(serviceURL, processIdentifier, inputs, outputs, design);
			}
			logger.info("Using " + evaluator.getClass().getSimpleName() + " to evaluate " + design.getSize() + " input points.");
			return evaluator.evaluate();
		}
		catch (IOException e) {
			throw new ProcessEvaluatorException("Couldn't connect to service.");
		}
		catch (JDOMException e) {
			throw new ProcessEvaluatorException("Couldn't read XML description from service.");
		}
	}

}
