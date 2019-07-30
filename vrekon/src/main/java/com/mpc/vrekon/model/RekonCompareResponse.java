package com.mpc.vrekon.model;

public class RekonCompareResponse {
	private Integer id1;
	private Integer idInstitusi1;
	private String name1;
	private String acquirer1;
	private String localDate1;
	private String localTime1;
	private Integer id2;
	private Integer idInstitusi2;
	private String name2;
	private String acquirer2;
	private String localDate2;
	private String localTime2;
	private String status;
	
	public Integer getId1() {
		return id1;
	}
	public void setId1(Integer id1) {
		this.id1 = id1;
	}
	public Integer getIdInstitusi1() {
		return idInstitusi1;
	}
	public void setIdInstitusi1(Integer idInstitusi1) {
		this.idInstitusi1 = idInstitusi1;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getAcquirer1() {
		return acquirer1;
	}
	public void setAcquirer1(String acquirer1) {
		this.acquirer1 = acquirer1;
	}
	public String getLocalDate1() {
		return localDate1;
	}
	public void setLocalDate1(String localDate1) {
		this.localDate1 = localDate1;
	}
	public String getLocalTime1() {
		return localTime1;
	}
	public void setLocalTime1(String localTime1) {
		this.localTime1 = localTime1;
	}
	public Integer getId2() {
		return id2;
	}
	public void setId2(Integer id2) {
		this.id2 = id2;
	}
	public Integer getIdInstitusi2() {
		return idInstitusi2;
	}
	public void setIdInstitusi2(Integer idInstitusi2) {
		this.idInstitusi2 = idInstitusi2;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getAcquirer2() {
		return acquirer2;
	}
	public void setAcquirer2(String acquirer2) {
		this.acquirer2 = acquirer2;
	}
	public String getLocalDate2() {
		return localDate2;
	}
	public void setLocalDate2(String localDate2) {
		this.localDate2 = localDate2;
	}
	public String getLocalTime2() {
		return localTime2;
	}
	public void setLocalTime2(String localTime2) {
		this.localTime2 = localTime2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "RekonCompareResponse [id1=" + id1 + ", idInstitusi1="
				+ idInstitusi1 + ", name1=" + name1 + ", acquirer1="
				+ acquirer1 + ", localDate1=" + localDate1 + ", localTime1="
				+ localTime1 + ", id2=" + id2 + ", idInstitusi2="
				+ idInstitusi2 + ", name2=" + name2 + ", acquirer2="
				+ acquirer2 + ", localDate2=" + localDate2 + ", localTime2="
				+ localTime2 + ", status=" + status + "]";
	}
}
