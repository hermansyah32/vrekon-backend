package com.mpc.vrekon.model;

public class DBCopyStartRequest {
	private Integer idInstitusi;
	private Integer idService;
	
	public Integer getIdInstitusi() {
		return idInstitusi;
	}
	public void setIdInstitusi(Integer idInstitusi) {
		this.idInstitusi = idInstitusi;
	}
	public Integer getIdService() {
		return idService;
	}
	public void setIdService(Integer idService) {
		this.idService = idService;
	}
	@Override
	public String toString() {
		return "DBCopyStartRequest [idInstitusi=" + idInstitusi
				+ ", idService=" + idService + "]";
	}
}
