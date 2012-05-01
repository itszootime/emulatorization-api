package org.uncertweb.xml;
import org.jdom.Namespace;


public class Namespaces {
	
	public static final Namespace WSDL = Namespace.getNamespace("wsdl", "http://schemas.xmlsoap.org/wsdl/");
	public static final Namespace XSD = Namespace.getNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
	public static final Namespace PS = Namespace.getNamespace("ps", "http://www.uncertweb.org/ProcessingService");
	public static final Namespace SOAPENV = Namespace.getNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
	public static final Namespace WPS = Namespace.getNamespace("wps", "http://www.opengis.net/wps/1.0.0");
	public static final Namespace OWS = Namespace.getNamespace("ows", "http://www.opengis.net/ows/1.1");
	
}
