package com.mpc.vrekon.model;

public class RequestResponse {
	private String Status;
	private String Message;
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	@Override
	public String toString() {
		return "ErrorResponse [Status=" + Status + ", Message=" + Message + "]";
	}
	
}
