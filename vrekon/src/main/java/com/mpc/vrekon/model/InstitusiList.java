package com.mpc.vrekon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InstitusiList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = 1;
	private Integer institusiTabel;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getInstitusiTabel() {
		return institusiTabel;
	}
	public void setInstitusiTabel(Integer institusiTabel) {
		this.institusiTabel = institusiTabel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "InstitusiList [id=" + id + ", institusiTabel=" + institusiTabel
				+ ", name=" + name + "]";
	}
}
