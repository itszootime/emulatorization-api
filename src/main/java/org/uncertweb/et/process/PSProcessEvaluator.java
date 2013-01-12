package org.uncertweb.et.process;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
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

public class PSProcessEvaluator extends AbstractProcessEvaluator {
	
	private static final Logger logger = LoggerFactory.getLogger(PSProcessEvaluator.class);

	public PSProcessEvaluator(String serviceURL, String processIdentifier, List<Input> inputs, List<Output> outputs, Design design) {
		super(serviceURL, processIdentifier, inputs, outputs, design);
	}

	public ProcessEvaluationResult evaluate() throws ProcessEvaluatorException {
		try {
			// build schema document
			SAXBuilder builder = new SAXBuilder();
			Document schema = builder.build(new URL(serviceURL + "?schema"));
			Element schemaRoot = schema.getRootElement();

			if (schemaRoot.getName().equals("schema") && schemaRoot.getNamespaceURI().equals(Namespaces.XSD.getURI())) {
				// our objective here is to find the type of data for each input or output
				Map<Input, QName> inputDataTypes = getInputDataTypes(schemaRoot);
				Map<Output, QName> outputDataTypes = getOutputDataTypes(schemaRoot);
				
				// get an ordered list of input identifiers
				List<String> orderedInputIdentifiers = new ArrayList<String>();
				List<?> inputElementList = XPathWrapper.newInstance("//xsd:element[@name='" + processIdentifier + "Request']/xsd:complexType/xsd:sequence/xsd:element[@name!='RequestedOutputs']").selectNodes(schemaRoot);
				for (Object o : inputElementList) {
					orderedInputIdentifiers.add(((Element)o).getAttributeValue("name"));
				}
				
				// do evaluate
				Map<Output, Double[]> runResults = new HashMap<Output, Double[]>();
				int step = (int)Math.floor(design.getSize() * 0.25);
				for (int run = 0; run < design.getSize(); run++) {
					// print log message
					if ((run + 1) % step == 0 || run == 0) {
						logger.info("Performing run " + (run + 1) + " of " + design.getSize() + "...");
					}
										
					// create request
					Document document = new Document();
					Element request = new Element(processIdentifier + "Request", Namespaces.PS);
					document.setRootElement(new Element("Envelope", Namespaces.SOAPENV)
					.addContent(new Element("Header", Namespaces.SOAPENV))
					.addContent(new Element("Body", Namespaces.SOAPENV)
					.addContent(request)));

					// add each input
					for (String inputIdentifier : orderedInputIdentifiers) {
						// get input
						// TODO: not massively efficient
						Input input = null;
						for (Input currentInput : inputs) {
							if (currentInput.getIdentifier().equals(inputIdentifier)) {
								input = currentInput;
								break;
							}
						}
						
						// create element
						Element inputElement = new Element(input.getIdentifier(), Namespaces.PS);
						request.addContent(inputElement);

						// get value
						// FIXME: really messy - some designs have fixed inputs, some don't....
						double value;
						if (design.getInputIdentifiers().contains(input.getIdentifier())) {
							value = design.getPoints(input.getIdentifier())[run];
						}
						else {
							value = input.getAsConstantInput().getValue();
						}

						// get type to cast into
						QName type = inputDataTypes.get(input);
						// FIXME: type will be null if an input can't be found in the service description
						if (type.getNamespaceURI().equals(Namespaces.XSD.getURI())) {
							// simple
							inputElement.setText(String.valueOf(value));
						}
						else {
							throw new ProcessEvaluatorException("Process uses unsupported data type for " + input.getIdentifier() + "input.");
						}
					}
					
					// TODO: add requested outputs here

					// evaluate
					HttpURLConnection connection = (HttpURLConnection) new URL(serviceURL + "/soap").openConnection();
					connection.setDoOutput(true);
					new XMLOutputter().output(document, connection.getOutputStream());

					// get response
					Document responseDocument = new SAXBuilder().build(connection.getInputStream());
					Element responseEnvelope = responseDocument.getRootElement();
					Element responseBody = responseEnvelope.getChild("Body", Namespaces.SOAPENV);

					// see if we got a fault
					Element faultElement = responseBody.getChild("Fault", Namespaces.SOAPENV);
					if (faultElement != null) {
						// faultstring
						// faultcode
						// detail
						Element detail = faultElement.getChild("detail");
						// FIXME: should never be null?
						logger.debug("Failed at run " + (run + 1) + ".");
						if (detail != null) {
							logger.debug("Exception detail is: " + detail.getChildText("exception", Namespaces.PS));
						}
						
						// get faultstring
						String message = faultElement.getChildText("faultstring");
						throw new ProcessEvaluatorException(message);
					}
					else {
						Element responseElement = responseBody.getChild(processIdentifier + "Response", Namespaces.PS);

						// get outputs from response
						for (Output output : outputs) {
							// get element
							Element outputElement = responseElement.getChild(output.getIdentifier(), Namespaces.PS);
							
							// if exists
							if (outputElement != null) {
								// find value
								double runValue;

								// get type to cast from
								QName type = outputDataTypes.get(output);
								if (type.getNamespaceURI().equals(Namespaces.XSD.getURI())) {
									// simple!
									runValue = Double.valueOf(outputElement.getText());
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
				
				//logger.debug("All done.");
				//for (String identifier : result.getOutputIdentifiers()) {
				//	logger.debug(identifier + ": " + Arrays.toString(result.getResults(identifier)));
				//}

				// return results
				return result;
			}
			else {
				// no schema found
				throw new ProcessEvaluatorException();
			}
		}
		catch (IOException e) {
			// malformed url or general io
			throw new ProcessEvaluatorException();
		}
		catch (JDOMException e) {
			// parsing schema problems
			// TODO: also get here if user gives invalid url
			throw new ProcessEvaluatorException();
		}
	}

	private Map<Input, QName> getInputDataTypes(Element root) throws JDOMException {
		// map to fill
		Map<Input, QName> inputDataTypes = new HashMap<Input, QName>();

		// select request element for process
		XPath xpath = XPathWrapper.newInstance("//xsd:element[@name='" + processIdentifier + "Request']");
		Element requestElement = (Element) xpath.selectSingleNode(root);

		if (requestElement != null) {
			// search for each element with name from outputs
			for (Input input : inputs) {
				// select input element
				xpath = XPathWrapper.newInstance(".//xsd:element[@name='" + input.getIdentifier() + "']");
				Element inputElement = (Element) xpath.selectSingleNode(requestElement);

				if (inputElement != null) {
					QName dataType = getParameterDataType(inputElement);
					inputDataTypes.put(input, dataType);
				}
			}
		}
		else {
			// TODO: exception?
		}

		return inputDataTypes;
	}

	private Map<Output, QName> getOutputDataTypes(Element root) throws JDOMException {
		// map to fill
		Map<Output, QName> outputDataTypes = new HashMap<Output, QName>();

		// select request element for process
		XPath xpath = XPathWrapper.newInstance("//xsd:element[@name='" + processIdentifier + "Response']");
		Element responseElement = (Element) xpath.selectSingleNode(root);

		if (responseElement != null) {
			// search for each element with name from outputs
			for (Output output : outputs) {
				// select input element
				xpath = XPathWrapper.newInstance(".//xsd:element[@name='" + output.getIdentifier() + "']");
				Element outputElement = (Element) xpath.selectSingleNode(responseElement);

				if (outputElement != null) {
					QName dataType = getParameterDataType(outputElement);
					outputDataTypes.put(output, dataType);
				}
				else {
					// TODO: exception?
				}
			}
		}
		else {
			// TODO: exception?
		}

		return outputDataTypes;
	}

	private QName getParameterDataType(Element parameterElement) throws JDOMException {
		// could be: complexType/choice/element[@name='Data']/@type = xsd:double, etc...
		// could be: complexType/choice/element[@name='Data']/complexType/sequence/element/@ref = gml:Point, etc...
		// could be: complexType/choice/element[@name='Data']/simpleType/list/@itemType = xsd:double, etc...
		// TODO: reference only (ie. binary) we will ignore for now
		// TODO: fix workarounds for xpath not working properly
		
		// ok found element, try simple type
		Attribute typeAttr;
		XPath xpath = XPathWrapper.newInstance("./@type");
		typeAttr = (Attribute) xpath.selectSingleNode(parameterElement);

		// then try list
		if (typeAttr == null) {
			xpath = XPathWrapper.newInstance("./xsd:list/@itemType");
			typeAttr = (Attribute) xpath.selectSingleNode(parameterElement);
		}

		// then try ref
		if	(typeAttr == null) {
			xpath = XPathWrapper.newInstance(".//xsd:element[@name!='DataReference']/@ref");
			typeAttr = (Attribute) xpath.selectSingleNode(parameterElement);
		}

		// check if found a type
		if (typeAttr != null) {
			String[] qName = typeAttr.getValue().split(":");
			Namespace namespace = typeAttr.getParent().getNamespace(qName[0]);
			return new QName(namespace.getURI(), qName[1]);
		}
		else {
			// TODO: must be a reference, ignored exception?
			return null;
		}
	}

}
