/*
 * XML Type:  VariableInputType
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.VariableInputType
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization.impl;
/**
 * An XML VariableInputType(@http://uncertws.aston.ac.uk/emulatorization).
 *
 * This is a complex type.
 */
public class VariableInputTypeImpl extends uk.ac.aston.uncertws.emulatorization.impl.InputTypeImpl implements uk.ac.aston.uncertws.emulatorization.VariableInputType
{
    private static final long serialVersionUID = 1L;
    
    public VariableInputTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MIN$0 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "min");
    private static final javax.xml.namespace.QName MAX$2 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "max");
    
    
    /**
     * Gets the "min" element
     */
    public double getMin()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MIN$0, 0);
            if (target == null)
            {
                return 0.0;
            }
            return target.getDoubleValue();
        }
    }
    
    /**
     * Gets (as xml) the "min" element
     */
    public org.apache.xmlbeans.XmlDouble xgetMin()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(MIN$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "min" element
     */
    public void setMin(double min)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MIN$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MIN$0);
            }
            target.setDoubleValue(min);
        }
    }
    
    /**
     * Sets (as xml) the "min" element
     */
    public void xsetMin(org.apache.xmlbeans.XmlDouble min)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(MIN$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDouble)get_store().add_element_user(MIN$0);
            }
            target.set(min);
        }
    }
    
    /**
     * Gets the "max" element
     */
    public double getMax()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MAX$2, 0);
            if (target == null)
            {
                return 0.0;
            }
            return target.getDoubleValue();
        }
    }
    
    /**
     * Gets (as xml) the "max" element
     */
    public org.apache.xmlbeans.XmlDouble xgetMax()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(MAX$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "max" element
     */
    public void setMax(double max)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MAX$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MAX$2);
            }
            target.setDoubleValue(max);
        }
    }
    
    /**
     * Sets (as xml) the "max" element
     */
    public void xsetMax(org.apache.xmlbeans.XmlDouble max)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(MAX$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDouble)get_store().add_element_user(MAX$2);
            }
            target.set(max);
        }
    }
}
