package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InstitusiList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idInstitusi;
	private String name;
	
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
	
	@Override
	public String toString() {
		return "InstitusiList [idInstitusi=" + idInstitusi + ", name=" + name
				+ "]";
	}
}
