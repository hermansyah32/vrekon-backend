package com.mpc.vrekon.model;

import javax.persistence.*;
import javax.xml.transform.Source;

@Entity
public class SourceConfig {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer idApplication;
    private String sourceType;
    private String dbHost;
    private Integer dbPort;
    private String dbName;
    private String dbUsername;
    private String dbPassword;
    @Column(columnDefinition = "TEXT")
    private String fileNames;
    private String tableName;
    private String lastUpdate;

    public SourceConfig(){

    }

    public SourceConfig(Integer idApplication, String sourceType, String dbHost, Integer dbPort, String dbName,
                        String dbUsername, String dbPassword, String fileNames, String tableName, String lastUpdate) {
        this.idApplication = idApplication;
        this.sourceType = sourceType;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.fileNames = fileNames;
        this.tableName = tableName;
        this.lastUpdate = lastUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(Integer idApplication) {
        this.idApplication = idApplication;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public Integer getDbPort() {
        return dbPort;
    }

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
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

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "SourceConfig{" +
                "id=" + id +
                ", idApplication=" + idApplication +
                ", sourceType='" + sourceType + '\'' +
                ", dbHost='" + dbHost + '\'' +
                ", dbPort=" + dbPort +
                ", dbName='" + dbName + '\'' +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", fileNames='" + fileNames + '\'' +
                ", tableName='" + tableName + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
