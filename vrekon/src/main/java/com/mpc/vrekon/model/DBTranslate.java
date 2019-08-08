package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBTranslate{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = 1;
	private Integer idService;
	private String originTableName;
	private String targetTableName;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdService() {
		return idService;
	}
	public void setIdService(Integer idService) {
		this.idService = idService;
	}
	public String getOriginTableName() {
		return originTableName;
	}
	public void setOriginTableName(String originTableName) {
		this.originTableName = originTableName;
	}
	public String getTargetTableName() {
		return targetTableName;
	}
	public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}
	
	@Override
	public String toString() {
		return "DBTranslate [id=" + id + ", idService=" + idService
				+ ", originTableName=" + originTableName + ", targetTableName="
				+ targetTableName + "]";
	}
}
