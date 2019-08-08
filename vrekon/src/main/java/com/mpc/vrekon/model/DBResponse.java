package com.mpc.vrekon.model;

import java.util.List;

public class DBResponse extends DBSetting{
	private List<DBTranslate> dbTranslates;

	public List<DBTranslate> getDbTranslates() {
		return dbTranslates;
	}

	public void setDbTranslates(List<DBTranslate> dbTranslates) {
		this.dbTranslates = dbTranslates;
	}

	@Override
	public String toString() {
		return "DBResponse [dbTranslates=" + dbTranslates + ", DBSetting="
				+ super.toString() + "]";
	}
}
