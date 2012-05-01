package org.uncertweb.et.process;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.design.Design;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.xml.Namespaces;
import org.uncertweb.xml.XPathWrapper;

public class WPSProcessEvaluator extends AbstractProcessEvaluator {
	
	private static final Logger logger = LoggerFactory.getLogger(WPSProcessEvaluator.class);

	public WPSProcessEvaluator(String serviceURL, String processIdentifier, List<Input> inputs, List<Output> outputs, Design design) {
		super(serviceURL, processIdentifier, inputs, outputs, design);
	}

	public ProcessEvaluationResult evaluate() throws ProcessEvaluatorException {
		try {			
			// get process description document
			SAXBuilder builder = new SAXBuilder();
			Document procDesc = builder.build(new URL(serviceURL + "?Service=WPS&Request=DescribeProcess&Identifier=" + processIdentifier));
			Element procDescRoot = procDesc.getRootElement();

			// our objective here is to find the type of data for each input or output
			Map<Input, String> inputDataTypes = getInputDataTypes(procDescRoot);
			Map<Output, String> outputDataTypes = getOutputDataTypes(procDescRoot);

			// do evaluate
			Map<Output, Double[]> runResults = new HashMap<Output, Double[]>();
			for (int run = 0; run < design.getSize(); run++) {
				// print log message
				//logger.info("Performing run " + (run + 1) + " of " + design.getSize() + "...");
				
				// create request
				Document document = new Document();
				Element execute = new Element("Execute", Namespaces.WPS)
				.setAttribute("service", "WPS")
				.setAttribute("version", "1.0.0")
				.addContent(new Element("Identifier", Namespaces.OWS).setText(processIdentifier));
				document.setRootElement(execute);
				
				// make things a bit tidier!
				execute.addNamespaceDeclaration(Namespaces.WPS);
				execute.addNamespaceDeclaration(Namespaces.OWS);

				// add each input
				Element dataInputsElement = new Element("DataInputs", Namespaces.WPS);
				execute.addContent(dataInputsElement);
				for (Input input : inputs) {
					// create element
					Element dataElement = new Element("Data", Namespaces.WPS);
					Element inputElement = new Element("Input", Namespaces.WPS)
					.addContent(new Element("Identifier", Namespaces.OWS).setText(input.getIdentifier()))
					.addContent(dataElement);
					dataInputsElement.addContent(inputElement);

					// get value
					double value = design.getPoints(input.getIdentifier())[run];

					// get type to cast into
					String type = inputDataTypes.get(input);
					if (type.equals("double")) {
						// simple
						dataElement.addContent(new Element("LiteralData", Namespaces.WPS)
						.setAttribute("dataType", "xs:double")
						.setText(String.valueOf(value)));
					}
					else {
						throw new ProcessEvaluatorException("Process uses unsupported data type for " + input.getIdentifier() + "input.");
					}
				}

				// add response form
				Element responseDocElement = new Element("ResponseDocument", Namespaces.WPS)
				.setAttribute("store", "false")
				.setAttribute("lineage", "false")
				.setAttribute("status", "false");
				execute.addContent(new Element("ResponseForm", Namespaces.WPS)
				.addContent(responseDocElement));
				
				for (Output output : outputs) {
					responseDocElement.addContent(new Element("Output", Namespaces.WPS)
					.setAttribute("asReference", "false")
					.addContent(new Element("Identifier", Namespaces.OWS).setText(output.getIdentifier())));
				}
				
				// evaluate
				HttpURLConnection connection = (HttpURLConnection) new URL(serviceURL + "?Service=WPS&Request=Execute").openConnection();
				connection.setDoOutput(true);
				new XMLOutputter().output(document, connection.getOutputStream());

				// get response
				Document responseDocument = new SAXBuilder().build(connection.getInputStream());
				Element responseRoot = responseDocument.getRootElement();

				// see if we got an exception
				Element exceptionReport = responseRoot.getChild("ExceptionReport", Namespaces.OWS);
				if (exceptionReport != null) {
					// many Exception elements, all have exception code
					// let's just get the message from the first one for now
					Element exceptionElement = exceptionReport.getChild("Exception", Namespaces.OWS);
					String message = exceptionElement.getChildText("ExceptionText", Namespaces.OWS);
					throw new ProcessEvaluatorException(message);
				}
				else {
					// get outputs from response
					for (Output output : outputs) {
						// get element
						XPath xpath = XPathWrapper.newInstance("//wps:Output[ows:Identifier = '" + output.getIdentifier() + "']");
						Element outputElement = (Element) xpath.selectSingleNode(responseRoot);

						// if exists
						if (outputElement != null) {
							// find value
							double runValue;

							// get type to cast from
							String type = outputDataTypes.get(output);
							if (type.equals("double")) {
								// simple!
								xpath = XPathWrapper.newInstance(".//wps:LiteralData");								
								Element literalData = (Element) xpath.selectSingleNode(outputElement);
								runValue = Double.valueOf(literalData.getText());
							}
							else {
								throw new ProcessEvaluatorException("Process uses unsupported data type for " + output.getIdentifier() + " output.");
							}

							// add to map
							Double[] runResult = runResults.get(output);
							if (runResult == null) {
								runResult = new Double[design.getSize()];
							}
							runResult[run] = runValue;
							if (!runResults.containsKey(output)) {
								runResults.put(output, runResult);
							}
						}
						else {
							throw new ProcessEvaluatorException("Could not find " + output.getIdentifier() + " output in response.");
						}
					}
				}
			}
			
			// construct result object
			ProcessEvaluationResult result = new ProcessEvaluationResult();
			for (Output output : outputs) {
				result.addResults(output.getIdentifier(), runResults.get(output));
			}

			// return results
			return result;
		}
		catch (IOException e) {
			// malformed url or general io
			throw new ProcessEvaluatorException();
		}
		catch (JDOMException e) {
			// parsing schema problems
			throw new ProcessEvaluatorException();
		}
	}

