package com.mpc.vrekon.model;

import java.util.List;

public class RekonCompareRequest {
	private Integer idInstitusiFrom;
	private Integer idInstitusiTo;
	private List<RekonCompareKey> rekonCompareKey;
	
	public Integer getIdInstitusiFrom() {
		return idInstitusiFrom;
	}
	public void setIdInstitusiFrom(Integer idInstitusiFrom) {
		this.idInstitusiFrom = idInstitusiFrom;
	}
	public Integer getIdInstitusiTo() {
		return idInstitusiTo;
	}
	public void setIdInstitusiTo(Integer idInstitusiTo) {
		this.idInstitusiTo = idInstitusiTo;
	}
	public List<RekonCompareKey> getRekonCompareKey() {
		return rekonCompareKey;
	}
	public void setRekonCompareKey(List<RekonCompareKey> rekonCompareKey) {
		this.rekonCompareKey = rekonCompareKey;
	}
	
	@Override
	public String toString() {
		return "SetupRekonRespon [idInstitusiFrom=" + idInstitusiFrom
				+ ", idInstitusiTo=" + idInstitusiTo + ", rekonCompareKey="
				+ rekonCompareKey + "]";
	}
}
