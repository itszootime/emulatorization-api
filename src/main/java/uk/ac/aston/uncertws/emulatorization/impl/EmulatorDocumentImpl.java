/*
 * An XML document type.
 * Localname: Emulator
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.EmulatorDocument
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization.impl;
/**
 * A document containing one Emulator(@http://uncertws.aston.ac.uk/emulatorization) element.
 *
 * This is a complex type.
 */
public class EmulatorDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.ac.aston.uncertws.emulatorization.EmulatorDocument
{
    private static final long serialVersionUID = 1L;
    
    public EmulatorDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EMULATOR$0 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "Emulator");
    
    
    /**
     * Gets the "Emulator" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType getEmulator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType)get_store().find_element_user(EMULATOR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Emulator" element
     */
    public void setEmulator(uk.ac.aston.uncertws.emulatorization.EmulatorType emulator)
    {
        generatedSetterHelperImpl(emulator, EMULATOR$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "Emulator" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType addNewEmulator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType)get_store().add_element_user(EMULATOR$0);
            return target;
        }
    }
}
