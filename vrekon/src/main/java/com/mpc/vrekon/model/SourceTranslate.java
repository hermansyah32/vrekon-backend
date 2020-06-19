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
    private Integer id_source_config;
    private String original_field_name;
    private String temporary_field_name;

    public SourceTranslate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_source_config() {
        return id_source_config;
    }

    public void setId_source_config(Integer id_source_config) {
        this.id_source_config = id_source_config;
    }

    public String getOriginal_field_name() {
        return original_field_name;
    }

    public void setOriginal_field_name(String original_field_name) {
        this.original_field_name = original_field_name;
    }

    public String getTemporary_field_name() {
        return temporary_field_name;
    }

    public void setTemporary_field_name(String temporary_field_name) {
        this.temporary_field_name = temporary_field_name;
    }

    @Override
    public String toString() {
        return "SourceTranslate{" +
                "id=" + id +
                ", id_source_config=" + id_source_config +
                ", original_field_name='" + original_field_name + '\'' +
                ", temporary_field_name='" + temporary_field_name + '\'' +
                '}';
    }
}
