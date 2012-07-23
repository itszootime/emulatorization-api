package org.uncertweb.et.parameter;


public class ParameterDescription {
	
	public enum DataType { Numeric };
	
	private String detail;
	private DataType dataType;	
	private String encodingType;
	private int minOccurs;
	private int maxOccurs;
	
	public ParameterDescription(DataType dataType) {
		this(dataType, 1, 1);
	}
	
	public ParameterDescription(DataType dataType, String encodingType) {
		this(dataType);
		this.encodingType = encodingType;
	}
	
	public ParameterDescription(DataType dataType, int minOccurs, int maxOccurs) {
		this.dataType = dataType;
		this.minOccurs = minOccurs;
		this.maxOccurs = maxOccurs;
	}
	
	public ParameterDescription(DataType dataType, String encodingType, int minOccurs, int maxOccurs) {
		this(dataType, minOccurs, maxOccurs);
		this.encodingType = encodingType;
	}
	
	public ParameterDescription(String detail, DataType dataType, int minOccurs, int maxOccurs) {
		this(dataType, minOccurs, maxOccurs);
		this.detail = detail;
	}

	public ParameterDescription(String detail, DataType dataType, String encodingType, int minOccurs, int maxOccurs) {
		this(dataType, encodingType, minOccurs, maxOccurs);
		this.detail = detail;
	}

	public ParameterDescription(String detail, DataType dataType) {
		this(dataType);
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
