package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.transform.Source;

@Entity
public class SourceConfig {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer id_application;
    private String source_type;
    private String db_host;
    private Integer db_port;
    private String db_name;
    private String db_username;
    private String db_password;
    private String file_name;
    private String last_update;

    public SourceConfig(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_application() {
        return id_application;
    }

    public void setId_application(Integer id_application) {
        this.id_application = id_application;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public String getDb_host() {
        return db_host;
    }

    public void setDb_host(String db_host) {
        this.db_host = db_host;
    }

    public Integer getDb_port() {
        return db_port;
    }

    public void setDb_port(Integer db_port) {
        this.db_port = db_port;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getDb_username() {
        return db_username;
    }

    public void setDb_username(String db_username) {
        this.db_username = db_username;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "SourceConfig{" +
                "id=" + id +
                ", id_application=" + id_application +
                ", source_type='" + source_type + '\'' +
                ", db_host='" + db_host + '\'' +
                ", db_port=" + db_port +
                ", db_name='" + db_name + '\'' +
                ", db_username='" + db_username + '\'' +
                ", db_password='" + db_password + '\'' +
                ", file_name='" + file_name + '\'' +
                ", last_update='" + last_update + '\'' +
                '}';
    }
}
