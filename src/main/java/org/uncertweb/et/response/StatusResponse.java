package org.uncertweb.et.response;

public class StatusResponse extends Response {
	
	private String message;
	private boolean matlabOK;
	private String matlabMessage;
	private boolean rserveOK;
	private String rserveMessage;
	
	public StatusResponse(String message, boolean matlabOK, String matlabMessage, boolean rserveOK, String rserveMessage) {
		this.message = message;
		this.matlabOK = matlabOK;
		this.matlabMessage = matlabMessage;
		this.rserveOK = rserveOK;
		this.rserveMessage = rserveMessage;
	}

	public String getMessage() {
		return message;
	}

	public String getMatlabMessage() {
		return matlabMessage;
	}

	public String getRserveMessage() {
		return rserveMessage;
	}

	public boolean isMatlabOK() {
		return matlabOK;
	}

	public boolean isRserveOK() {
		return rserveOK;
	}

}