	private Map<Input, String> getInputDataTypes(Element root) throws JDOMException {
		// map to fill
		Map<Input, String> inputDataTypes = new HashMap<Input, String>();

		// search for each element with name from outputs
		for (Input input : inputs) {
			// select input element
			XPath xpath = XPathWrapper.newInstance(".//Input[ows:Identifier = '" + input.getIdentifier() + "']");
			Element inputElement = (Element) xpath.selectSingleNode(root);

			if (inputElement != null) {
				String dataType = getParameterDataType(inputElement);
				inputDataTypes.put(input, dataType);
			}
		}

		return inputDataTypes;
	}
	
	private Map<Output, String> getOutputDataTypes(Element root) throws JDOMException {
		// map to fill
		Map<Output, String> outputDataTypes = new HashMap<Output, String>();

		// search for each element with name from outputs
		for (Output output : outputs) {
			// select input element
			XPath xpath = XPathWrapper.newInstance(".//Output[ows:Identifier = '" + output.getIdentifier() + "']");
			Element outputElement = (Element) xpath.selectSingleNode(root);

			if (outputElement != null) {
				String dataType = getParameterDataType(outputElement);
				outputDataTypes.put(output, dataType);
			}
		}

		return outputDataTypes;
	}

	private String getParameterDataType(Element parameterElement) throws JDOMException {
		// ok found element, try literal type
		XPath xpath = XPathWrapper.newInstance("*[name() = 'LiteralData' or name() = 'LiteralOutput']");
		Element dataElement = (Element) xpath.selectSingleNode(parameterElement);
		if (dataElement != null) {
			Element dataType = dataElement.getChild("DataType", Namespaces.OWS);
			if (dataType != null) {
				// hack
				return dataType.getAttributeValue("reference", Namespaces.OWS).split(":")[1];				
			}
		}
		
		// TODO: must be unsupported/reference, ignored exception?
		return null;
	}

}
