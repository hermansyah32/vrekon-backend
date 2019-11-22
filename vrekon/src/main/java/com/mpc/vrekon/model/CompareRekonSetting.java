package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompareRekonSetting {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = 1;
	private Integer idInstitusiFrom;
	private Integer idServiceFrom;
	private Integer idInstitusiTo;
	private Integer idServiceTo;
	private String listKey;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdInstitusiFrom() {
		return idInstitusiFrom;
	}
	public void setIdInstitusiFrom(Integer idInstitusiFrom) {
		this.idInstitusiFrom = idInstitusiFrom;
	}
	public Integer getIdServiceFrom() {
		return idServiceFrom;
	}
	public void setIdServiceFrom(Integer idServiceFrom) {
		this.idServiceFrom = idServiceFrom;
	}
	public Integer getIdInstitusiTo() {
		return idInstitusiTo;
	}
	public void setIdInstitusiTo(Integer idInstitusiTo) {
		this.idInstitusiTo = idInstitusiTo;
	}
	public Integer getIdServiceTo() {
		return idServiceTo;
	}
	public void setIdServiceTo(Integer idServiceTo) {
		this.idServiceTo = idServiceTo;
	}
	public String getListKey() {
		return listKey;
	}
	public void setListKey(String listKey) {
		this.listKey = listKey;
	}
	
	@Override
	public String toString() {
		return "CompareRekonSetting [id=" + id + ", idInstitusiFrom="
				+ idInstitusiFrom + ", idServiceFrom=" + idServiceFrom
				+ ", idInstitusiTo=" + idInstitusiTo + ", idServiceTo="
				+ idServiceTo + ", listKey=" + listKey + "]";
	}
}
