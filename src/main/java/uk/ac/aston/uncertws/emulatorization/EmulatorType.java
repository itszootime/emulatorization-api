/*
 * XML Type:  EmulatorType
 * Namespace: http://uncertws.aston.ac.uk/emulatorization
 * Java type: uk.ac.aston.uncertws.emulatorization.EmulatorType
 *
 * Automatically generated - do not modify.
 */
package uk.ac.aston.uncertws.emulatorization;


/**
 * An XML EmulatorType(@http://uncertws.aston.ac.uk/emulatorization).
 *
 * This is a complex type.
 */
public interface EmulatorType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(EmulatorType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("emulatortype3a6atype");
    
    /**
     * Gets the "inputs" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs getInputs();
    
    /**
     * Sets the "inputs" element
     */
    void setInputs(uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs inputs);
    
    /**
     * Appends and returns a new empty "inputs" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs addNewInputs();
    
    /**
     * Gets the "outputs" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs getOutputs();
    
    /**
     * Sets the "outputs" element
     */
    void setOutputs(uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs outputs);
    
    /**
     * Appends and returns a new empty "outputs" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs addNewOutputs();
    
    /**
     * Gets the "design" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.Design getDesign();
    
    /**
     * Sets the "design" element
     */
    void setDesign(uk.ac.aston.uncertws.emulatorization.EmulatorType.Design design);
    
    /**
     * Appends and returns a new empty "design" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.Design addNewDesign();
    
    /**
     * Gets the "evaluationResult" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult getEvaluationResult();
    
    /**
     * Sets the "evaluationResult" element
     */
    void setEvaluationResult(uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult evaluationResult);
    
    /**
     * Appends and returns a new empty "evaluationResult" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult addNewEvaluationResult();
    
    /**
     * Gets the "meanFunction" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction.Enum getMeanFunction();
    
    /**
     * Gets (as xml) the "meanFunction" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction xgetMeanFunction();
    
    /**
     * Sets the "meanFunction" element
     */
    void setMeanFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction.Enum meanFunction);
    
    /**
     * Sets (as xml) the "meanFunction" element
     */
    void xsetMeanFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction meanFunction);
    
    /**
     * Gets the "covarianceFunction" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction.Enum getCovarianceFunction();
    
    /**
     * Gets (as xml) the "covarianceFunction" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction xgetCovarianceFunction();
    
    /**
     * Sets the "covarianceFunction" element
     */
    void setCovarianceFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction.Enum covarianceFunction);
    
    /**
     * Sets (as xml) the "covarianceFunction" element
     */
    void xsetCovarianceFunction(uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction covarianceFunction);
    
    /**
     * Gets the "lengthScales" element
     */
    java.util.List getLengthScales();
    
    /**
     * Gets (as xml) the "lengthScales" element
     */
    uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales xgetLengthScales();
    
    /**
     * Sets the "lengthScales" element
     */
    void setLengthScales(java.util.List lengthScales);
    
    /**
     * Sets (as xml) the "lengthScales" element
     */
    void xsetLengthScales(uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales lengthScales);
    
    /**
     * Gets the "nuggetVariance" element
     */
    double getNuggetVariance();
    
    /**
     * Gets (as xml) the "nuggetVariance" element
     */
    org.apache.xmlbeans.XmlDouble xgetNuggetVariance();
    
    /**
     * True if has "nuggetVariance" element
     */
    boolean isSetNuggetVariance();
    
    /**
     * Sets the "nuggetVariance" element
     */
    void setNuggetVariance(double nuggetVariance);
    
    /**
     * Sets (as xml) the "nuggetVariance" element
     */
    void xsetNuggetVariance(org.apache.xmlbeans.XmlDouble nuggetVariance);
    
    /**
     * Unsets the "nuggetVariance" element
     */
    void unsetNuggetVariance();
    
    /**
     * An XML inputs(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public interface Inputs extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Inputs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("inputs0ac5elemtype");
        
        /**
         * Gets array of all "FixedInput" elements
         */
        uk.ac.aston.uncertws.emulatorization.FixedInputType[] getFixedInputArray();
        
        /**
         * Gets ith "FixedInput" element
         */
        uk.ac.aston.uncertws.emulatorization.FixedInputType getFixedInputArray(int i);
        
        /**
         * Returns number of "FixedInput" element
         */
        int sizeOfFixedInputArray();
        
        /**
         * Sets array of all "FixedInput" element
         */
        void setFixedInputArray(uk.ac.aston.uncertws.emulatorization.FixedInputType[] fixedInputArray);
        
        /**
         * Sets ith "FixedInput" element
         */
        void setFixedInputArray(int i, uk.ac.aston.uncertws.emulatorization.FixedInputType fixedInput);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "FixedInput" element
         */
        uk.ac.aston.uncertws.emulatorization.FixedInputType insertNewFixedInput(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "FixedInput" element
         */
        uk.ac.aston.uncertws.emulatorization.FixedInputType addNewFixedInput();
        
        /**
         * Removes the ith "FixedInput" element
         */
        void removeFixedInput(int i);
        
        /**
         * Gets array of all "VariableInput" elements
         */
        uk.ac.aston.uncertws.emulatorization.VariableInputType[] getVariableInputArray();
        
        /**
         * Gets ith "VariableInput" element
         */
        uk.ac.aston.uncertws.emulatorization.VariableInputType getVariableInputArray(int i);
        
        /**
         * Returns number of "VariableInput" element
         */
        int sizeOfVariableInputArray();
        
        /**
         * Sets array of all "VariableInput" element
         */
        void setVariableInputArray(uk.ac.aston.uncertws.emulatorization.VariableInputType[] variableInputArray);
        
        /**
         * Sets ith "VariableInput" element
         */
        void setVariableInputArray(int i, uk.ac.aston.uncertws.emulatorization.VariableInputType variableInput);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "VariableInput" element
         */
        uk.ac.aston.uncertws.emulatorization.VariableInputType insertNewVariableInput(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "VariableInput" element
         */
        uk.ac.aston.uncertws.emulatorization.VariableInputType addNewVariableInput();
        
        /**
         * Removes the ith "VariableInput" element
         */
        void removeVariableInput(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.Inputs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML outputs(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public interface Outputs extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Outputs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("outputs7facelemtype");
        
        /**
         * Gets array of all "Output" elements
         */
        uk.ac.aston.uncertws.emulatorization.OutputType[] getOutputArray();
        
        /**
         * Gets ith "Output" element
         */
        uk.ac.aston.uncertws.emulatorization.OutputType getOutputArray(int i);
        
        /**
         * Returns number of "Output" element
         */
        int sizeOfOutputArray();
        
        /**
         * Sets array of all "Output" element
         */
        void setOutputArray(uk.ac.aston.uncertws.emulatorization.OutputType[] outputArray);
        
        /**
         * Sets ith "Output" element
         */
        void setOutputArray(int i, uk.ac.aston.uncertws.emulatorization.OutputType output);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "Output" element
         */
        uk.ac.aston.uncertws.emulatorization.OutputType insertNewOutput(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "Output" element
         */
        uk.ac.aston.uncertws.emulatorization.OutputType addNewOutput();
        
        /**
         * Removes the ith "Output" element
         */
        void removeOutput(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.Outputs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML design(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public interface Design extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Design.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("designc1d0elemtype");
        
        /**
         * Gets the "size" element
         */
        java.math.BigInteger getSize();
        
        /**
         * Gets (as xml) the "size" element
         */
        org.apache.xmlbeans.XmlInteger xgetSize();
        
        /**
         * Sets the "size" element
         */
        void setSize(java.math.BigInteger size);
        
        /**
         * Sets (as xml) the "size" element
         */
        void xsetSize(org.apache.xmlbeans.XmlInteger size);
        
        /**
         * Gets array of all "DesignInput" elements
         */
        uk.ac.aston.uncertws.emulatorization.DesignInputType[] getDesignInputArray();
        
        /**
         * Gets ith "DesignInput" element
         */
        uk.ac.aston.uncertws.emulatorization.DesignInputType getDesignInputArray(int i);
        
        /**
         * Returns number of "DesignInput" element
         */
        int sizeOfDesignInputArray();
        
        /**
         * Sets array of all "DesignInput" element
         */
        void setDesignInputArray(uk.ac.aston.uncertws.emulatorization.DesignInputType[] designInputArray);
        
        /**
         * Sets ith "DesignInput" element
         */
        void setDesignInputArray(int i, uk.ac.aston.uncertws.emulatorization.DesignInputType designInput);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "DesignInput" element
         */
        uk.ac.aston.uncertws.emulatorization.DesignInputType insertNewDesignInput(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "DesignInput" element
         */
        uk.ac.aston.uncertws.emulatorization.DesignInputType addNewDesignInput();
        
        /**
         * Removes the ith "DesignInput" element
         */
        void removeDesignInput(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.Design newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.Design) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.Design newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.Design) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML evaluationResult(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a complex type.
     */
    public interface EvaluationResult extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(EvaluationResult.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("evaluationresult5235elemtype");
        
        /**
         * Gets array of all "EvaluationOutput" elements
         */
        uk.ac.aston.uncertws.emulatorization.EvaluationOutputType[] getEvaluationOutputArray();
        
        /**
         * Gets ith "EvaluationOutput" element
         */
        uk.ac.aston.uncertws.emulatorization.EvaluationOutputType getEvaluationOutputArray(int i);
        
        /**
         * Returns number of "EvaluationOutput" element
         */
        int sizeOfEvaluationOutputArray();
        
        /**
         * Sets array of all "EvaluationOutput" element
         */
        void setEvaluationOutputArray(uk.ac.aston.uncertws.emulatorization.EvaluationOutputType[] evaluationOutputArray);
        
        /**
         * Sets ith "EvaluationOutput" element
         */
        void setEvaluationOutputArray(int i, uk.ac.aston.uncertws.emulatorization.EvaluationOutputType evaluationOutput);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "EvaluationOutput" element
         */
        uk.ac.aston.uncertws.emulatorization.EvaluationOutputType insertNewEvaluationOutput(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "EvaluationOutput" element
         */
        uk.ac.aston.uncertws.emulatorization.EvaluationOutputType addNewEvaluationOutput();
        
        /**
         * Removes the ith "EvaluationOutput" element
         */
        void removeEvaluationOutput(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.EvaluationResult) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML meanFunction(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is an atomic type that is a restriction of uk.ac.aston.uncertws.emulatorization.EmulatorType$MeanFunction.
     */
    public interface MeanFunction extends org.apache.xmlbeans.XmlString
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(MeanFunction.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("meanfunctiond371elemtype");
        
        org.apache.xmlbeans.StringEnumAbstractBase enumValue();
        void set(org.apache.xmlbeans.StringEnumAbstractBase e);
        
        static final Enum ZERO = Enum.forString("zero");
        static final Enum CONSTANT = Enum.forString("constant");
        static final Enum LINEAR = Enum.forString("linear");
        static final Enum QUADRATIC = Enum.forString("quadratic");
        
        static final int INT_ZERO = Enum.INT_ZERO;
        static final int INT_CONSTANT = Enum.INT_CONSTANT;
        static final int INT_LINEAR = Enum.INT_LINEAR;
        static final int INT_QUADRATIC = Enum.INT_QUADRATIC;
        
        /**
         * Enumeration value class for uk.ac.aston.uncertws.emulatorization.EmulatorType$MeanFunction.
         * These enum values can be used as follows:
         * <pre>
         * enum.toString(); // returns the string value of the enum
         * enum.intValue(); // returns an int value, useful for switches
         * // e.g., case Enum.INT_ZERO
         * Enum.forString(s); // returns the enum value for a string
         * Enum.forInt(i); // returns the enum value for an int
         * </pre>
         * Enumeration objects are immutable singleton objects that
         * can be compared using == object equality. They have no
         * public constructor. See the constants defined within this
         * class for all the valid values.
         */
        static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
        {
            /**
             * Returns the enum value for a string, or null if none.
             */
            public static Enum forString(java.lang.String s)
                { return (Enum)table.forString(s); }
            /**
             * Returns the enum value corresponding to an int, or null if none.
             */
            public static Enum forInt(int i)
                { return (Enum)table.forInt(i); }
            
            private Enum(java.lang.String s, int i)
                { super(s, i); }
            
            static final int INT_ZERO = 1;
            static final int INT_CONSTANT = 2;
            static final int INT_LINEAR = 3;
            static final int INT_QUADRATIC = 4;
            
            public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
                new org.apache.xmlbeans.StringEnumAbstractBase.Table
            (
                new Enum[]
                {
                    new Enum("zero", INT_ZERO),
                    new Enum("constant", INT_CONSTANT),
                    new Enum("linear", INT_LINEAR),
                    new Enum("quadratic", INT_QUADRATIC),
                }
            );
            private static final long serialVersionUID = 1L;
            private java.lang.Object readResolve() { return forInt(intValue()); } 
        }
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction newValue(java.lang.Object obj) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction) type.newValue( obj ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.MeanFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML covarianceFunction(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is an atomic type that is a restriction of uk.ac.aston.uncertws.emulatorization.EmulatorType$CovarianceFunction.
     */
    public interface CovarianceFunction extends org.apache.xmlbeans.XmlString
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CovarianceFunction.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("covariancefunctione359elemtype");
        
        org.apache.xmlbeans.StringEnumAbstractBase enumValue();
        void set(org.apache.xmlbeans.StringEnumAbstractBase e);
        
        static final Enum SQUARED_EXPONENTIAL = Enum.forString("squared_exponential");
        static final Enum MATERN = Enum.forString("matern");
        
        static final int INT_SQUARED_EXPONENTIAL = Enum.INT_SQUARED_EXPONENTIAL;
        static final int INT_MATERN = Enum.INT_MATERN;
        
        /**
         * Enumeration value class for uk.ac.aston.uncertws.emulatorization.EmulatorType$CovarianceFunction.
         * These enum values can be used as follows:
         * <pre>
         * enum.toString(); // returns the string value of the enum
         * enum.intValue(); // returns an int value, useful for switches
         * // e.g., case Enum.INT_SQUARED_EXPONENTIAL
         * Enum.forString(s); // returns the enum value for a string
         * Enum.forInt(i); // returns the enum value for an int
         * </pre>
         * Enumeration objects are immutable singleton objects that
         * can be compared using == object equality. They have no
         * public constructor. See the constants defined within this
         * class for all the valid values.
         */
        static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
        {
            /**
             * Returns the enum value for a string, or null if none.
             */
            public static Enum forString(java.lang.String s)
                { return (Enum)table.forString(s); }
            /**
             * Returns the enum value corresponding to an int, or null if none.
             */
            public static Enum forInt(int i)
                { return (Enum)table.forInt(i); }
            
            private Enum(java.lang.String s, int i)
                { super(s, i); }
            
            static final int INT_SQUARED_EXPONENTIAL = 1;
            static final int INT_MATERN = 2;
            
            public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
                new org.apache.xmlbeans.StringEnumAbstractBase.Table
            (
                new Enum[]
                {
                    new Enum("squared_exponential", INT_SQUARED_EXPONENTIAL),
                    new Enum("matern", INT_MATERN),
                }
            );
            private static final long serialVersionUID = 1L;
            private java.lang.Object readResolve() { return forInt(intValue()); } 
        }
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction newValue(java.lang.Object obj) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction) type.newValue( obj ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.CovarianceFunction) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * An XML lengthScales(@http://uncertws.aston.ac.uk/emulatorization).
     *
     * This is a list type whose items are org.apache.xmlbeans.XmlDouble.
     */
    public interface LengthScales extends org.apache.xmlbeans.XmlAnySimpleType
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
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(LengthScales.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s604DBDE34056F6E8C5D619553FC84F65").resolveHandle("lengthscales5fffelemtype");
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales newValue(java.lang.Object obj) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales) type.newValue( obj ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales newInstance() {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (uk.ac.aston.uncertws.emulatorization.EmulatorType.LengthScales) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType newInstance() {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static uk.ac.aston.uncertws.emulatorization.EmulatorType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (uk.ac.aston.uncertws.emulatorization.EmulatorType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
