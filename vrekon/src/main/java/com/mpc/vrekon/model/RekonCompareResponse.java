package com.mpc.vrekon.model;

public class RekonCompareResponse {
	private Institusi1 institusiFrom;
	private Institusi2 institusiTo;
	private String status;
	
	public Institusi1 getInstitusiFrom() {
		return institusiFrom;
	}
	public void setInstitusiFrom(Institusi1 institusiFrom) {
		this.institusiFrom = institusiFrom;
	}
	public Institusi2 getInstitusiTo() {
		return institusiTo;
	}
	public void setInstitusiTo(Institusi2 institusiTo) {
		this.institusiTo = institusiTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
