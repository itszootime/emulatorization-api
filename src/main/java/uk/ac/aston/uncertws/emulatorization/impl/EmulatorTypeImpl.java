/*
 * XML Type:  EmulatorType
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.EmulatorType
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization.impl;
/**
 * An XML EmulatorType(@http://uncertws.aston.ac.uk/emulatorization).
 *
 * This is a complex type.
 */
public class EmulatorTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.ac.aston.uncertws.emulatorization.EmulatorType
{
    private static final long serialVersionUID = 1L;
    
    public EmulatorTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INPUTS$0 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "inputs");
    private static final javax.xml.namespace.QName OUTPUTS$2 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "outputs");
    private static final javax.xml.namespace.QName DESIGN$4 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "design");
    private static final javax.xml.namespace.QName EVALUATIONRESULT$6 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "evaluationResult");
    private static final javax.xml.namespace.QName MEANFUNCTION$8 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "meanFunction");
    private static final javax.xml.namespace.QName COVARIANCEFUNCTION$10 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "covarianceFunction");
    private static final javax.xml.namespace.QName LENGTHSCALES$12 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "lengthScales");
    private static final javax.xml.namespace.QName NUGGETVARIANCE$14 = 
        new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "nuggetVariance");
    
    
    /**
     * Gets the "inputs" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs getInputs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs)get_store().find_element_user(INPUTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "inputs" element
     */
    public void setInputs(uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs inputs)
    {
        generatedSetterHelperImpl(inputs, INPUTS$0, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "inputs" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs addNewInputs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs)get_store().add_element_user(INPUTS$0);
            return target;
        }
    }
    
    /**
     * Gets the "outputs" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs getOutputs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs)get_store().find_element_user(OUTPUTS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "outputs" element
     */
    public void setOutputs(uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs outputs)
    {
        generatedSetterHelperImpl(outputs, OUTPUTS$2, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "outputs" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs addNewOutputs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs)get_store().add_element_user(OUTPUTS$2);
            return target;
        }
    }
    
    /**
     * Gets the "design" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.Design getDesign()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.Design target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.Design)get_store().find_element_user(DESIGN$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "design" element
     */
    public void setDesign(uk.ac.aston.uncertws.emulatorization.EmulatorType.Design design)
    {
        generatedSetterHelperImpl(design, DESIGN$4, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "design" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.Design addNewDesign()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.Design target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.Design)get_store().add_element_user(DESIGN$4);
            return target;
        }
    }
    
    /**
     * Gets the "evaluationResult" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult getEvaluationResult()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult)get_store().find_element_user(EVALUATIONRESULT$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "evaluationResult" element
     */
    public void setEvaluationResult(uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult evaluationResult)
    {
        generatedSetterHelperImpl(evaluationResult, EVALUATIONRESULT$6, 0, org.apache.xmlbeans.impl.values.XmlObjectBase.KIND_SETTERHELPER_SINGLETON);
    }
    
    /**
     * Appends and returns a new empty "evaluationResult" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult addNewEvaluationResult()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult)get_store().add_element_user(EVALUATIONRESULT$6);
            return target;
        }
    }
    
    /**
     * Gets the "meanFunction" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction.Enum getMeanFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MEANFUNCTION$8, 0);
            if (target == null)
            {
                return null;
            }
            return (uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "meanFunction" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction xgetMeanFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction)get_store().find_element_user(MEANFUNCTION$8, 0);
            return target;
        }
    }
    
    /**
     * Sets the "meanFunction" element
     */
    public void setMeanFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction.Enum meanFunction)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MEANFUNCTION$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MEANFUNCTION$8);
            }
            target.setEnumValue(meanFunction);
        }
    }
    
    /**
     * Sets (as xml) the "meanFunction" element
     */
    public void xsetMeanFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction meanFunction)
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction)get_store().find_element_user(MEANFUNCTION$8, 0);
            if (target == null)
            {
                target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction)get_store().add_element_user(MEANFUNCTION$8);
            }
            target.set(meanFunction);
        }
    }
    
    /**
     * Gets the "covarianceFunction" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction.Enum getCovarianceFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COVARIANCEFUNCTION$10, 0);
            if (target == null)
            {
                return null;
            }
            return (uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "covarianceFunction" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction xgetCovarianceFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction)get_store().find_element_user(COVARIANCEFUNCTION$10, 0);
            return target;
        }
    }
    
    /**
     * Sets the "covarianceFunction" element
     */
    public void setCovarianceFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction.Enum covarianceFunction)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COVARIANCEFUNCTION$10, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COVARIANCEFUNCTION$10);
            }
            target.setEnumValue(covarianceFunction);
        }
    }
    
    /**
     * Sets (as xml) the "covarianceFunction" element
     */
    public void xsetCovarianceFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction covarianceFunction)
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction)get_store().find_element_user(COVARIANCEFUNCTION$10, 0);
            if (target == null)
            {
                target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction)get_store().add_element_user(COVARIANCEFUNCTION$10);
            }
            target.set(covarianceFunction);
        }
    }
    
    /**
     * Gets the "lengthScales" element
     */
    public java.util.List getLengthScales()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LENGTHSCALES$12, 0);
            if (target == null)
            {
                return null;
            }
            return target.getListValue();
        }
    }
    
    /**
     * Gets (as xml) the "lengthScales" element
     */
    public uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales xgetLengthScales()
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales)get_store().find_element_user(LENGTHSCALES$12, 0);
            return target;
        }
    }
    
    /**
     * Sets the "lengthScales" element
     */
    public void setLengthScales(java.util.List lengthScales)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LENGTHSCALES$12, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LENGTHSCALES$12);
            }
            target.setListValue(lengthScales);
        }
    }
    
    /**
     * Sets (as xml) the "lengthScales" element
     */
    public void xsetLengthScales(uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales lengthScales)
    {
        synchronized (monitor())
        {
            check_orphaned();
            uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales target = null;
            target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales)get_store().find_element_user(LENGTHSCALES$12, 0);
            if (target == null)
            {
                target = (uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales)get_store().add_element_user(LENGTHSCALES$12);
            }
            target.set(lengthScales);
        }
    }
    
    /**
     * Gets the "nuggetVariance" element
     */
    public double getNuggetVariance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUGGETVARIANCE$14, 0);
            if (target == null)
            {
                return 0.0;
            }
            return target.getDoubleValue();
        }
    }
    
    /**
     * Gets (as xml) the "nuggetVariance" element
     */
    public org.apache.xmlbeans.XmlDouble xgetNuggetVariance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(NUGGETVARIANCE$14, 0);
            return target;
        }
    }
    
    /**
     * True if has "nuggetVariance" element
     */
    public boolean isSetNuggetVariance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NUGGETVARIANCE$14) != 0;
        }
    }
    
    /**
     * Sets the "nuggetVariance" element
     */
    public void setNuggetVariance(double nuggetVariance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUGGETVARIANCE$14, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NUGGETVARIANCE$14);
            }
            target.setDoubleValue(nuggetVariance);
        }
    }
    
    /**
     * Sets (as xml) the "nuggetVariance" element
     */
    public void xsetNuggetVariance(org.apache.xmlbeans.XmlDouble nuggetVariance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(NUGGETVARIANCE$14, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDouble)get_store().add_element_user(NUGGETVARIANCE$14);
            }
            target.set(nuggetVariance);
        }
    }
    
    /**
     * Unsets the "nuggetVariance" element
     */
    public void unsetNuggetVariance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NUGGETVARIANCE$14, 0);
        }
    }
    /**
     * An XML inputs(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public static class InputsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs
    {
        private static final long serialVersionUID = 1L;
        
        public InputsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName FIXEDINPUT$0 = 
            new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "FixedInput");
        private static final javax.xml.namespace.QName VARIABLEINPUT$2 = 
            new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "VariableInput");
        
        
        /**
         * Gets array of all "FixedInput" elements
         */
        public uk.ac.aston.uncertws.emulatorization.FixedInputType[] getFixedInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(FIXEDINPUT$0, targetList);
                uk.ac.aston.uncertws.emulatorization.FixedInputType[] result = new uk.ac.aston.uncertws.emulatorization.FixedInputType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "FixedInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.FixedInputType getFixedInputArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.FixedInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.FixedInputType)get_store().find_element_user(FIXEDINPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "FixedInput" element
         */
        public int sizeOfFixedInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(FIXEDINPUT$0);
            }
        }
        
        /**
         * Sets array of all "FixedInput" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setFixedInputArray(uk.ac.aston.uncertws.emulatorization.FixedInputType[] fixedInputArray)
        {
            check_orphaned();
            arraySetterHelper(fixedInputArray, FIXEDINPUT$0);
        }
        
        /**
         * Sets ith "FixedInput" element
         */
        public void setFixedInputArray(int i, uk.ac.aston.uncertws.emulatorization.FixedInputType fixedInput)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.FixedInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.FixedInputType)get_store().find_element_user(FIXEDINPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(fixedInput);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "FixedInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.FixedInputType insertNewFixedInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.FixedInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.FixedInputType)get_store().insert_element_user(FIXEDINPUT$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "FixedInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.FixedInputType addNewFixedInput()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.FixedInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.FixedInputType)get_store().add_element_user(FIXEDINPUT$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "FixedInput" element
         */
        public void removeFixedInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(FIXEDINPUT$0, i);
            }
        }
        
        /**
         * Gets array of all "VariableInput" elements
         */
        public uk.ac.aston.uncertws.emulatorization.VariableInputType[] getVariableInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(VARIABLEINPUT$2, targetList);
                uk.ac.aston.uncertws.emulatorization.VariableInputType[] result = new uk.ac.aston.uncertws.emulatorization.VariableInputType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "VariableInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.VariableInputType getVariableInputArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.VariableInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.VariableInputType)get_store().find_element_user(VARIABLEINPUT$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "VariableInput" element
         */
        public int sizeOfVariableInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(VARIABLEINPUT$2);
            }
        }
        
        /**
         * Sets array of all "VariableInput" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setVariableInputArray(uk.ac.aston.uncertws.emulatorization.VariableInputType[] variableInputArray)
        {
            check_orphaned();
            arraySetterHelper(variableInputArray, VARIABLEINPUT$2);
        }
        
        /**
         * Sets ith "VariableInput" element
         */
        public void setVariableInputArray(int i, uk.ac.aston.uncertws.emulatorization.VariableInputType variableInput)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.VariableInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.VariableInputType)get_store().find_element_user(VARIABLEINPUT$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(variableInput);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "VariableInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.VariableInputType insertNewVariableInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.VariableInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.VariableInputType)get_store().insert_element_user(VARIABLEINPUT$2, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "VariableInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.VariableInputType addNewVariableInput()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.VariableInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.VariableInputType)get_store().add_element_user(VARIABLEINPUT$2);
                return target;
            }
        }
        
        /**
         * Removes the ith "VariableInput" element
         */
        public void removeVariableInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(VARIABLEINPUT$2, i);
            }
        }
    }
    /**
     * An XML outputs(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public static class OutputsImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs
    {
        private static final long serialVersionUID = 1L;
        
        public OutputsImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName OUTPUT$0 = 
            new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "Output");
        
        
        /**
         * Gets array of all "Output" elements
         */
        public uk.ac.aston.uncertws.emulatorization.OutputType[] getOutputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(OUTPUT$0, targetList);
                uk.ac.aston.uncertws.emulatorization.OutputType[] result = new uk.ac.aston.uncertws.emulatorization.OutputType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "Output" element
         */
        public uk.ac.aston.uncertws.emulatorization.OutputType getOutputArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.OutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.OutputType)get_store().find_element_user(OUTPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "Output" element
         */
        public int sizeOfOutputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(OUTPUT$0);
            }
        }
        
        /**
         * Sets array of all "Output" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setOutputArray(uk.ac.aston.uncertws.emulatorization.OutputType[] outputArray)
        {
            check_orphaned();
            arraySetterHelper(outputArray, OUTPUT$0);
        }
        
        /**
         * Sets ith "Output" element
         */
        public void setOutputArray(int i, uk.ac.aston.uncertws.emulatorization.OutputType output)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.OutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.OutputType)get_store().find_element_user(OUTPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(output);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "Output" element
         */
        public uk.ac.aston.uncertws.emulatorization.OutputType insertNewOutput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.OutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.OutputType)get_store().insert_element_user(OUTPUT$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "Output" element
         */
        public uk.ac.aston.uncertws.emulatorization.OutputType addNewOutput()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.OutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.OutputType)get_store().add_element_user(OUTPUT$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "Output" element
         */
        public void removeOutput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(OUTPUT$0, i);
            }
        }
    }
    /**
     * An XML design(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public static class DesignImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.ac.aston.uncertws.emulatorization.EmulatorType.Design
    {
        private static final long serialVersionUID = 1L;
        
        public DesignImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName SIZE$0 = 
            new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "size");
        private static final javax.xml.namespace.QName DESIGNINPUT$2 = 
            new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "DesignInput");
        
        
        /**
         * Gets the "size" element
         */
        public java.math.BigInteger getSize()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SIZE$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getBigIntegerValue();
            }
        }
        
        /**
         * Gets (as xml) the "size" element
         */
        public org.apache.xmlbeans.XmlInteger xgetSize()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(SIZE$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "size" element
         */
        public void setSize(java.math.BigInteger size)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SIZE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SIZE$0);
                }
                target.setBigIntegerValue(size);
            }
        }
        
        /**
         * Sets (as xml) the "size" element
         */
        public void xsetSize(org.apache.xmlbeans.XmlInteger size)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInteger target = null;
                target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(SIZE$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(SIZE$0);
                }
                target.set(size);
            }
        }
        
        /**
         * Gets array of all "DesignInput" elements
         */
        public uk.ac.aston.uncertws.emulatorization.DesignInputType[] getDesignInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(DESIGNINPUT$2, targetList);
                uk.ac.aston.uncertws.emulatorization.DesignInputType[] result = new uk.ac.aston.uncertws.emulatorization.DesignInputType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "DesignInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.DesignInputType getDesignInputArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.DesignInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.DesignInputType)get_store().find_element_user(DESIGNINPUT$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "DesignInput" element
         */
        public int sizeOfDesignInputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(DESIGNINPUT$2);
            }
        }
        
        /**
         * Sets array of all "DesignInput" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setDesignInputArray(uk.ac.aston.uncertws.emulatorization.DesignInputType[] designInputArray)
        {
            check_orphaned();
            arraySetterHelper(designInputArray, DESIGNINPUT$2);
        }
        
        /**
         * Sets ith "DesignInput" element
         */
        public void setDesignInputArray(int i, uk.ac.aston.uncertws.emulatorization.DesignInputType designInput)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.DesignInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.DesignInputType)get_store().find_element_user(DESIGNINPUT$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(designInput);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "DesignInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.DesignInputType insertNewDesignInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.DesignInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.DesignInputType)get_store().insert_element_user(DESIGNINPUT$2, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "DesignInput" element
         */
        public uk.ac.aston.uncertws.emulatorization.DesignInputType addNewDesignInput()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.DesignInputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.DesignInputType)get_store().add_element_user(DESIGNINPUT$2);
                return target;
            }
        }
        
        /**
         * Removes the ith "DesignInput" element
         */
        public void removeDesignInput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(DESIGNINPUT$2, i);
            }
        }
    }
    /**
     * An XML evaluationResult(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public static class EvaluationResultImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult
    {
        private static final long serialVersionUID = 1L;
        
        public EvaluationResultImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName EVALUATIONOUTPUT$0 = 
            new javax.xml.namespace.QName("http://uncertws.aston.ac.uk/emulatorization", "EvaluationOutput");
        
        
        /**
         * Gets array of all "EvaluationOutput" elements
         */
        public uk.ac.aston.uncertws.emulatorization.EvaluationOutputType[] getEvaluationOutputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(EVALUATIONOUTPUT$0, targetList);
                uk.ac.aston.uncertws.emulatorization.EvaluationOutputType[] result = new uk.ac.aston.uncertws.emulatorization.EvaluationOutputType[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "EvaluationOutput" element
         */
        public uk.ac.aston.uncertws.emulatorization.EvaluationOutputType getEvaluationOutputArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.EvaluationOutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.EvaluationOutputType)get_store().find_element_user(EVALUATIONOUTPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "EvaluationOutput" element
         */
        public int sizeOfEvaluationOutputArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(EVALUATIONOUTPUT$0);
            }
        }
        
        /**
         * Sets array of all "EvaluationOutput" element  WARNING: This method is not atomicaly synchronized.
         */
        public void setEvaluationOutputArray(uk.ac.aston.uncertws.emulatorization.EvaluationOutputType[] evaluationOutputArray)
        {
            check_orphaned();
            arraySetterHelper(evaluationOutputArray, EVALUATIONOUTPUT$0);
        }
        
        /**
         * Sets ith "EvaluationOutput" element
         */
        public void setEvaluationOutputArray(int i, uk.ac.aston.uncertws.emulatorization.EvaluationOutputType evaluationOutput)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.EvaluationOutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.EvaluationOutputType)get_store().find_element_user(EVALUATIONOUTPUT$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(evaluationOutput);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "EvaluationOutput" element
         */
        public uk.ac.aston.uncertws.emulatorization.EvaluationOutputType insertNewEvaluationOutput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.EvaluationOutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.EvaluationOutputType)get_store().insert_element_user(EVALUATIONOUTPUT$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "EvaluationOutput" element
         */
        public uk.ac.aston.uncertws.emulatorization.EvaluationOutputType addNewEvaluationOutput()
        {
            synchronized (monitor())
            {
                check_orphaned();
                uk.ac.aston.uncertws.emulatorization.EvaluationOutputType target = null;
                target = (uk.ac.aston.uncertws.emulatorization.EvaluationOutputType)get_store().add_element_user(EVALUATIONOUTPUT$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "EvaluationOutput" element
         */
        public void removeEvaluationOutput(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(EVALUATIONOUTPUT$0, i);
            }
        }
    }
    /**
     * An XML meanFunction(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is an atomic type that is a restriction of uk.ac.aston.uncertws.emulatorization.EmulatorType$MeanFunction.
     */
    public static class MeanFunctionImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction
    {
        private static final long serialVersionUID = 1L;
        
        public MeanFunctionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected MeanFunctionImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
    /**
     * An XML covarianceFunction(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is an atomic type that is a restriction of uk.ac.aston.uncertws.emulatorization.EmulatorType$CovarianceFunction.
     */
    public static class CovarianceFunctionImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction
    {
        private static final long serialVersionUID = 1L;
        
        public CovarianceFunctionImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected CovarianceFunctionImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
    /**
     * An XML lengthScales(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a list type whose items are org.apache.xmlbeans.XmlDouble.
     */
    public static class LengthScalesImpl extends org.apache.xmlbeans.impl.values.XmlListImpl implements uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales
    {
        private static final long serialVersionUID = 1L;
        
        public LengthScalesImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected LengthScalesImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
