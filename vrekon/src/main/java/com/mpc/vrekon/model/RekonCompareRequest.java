package com.mpc.vrekon.model;

import java.util.List;

public class RekonCompareRequest extends CompareRekonSetting{
	private List<RekonCompareKey> rekonCompareKey;

	public List<RekonCompareKey> getRekonCompareKey() {
		return rekonCompareKey;
	}

	public void setRekonCompareKey(List<RekonCompareKey> rekonCompareKey) {
		this.rekonCompareKey = rekonCompareKey;
	}

	@Override
	public String toString() {
		return "RekonCompareRequest [rekonCompareKey=" + rekonCompareKey
				+ ", getIdInstitusiFrom()=" + getIdInstitusiFrom()
				+ ", getIdServiceFrom()=" + getIdServiceFrom()
				+ ", getIdInstitusiTo()=" + getIdInstitusiTo()
				+ ", getIdServiceTo()=" + getIdServiceTo() + ", getId()="
				+ getId() + ", getListKey()=" + getListKey() + "]";
	}
}
