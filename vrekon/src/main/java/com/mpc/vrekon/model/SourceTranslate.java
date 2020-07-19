package com.mpc.vrekon.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class SourceTranslate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer idSourceConfig;
    private String originalFieldName;
    private String temporaryFieldName;

    @Transient
    private Map<String, Object> mapField = null;

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

    public void addToMap(String key, Object value){
        if (mapField==null){
            this.toHashMap();
        }
        this.mapField.put(key, value);
    }

    public Map<String, Object> toHashMap(){
        if (mapField==null){
            mapField = new HashMap<>();
            mapField.put("id", id);
            mapField.put("idSourceConfig", idSourceConfig);
            mapField.put("originalFieldName", originalFieldName);
            mapField.put("temporaryFieldName", temporaryFieldName);
        }
        return mapField;
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
