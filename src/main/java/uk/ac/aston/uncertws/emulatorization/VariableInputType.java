/*
 * XML Type:  VariableInputType
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.VariableInputType
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization;


/**
 * An XML VariableInputType(@http://uncertws.aston.ac.uk/emulatorization).
 *
 * This is a complex type.
 */
public interface VariableInputType extends uk.ac.aston.uncertws.emulatorization.InputType
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(VariableInputType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("variableinputtypee291type");
    
    /**
     * Gets the "min" element
     */
    double getMin();
    
    /**
     * Gets (as xml) the "min" element
     */
    org.apache.xmlbeans.XmlDouble xgetMin();
    
    /**
     * Sets the "min" element
     */
    void setMin(double min);
    
    /**
     * Sets (as xml) the "min" element
     */
    void xsetMin(org.apache.xmlbeans.XmlDouble min);
    
    /**
     * Gets the "max" element
     */
    double getMax();
    
    /**
     * Gets (as xml) the "max" element
     */
    org.apache.xmlbeans.XmlDouble xgetMax();
    
    /**
     * Sets the "max" element
     */
    void setMax(double max);
    
    /**
     * Sets (as xml) the "max" element
     */
    void xsetMax(org.apache.xmlbeans.XmlDouble max);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType newInstance() {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.VariableInputType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.VariableInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
