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
    private Integer temporary_tabel;
    private String application_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemporary_tabel() {
        return temporary_tabel;
    }

    public void setTemporary_tabel(Integer temporary_tabel) {
        this.temporary_tabel = temporary_tabel;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", temporary_tabel=" + temporary_tabel +
                ", application_name='" + application_name + '\'' +
                '}';
    }
}
