package org.uncertweb.et.description.parser;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uncertweb.et.description.ParameterDescription;
import org.uncertweb.et.description.ProcessDescription;
import org.uncertweb.et.description.ServiceDescription;
import org.uncertweb.et.description.ParameterDescription.DataType;
import org.uncertweb.xml.Namespaces;
import org.uncertweb.xml.XPathWrapper;

public class PSDescriptionParser extends AbstractServiceDescriptionParser {
	
	private static Logger logger = LoggerFactory.getLogger(PSDescriptionParser.class);

	public PSDescriptionParser(String serviceURL) {
		super(serviceURL);
	}

	public ServiceDescription parse() throws ServiceDescriptionParserException {
		// get wsdl and schema
		Element wsdl = null;
		Element schema = null;
		try {
			wsdl = new SAXBuilder().build(new URL(serviceURL + "?wsdl").openStream()).getRootElement();
			schema = new SAXBuilder().build(new URL(serviceURL + "?schema").openStream()).getRootElement();
		}
		catch (IOException e) {
			throw new ServiceDescriptionParserException(e.getMessage());
		}
		catch (JDOMException e) {
			throw new ServiceDescriptionParserException(e.getMessage());
		}

		// no need for if statement as an exception is thrown if wsdl or schema aren't parsed
		try {
			// create description
			ServiceDescription serviceDescription = new ServiceDescription();

			// get operation identifiers
			List<?> operations = wsdl.getChild("portType", Namespaces.WSDL).getChildren("operation", Namespaces.WSDL);
			for (Object o : operations) {
				Element operation = (Element) o;
				String processIdentifier = operation.getAttributeValue("name");

				// create process
				ProcessDescription processDescription = new ProcessDescription(processIdentifier);

				try {
					// for each process, get inputs and outputs
					List<?> inputs = ((Element) XPathWrapper.newInstance("//xsd:element[@name = '" + processIdentifier + "Request']/xsd:complexType/xsd:sequence").selectSingleNode(schema)).getChildren();
					for (Object oo : inputs) {
						// get element
						Element input = (Element) oo;

						// skip the requested output element
						if (!input.getAttributeValue("name").equals("RequestedOutputs")) { 
							// get description, add to process
							ParameterDescription inputDescription = parseParameterDescription(input);
							processDescription.addInputDescription(inputDescription);
						}
					}

					List<?> outputs = ((Element) XPathWrapper.newInstance("//xsd:element[@name = '" + processIdentifier + "Response']/xsd:complexType/xsd:sequence").selectSingleNode(schema)).getChildren();
					for (Object oo : outputs) {
						// get element
						Element output = (Element) oo;

						// get description, add to process
						ParameterDescription outputDescription = parseParameterDescription(output);
						processDescription.addOutputDescription(outputDescription);
					}

					// add to service description
					serviceDescription.addProcessDescription(processDescription);
				}
				catch (UnsupportedDataTypeException e) {
					// just log it, process won't be added
					logger.info("Found unsupported data type in process " + processIdentifier + ", skipped.");
				}
			}

			return serviceDescription;
		}
		catch (JDOMException e) {
			throw new ServiceDescriptionParserException(e.getMessage());
		}
	}

	private static ParameterDescription parseParameterDescription(Element parameterElement) throws JDOMException, UnsupportedDataTypeException {
		// parameters to fill
		String identifier = parameterElement.getAttributeValue("name");
		DataType dataType = null;

		// check if really simple
		Element simple = parameterElement.getChild("simpleType", Namespaces.XSD);
		Element complex = parameterElement.getChild("complexType", Namespaces.XSD);
		if (simple == null && complex == null) {
			// lookup type
			String qualType = parameterElement.getAttributeValue("type");
			// this will be qualified. for now assume all are in xsd namespace
			String type = qualType.split(":")[1];

			// decide what type it is
			if (type.equals("double")) {
				dataType = DataType.Numeric;
			}
		}
		else {
			if (simple != null) {
				// list here
			}
			else {
				// must be complex/ref				
				Element choice = complex.getChild("choice", Namespaces.XSD);
				Element element = (Element) XPathWrapper.newInstance("xsd:element[@name != 'DataReference']").selectSingleNode(choice);
				if (element != null) {

				}
				else {
					// could be neither (ref only)

				}
			}
		}
		
		// try get some metadata
		String detail = null;
		Element annotation = parameterElement.getChild("annotation", Namespaces.XSD);
		if (annotation != null) {
			String documentation = annotation.getChildText("documentation", Namespaces.XSD);
			if (documentation != null) {
				// parse
				Map<String, String> metadata = new HashMap<String, String>();
				Pattern p = java.util.regex.Pattern.compile("@([a-z-]+)\\s([\\w\\s-\\.]+)");
				Matcher m = p.matcher(documentation);
				while (m.find()) {
					metadata.put(m.group(1), m.group(2));
				}
				if (metadata.containsKey("description")) {
					detail = metadata.get("description");
				}
			}
		}

		if (dataType != null) {
			ParameterDescription description = new ParameterDescription(identifier, dataType);
			description.setDetail(detail);
			return description;
		}
		else {
			throw new UnsupportedDataTypeException();
		}
	}
	
//	try {
//		String wsdlLocation = "http://localhost:8080/ps/service?wsdl";
//
//		SymbolTable symbolTable = new SymbolTable(new NoopFactory().getBaseTypeMapping(), true, false, false); // btm, addImports, verbose, nowrap
//		symbolTable.setQuiet(true);
//		symbolTable.populate(wsdlLocation);
//		
//		Definition definition = symbolTable.getDefinition();
//		Map bindings = definition.getBindings();
//		for (Object o : bindings.values()) {
//			Binding binding = (Binding) o;
//			for (Object oo : binding.getExtensibilityElements()) {
//				ExtensibilityElement ee = (ExtensibilityElement) oo;
//				if (ee instanceof SOAPBinding) {
//					PortType portType = binding.getPortType();
//					for (Object ooo : portType.getOperations()) {
//						Operation operation = (Operation) ooo;
//						
//						System.out.println(operation.getName());
//						
//						System.out.println("Input:");
//						System.out.println(operation.getInput().getMessage());
//						
//						System.out.println("Output:");
//						System.out.println(operation.getOutput().getMessage());
//					}
//				}
//			}
//		}
//	}
//	catch (Exception e) {
//		e.printStackTrace();
//	}	
}