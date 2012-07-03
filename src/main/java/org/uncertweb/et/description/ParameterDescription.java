package org.uncertweb.et.description;


public class ParameterDescription {
	
	public enum DataType { Numeric };

	private String identifier;
	private String detail;
	private DataType dataType;	
	private String encodingType;
	private int minOccurs;
	private int maxOccurs;
	
	public ParameterDescription(String identifier, DataType dataType) {
		this(identifier, dataType, 1, 1);
	}
	
	public ParameterDescription(String identifier, DataType dataType, String encodingType) {
		this(identifier, dataType);
		this.encodingType = encodingType;
	}
	
	public ParameterDescription(String identifier, DataType dataType, int minOccurs, int maxOccurs) {
		this.identifier = identifier;
		this.dataType = dataType;
		this.minOccurs = minOccurs;
		this.maxOccurs = maxOccurs;
	}
	
	public ParameterDescription(String identifier, DataType dataType, String encodingType, int minOccurs, int maxOccurs) {
		this(identifier, dataType, minOccurs, maxOccurs);
		this.encodingType = encodingType;
	}
	
	public ParameterDescription(String identifier, String detail, DataType dataType, int minOccurs, int maxOccurs) {
		this(identifier, dataType, minOccurs, maxOccurs);
		this.detail = detail;
	}

	public ParameterDescription(String identifier, String detail, DataType dataType, String encodingType, int minOccurs, int maxOccurs) {
		this(identifier, dataType, encodingType, minOccurs, maxOccurs);
		this.detail = detail;
	}

	public ParameterDescription(String identifier, String detail, DataType dataType) {
		this(identifier, dataType);
		this.detail = detail;
	}

	public DataType getDataType() {
		return dataType;
	}
	
	public String getEncodingType() {
		return encodingType;
	}
	
	public String getDetail() {
		return detail;
	}

	public String getIdentifier() {
		return identifier;
	}
	
	public int getMaxOccurs() {
		return maxOccurs;
	}

	public int getMinOccurs() {
		return minOccurs;
	}
	
	public boolean isRequired() {
		return minOccurs > 0;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
