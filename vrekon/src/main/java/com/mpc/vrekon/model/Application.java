package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer temporaryTabel;
    private String applicationName;

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

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", temporaryTabel=" + temporaryTabel +
                ", applicationName='" + applicationName + '\'' +
                '}';
    }
}
