package com.mpc.vrekon.model;

import java.util.List;

public class RekonCompareRequest {
	private Integer idInstitusiFrom;
	private Integer idServiceFrom;
	private Integer idInstitusiTo;
	private Integer idServiceTo;
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
	public Integer getIdServiceFrom() {
		return idServiceFrom;
	}
	public void setIdServiceFrom(Integer idServiceFrom) {
		this.idServiceFrom = idServiceFrom;
	}
	public Integer getIdServiceTo() {
		return idServiceTo;
	}
	public void setIdServiceTo(Integer idServiceTo) {
		this.idServiceTo = idServiceTo;
	}
	
	@Override
	public String toString() {
		return "RekonCompareRequest [idInstitusiFrom=" + idInstitusiFrom
				+ ", idServiceFrom=" + idServiceFrom + ", idInstitusiTo="
				+ idInstitusiTo + ", idServiceTo=" + idServiceTo
				+ ", rekonCompareKey=" + rekonCompareKey + "]";
	}
}
