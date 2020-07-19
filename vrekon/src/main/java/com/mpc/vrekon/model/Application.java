package com.mpc.vrekon.model;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer temporaryTabel;
    private String applicationName;

    @Transient
    private Map<String, Object> mapField = null;

    public Application() {
    }

    public Application(Integer temporaryTabel, String applicationName) {
        this.temporaryTabel = temporaryTabel;
        this.applicationName = applicationName;
    }

    public Application(Integer id, Integer temporaryTabel, String applicationName) {
        // this constuctor used for edit
        this.id = id;
        this.temporaryTabel = temporaryTabel;
        this.applicationName = applicationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemporaryTabel() {
        return temporaryTabel;
    }

    public void setTemporaryTabel(Integer temporaryTabel) {
        this.temporaryTabel = temporaryTabel;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void addToMap(String key, Object value){
        if (mapField==null){
            toHashMap();
        }
        mapField.put(key, value);
        Logger log = Logger.getLogger(getClass());
        log.debug("addToMap: " + new Gson().toJson(mapField));
    }

    public Map<String, Object> toHashMap(){
        if (mapField==null){
            mapField = new HashMap<>();
            mapField.put("id", id);
            mapField.put("temporaryTabel", temporaryTabel);
            mapField.put("applicationName", applicationName);
        }
        return mapField;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", temporaryTabel=" + temporaryTabel +
                ", applicationName='" + applicationName + '\'' +
                '}';
    }
}
