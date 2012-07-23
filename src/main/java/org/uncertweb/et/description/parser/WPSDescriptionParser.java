package org.uncertweb.et.description.parser;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.description.ProcessDescription;
import org.uncertweb.et.description.ServiceDescription;
import org.uncertweb.et.parameter.Input;
import org.uncertweb.et.parameter.Output;
import org.uncertweb.et.parameter.ParameterDescription;
import org.uncertweb.et.parameter.ParameterDescription.DataType;
import org.uncertweb.xml.Namespaces;
import org.uncertweb.xml.XPathWrapper;

public class WPSDescriptionParser extends AbstractServiceDescriptionParser {
	
	private static final Logger logger = LoggerFactory.getLogger(WPSDescriptionParser.class);

	public WPSDescriptionParser(String serviceURL) {
		super(serviceURL);
	}

	public ServiceDescription parse() throws ServiceDescriptionParserException {
		/**
		 * There's lots of elements here that are unqualified, but should maybe be in the WPS namespace?
		 */

		// first get capabilities
		Element capabilities;
		try {
			capabilities = new SAXBuilder().build(new URL(serviceURL + "?Service=WPS&Request=GetCapabilities").openStream()).getRootElement();
		}
		catch (IOException e) {
			throw new ServiceDescriptionParserException(e.getMessage());
		}
		catch (JDOMException e) {
			throw new ServiceDescriptionParserException(e.getMessage());
		}

		// create service description
		ServiceDescription serviceDescription = new ServiceDescription();

		// find process identifiers
		String processIdentifiers = "";
		try {
			XPath xpath = XPathWrapper.newInstance("//wps:Process/ows:Identifier");
			List<?> identifierNodes = xpath.selectNodes(capabilities);
			for (Object o : identifierNodes) {
				processIdentifiers += (((Element) o).getText()) + ",";
			}
		}
		catch (JDOMException e) {
			throw new ServiceDescriptionParserException(e.getMessage());
		}		

		if (!processIdentifiers.isEmpty()) {
			// strip last comma
			processIdentifiers = processIdentifiers.substring(0, processIdentifiers.length() - 1);

			// get descriptions document
			Element procDescsElement;
			try {
				procDescsElement = new SAXBuilder().build(new URL(serviceURL + "?Service=WPS&Request=DescribeProcess&Identifier=" + processIdentifiers).openStream()).getRootElement();
			}
			catch (IOException e) {
				throw new ServiceDescriptionParserException(e.getMessage());
			}
			catch (JDOMException e) {
				throw new ServiceDescriptionParserException(e.getMessage());
			}

			// then get description for each process
			try {
				XPath xpath = XPathWrapper.newInstance("//ProcessDescription");
				List<?> procDescNodes = xpath.selectNodes(procDescsElement);
				for (Object procDescNode : procDescNodes) {
					// get description element element
					Element procDescElement = (Element) procDescNode;

					// get identifier, abstract and create process description
					String processIdentifier = procDescElement.getChildText("Identifier", Namespaces.OWS);
					String processTitle = procDescElement.getChildText("Abstract", Namespaces.OWS); // could be null, abstract is optional
					ProcessDescription processDescription = new ProcessDescription(processIdentifier, processTitle);

					try {
						// parse inputs
						xpath = XPathWrapper.newInstance(".//Input");
						List<?> inputNodes = xpath.selectNodes(procDescElement);
						for (Object o : inputNodes) {
							Element inputElement = (Element) o;
							String identifier = inputElement.getChildText("Identifier", Namespaces.OWS);
							DataType dataType = parseParameterDataType(inputElement);
							int minOccurs = Integer.parseInt(inputElement.getAttributeValue("minOccurs"));
							int maxOccurs = Integer.parseInt(inputElement.getAttributeValue("maxOccurs"));
							processDescription.addInput(new Input(identifier, new ParameterDescription(dataType, minOccurs, maxOccurs)));
						}

						// parse outputs
						xpath = XPathWrapper.newInstance(".//Output");
						List<?> outputNodes = xpath.selectNodes(procDescElement);
						for (Object o : outputNodes) {
							Element outputElement = (Element) o;
							String identifier = outputElement.getChildText("Identifier", Namespaces.OWS);
							DataType dataType = parseParameterDataType(outputElement);
							processDescription.addOutput(new Output(identifier, new ParameterDescription(dataType)));
						}

						// add
						serviceDescription.addProcessDescription(processDescription);
					}
					catch (UnsupportedDataTypeException e) {
						// TODO: just log it, process won't be included in description
						logger.info("Found unsupported data type in process " + processIdentifier + ", skipped.");
					}		
				}
			}
			catch (JDOMException e) {
				// from parsing process description(s), input/output, data types
				throw new ServiceDescriptionParserException(e.getMessage());
			}
		}

		return serviceDescription;
	}

	private DataType parseParameterDataType(Element parameterElement) throws UnsupportedDataTypeException, JDOMException {
		// LiteralData
		XPath xpath = XPathWrapper.newInstance("*[name() = 'LiteralData' or name() = 'LiteralOutput']");
		Element dataElement = (Element) xpath.selectSingleNode(parameterElement);
		if (dataElement != null) {
			// ows:DataType (optional)
			// can't be bothered to lookup the structure of ows:DataType for now, use 52n conventions
			Element dataTypeElement = dataElement.getChild("DataType", Namespaces.OWS);
			if (dataTypeElement != null) {
				String reference = dataTypeElement.getAttributeValue("reference", Namespaces.OWS);
				// the above will be a QName, but wps doesn't seem to use them properly. hack
				String[] typeTokens = reference.split(":");
				if (typeTokens.length == 1) {
					// try eg. http://www.w3.org/TR/xmlschema-2/#double
					typeTokens = reference.split("#");
				}
				String type = typeTokens[typeTokens.length - 1];
				if (type.equals("double")) {
					return DataType.Numeric;
				}
			}

			// UOMs (output only has this + DataType)

			// LiteralValuesChoice: AllowedValues, AnyValue, ValuesReference

			// DefaultValue (optional)

		}

		// ComplexData

		// BoundingBoxData

		throw new UnsupportedDataTypeException();
	}

}
