/*
 * An XML document type.
 * Localname: Emulator
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.EmulatorDocument
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization;


/**
 * A document containing one Emulator(@http://uncertws.aston.ac.uk/emulatorization) element.
 *
 * This is a complex type.
 */
public interface EmulatorDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(EmulatorDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("emulator6b20doctype");
    
    /**
     * Gets the "Emulator" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType getEmulator();
    
    /**
     * Sets the "Emulator" element
     */
    void setEmulator(uk.ac.aston.uncertws.emulatorization.EmulatorType emulator);
    
    /**
     * Appends and returns a new empty "Emulator" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType addNewEmulator();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument newInstance() {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
