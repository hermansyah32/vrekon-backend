package com.mpc.vrekon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShcLogKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4407870189328996210L;
	@Column(name ="PAN")
	private String pan;
	
	@Column(name="REFNUM")
	private String refnum;
	
	public ShcLogKey(){	}
	
	public ShcLogKey(String pan, String refnum) {
		super();
		this.pan = pan;
		this.refnum = refnum;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getRefnum() {
		return refnum;
	}
	public void setRefnum(String refnum) {
		this.refnum = refnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pan == null) ? 0 : pan.hashCode());
		result = prime * result + ((refnum == null) ? 0 : refnum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShcLogKey other = (ShcLogKey) obj;
		if (pan == null) {
			if (other.pan != null)
				return false;
		} else if (!pan.equals(other.pan))
			return false;
		if (refnum == null) {
			if (other.refnum != null)
				return false;
		} else if (!refnum.equals(other.refnum))
			return false;
		return true;
	}
}
