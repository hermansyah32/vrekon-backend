package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBSetting {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer idInstitusi;
	private String dbType;
	private String dbHost;
	private String dbName;
	private String dbUsername;
	private String dbPassword;
	private String dbTableName;
	private String status;	

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
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbHost() {
		return dbHost;
	}
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getDbTableName() {
		return dbTableName;
	}
	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "DBSetting [id=" + id + ", idInstitusi=" + idInstitusi
				+ ", dbType=" + dbType + ", dbHost=" + dbHost + ", dbName="
				+ dbName + ", dbUsername=" + dbUsername + ", dbPassword="
				+ dbPassword + ", dbTableName=" + dbTableName + ", status="
				+ status + "]";
	}
}
