package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SourceTranslate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer idSourceConfig;
    private String originalFieldName;
    private String temporaryFieldName;

    public SourceTranslate() {
    }

    public SourceTranslate(Integer idSourceConfig, String originalFieldName, String temporaryFieldName) {
        this.idSourceConfig = idSourceConfig;
        this.originalFieldName = originalFieldName;
        this.temporaryFieldName = temporaryFieldName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSourceConfig() {
        return idSourceConfig;
    }

    public void setIdSourceConfig(Integer idSourceConfig) {
        this.idSourceConfig = idSourceConfig;
    }

    public String getOriginalFieldName() {
        return originalFieldName;
    }

    public void setOriginalFieldName(String originalFieldName) {
        this.originalFieldName = originalFieldName;
    }

    public String getTemporaryFieldName() {
        return temporaryFieldName;
    }

    public void setTemporaryFieldName(String temporaryFieldName) {
        this.temporaryFieldName = temporaryFieldName;
    }

    @Override
    public String toString() {
        return "SourceTranslate{" +
                "id=" + id +
                ", idSourceConfig=" + idSourceConfig +
                ", originalFieldName='" + originalFieldName + '\'' +
                ", temporaryFieldName='" + temporaryFieldName + '\'' +
                '}';
    }
}
