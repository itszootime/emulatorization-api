/*
 * XML Type:  DesignInputType
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.DesignInputType
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization;


/**
 * An XML DesignInputType(@http://uncertws.aston.ac.uk/emulatorization).
 *
 * This is a complex type.
 */
public interface DesignInputType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(DesignInputType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("designinputtype924ftype");
    
    /**
     * Gets the "identifier" element
     */
    java.lang.String getIdentifier();
    
    /**
     * Gets (as xml) the "identifier" element
     */
    org.apache.xmlbeans.XmlString xgetIdentifier();
    
    /**
     * Sets the "identifier" element
     */
    void setIdentifier(java.lang.String identifier);
    
    /**
     * Sets (as xml) the "identifier" element
     */
    void xsetIdentifier(org.apache.xmlbeans.XmlString identifier);
    
    /**
     * Gets the "points" element
     */
    java.util.List getPoints();
    
    /**
     * Gets (as xml) the "points" element
     */
    uk.ac.aston.uncertws.emulatorization.DesignInputType.Points xgetPoints();
    
    /**
     * Sets the "points" element
     */
    void setPoints(java.util.List points);
    
    /**
     * Sets (as xml) the "points" element
     */
    void xsetPoints(uk.ac.aston.uncertws.emulatorization.DesignInputType.Points points);
    
    /**
     * Gets the "mean" element
     */
    double getMean();
    
    /**
     * Gets (as xml) the "mean" element
     */
    org.apache.xmlbeans.XmlDouble xgetMean();
    
    /**
     * True if has "mean" element
     */
    boolean isSetMean();
    
    /**
     * Sets the "mean" element
     */
    void setMean(double mean);
    
    /**
     * Sets (as xml) the "mean" element
     */
    void xsetMean(org.apache.xmlbeans.XmlDouble mean);
    
    /**
     * Unsets the "mean" element
     */
    void unsetMean();
    
    /**
     * Gets the "stdDev" element
     */
    double getStdDev();
    
    /**
     * Gets (as xml) the "stdDev" element
     */
    org.apache.xmlbeans.XmlDouble xgetStdDev();
    
    /**
     * True if has "stdDev" element
     */
    boolean isSetStdDev();
    
    /**
     * Sets the "stdDev" element
     */
    void setStdDev(double stdDev);
    
    /**
     * Sets (as xml) the "stdDev" element
     */
    void xsetStdDev(org.apache.xmlbeans.XmlDouble stdDev);
    
    /**
     * Unsets the "stdDev" element
     */
    void unsetStdDev();
    
    /**
     * An XML points(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a list type whose items are org.apache.xmlbeans.XmlDouble.
     */
    public interface Points extends org.apache.xmlbeans.XmlAnySimpleType
    {
        java.util.List getListValue();
        java.util.List xgetListValue();
        void setListValue(java.util.List list);
        /** @deprecated */
        java.util.List listValue();
        /** @deprecated */
        java.util.List xlistValue();
        /** @deprecated */
        void set(java.util.List list);
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Points.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("points818eelemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.DesignInputType.Points newValue(java.lang.Object obj) {
              return (uk.ac.aston.uncertws.emulatorization.DesignInputType.Points) type.newValue( obj ); }
            
            public static uk.ac.aston.uncertws.emulatorization.DesignInputType.Points newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.DesignInputType.Points) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.DesignInputType.Points newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.DesignInputType.Points) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType newInstance() {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.DesignInputType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.DesignInputType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
