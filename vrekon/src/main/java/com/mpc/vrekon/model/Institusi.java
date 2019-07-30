package com.mpc.vrekon.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Institusi {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer idInstitusi;
	private String name;
	private String acquirer;
	private String localDate;
	private String localTime;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdInstitusi() {
		return idInstitusi;
	}
	public void setIdInstitusi(Integer idInstitusi) {
		this.idInstitusi = idInstitusi;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAcquirer() {
		return acquirer;
	}
	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}
	public String getLocalDate() {
		return localDate;
	}
	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}
	public String getLocalTime() {
		return localTime;
	}
	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}
	
	@Override
	public String toString() {
		return "Institusi [id=" + id + ", idInstitusi=" + idInstitusi
				+ ", name=" + name + ", acquirer=" + acquirer + ", localDate="
				+ localDate + ", localTime=" + localTime + "]";
	}
}
