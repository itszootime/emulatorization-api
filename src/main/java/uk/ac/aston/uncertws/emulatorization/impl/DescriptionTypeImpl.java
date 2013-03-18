/*
 * XML Type:  DescriptionType
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.DescriptionType
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization.impl;
/**
 * An XML DescriptionType(@http://uncertws.aston.ac.uk/emulatorization).
 *
 * This is a complex type.
 */
public class DescriptionTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.ac.aston.uncertws.emulatorization.DescriptionType
{
    private static final long serialVersionUID = 1L;
    
    public DescriptionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DATATYPE$0 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "dataType");
    private static final javax.xml.namespace.QName ENCODINGTYPE$2 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "encodingType");
    private static final javax.xml.namespace.QName DETAIL$4 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "detail");
    private static final javax.xml.namespace.QName UOM$6 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "uom");
    
    
    /**
     * Gets the "dataType" element
     */
    public uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType.Enum getDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATATYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return (uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "dataType" element
     */
    public uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType xgetDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType target = null;
            target = (uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType)get_store().find_element_user(DATATYPE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "dataType" element
     */
    public void setDataType(uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType.Enum dataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATATYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DATATYPE$0);
            }
            target.setEnumValue(dataType);
        }
    }
    
    /**
     * Sets (as xml) the "dataType" element
     */
    public void xsetDataType(uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType dataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType target = null;
            target = (uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType)get_store().find_element_user(DATATYPE$0, 0);
            if (target == null)
            {
                target = (uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType)get_store().add_element_user(DATATYPE$0);
            }
            target.set(dataType);
        }
    }
    
    /**
     * Gets the "encodingType" element
     */
    public uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType.Enum getEncodingType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ENCODINGTYPE$2, 0);
            if (target == null)
            {
                return null;
            }
            return (uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "encodingType" element
     */
    public uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType xgetEncodingType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType target = null;
            target = (uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType)get_store().find_element_user(ENCODINGTYPE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "encodingType" element
     */
    public void setEncodingType(uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType.Enum encodingType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ENCODINGTYPE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ENCODINGTYPE$2);
            }
            target.setEnumValue(encodingType);
        }
    }
    
    /**
     * Sets (as xml) the "encodingType" element
     */
    public void xsetEncodingType(uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType encodingType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType target = null;
            target = (uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType)get_store().find_element_user(ENCODINGTYPE$2, 0);
            if (target == null)
            {
                target = (uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType)get_store().add_element_user(ENCODINGTYPE$2);
            }
            target.set(encodingType);
        }
    }
    
    /**
     * Gets the "detail" element
     */
    public java.lang.String getDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DETAIL$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "detail" element
     */
    public org.apache.xmlbeans.XmlString xgetDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DETAIL$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "detail" element
     */
    public boolean isSetDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DETAIL$4) != 0;
        }
    }
    
    /**
     * Sets the "detail" element
     */
    public void setDetail(java.lang.String detail)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DETAIL$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DETAIL$4);
            }
            target.setStringValue(detail);
        }
    }
    
    /**
     * Sets (as xml) the "detail" element
     */
    public void xsetDetail(org.apache.xmlbeans.XmlString detail)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DETAIL$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DETAIL$4);
            }
            target.set(detail);
        }
    }
    
    /**
     * Unsets the "detail" element
     */
    public void unsetDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DETAIL$4, 0);
        }
    }
    
    /**
     * Gets the "uom" element
     */
    public java.lang.String getUom()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UOM$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "uom" element
     */
    public org.apache.xmlbeans.XmlString xgetUom()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UOM$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "uom" element
     */
    public boolean isSetUom()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(UOM$6) != 0;
        }
    }
    
    /**
     * Sets the "uom" element
     */
    public void setUom(java.lang.String uom)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(UOM$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(UOM$6);
            }
            target.setStringValue(uom);
        }
    }
    
    /**
     * Sets (as xml) the "uom" element
     */
    public void xsetUom(org.apache.xmlbeans.XmlString uom)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(UOM$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(UOM$6);
            }
            target.set(uom);
        }
    }
    
    /**
     * Unsets the "uom" element
     */
    public void unsetUom()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(UOM$6, 0);
        }
    }
    /**
     * An XML dataType(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is an atomic type that is a restriction of uk.ac.aston.uncertws.emulatorization.DescriptionType$DataType.
     */
    public static class DataTypeImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements uk.ac.aston.uncertws.emulatorization.DescriptionType.DataType
    {
        private static final long serialVersionUID = 1L;
        
        public DataTypeImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected DataTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
    /**
     * An XML encodingType(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is an atomic type that is a restriction of uk.ac.aston.uncertws.emulatorization.DescriptionType$EncodingType.
     */
    public static class EncodingTypeImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements uk.ac.aston.uncertws.emulatorization.DescriptionType.EncodingType
    {
        private static final long serialVersionUID = 1L;
        
        public EncodingTypeImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected EncodingTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
