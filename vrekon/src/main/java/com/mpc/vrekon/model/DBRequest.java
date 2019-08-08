package com.mpc.vrekon.model;

import java.util.List;

public class DBRequest{
	private DBSetting dbSetting;
	private List<DBTranslate> dbTranslates;
	
	public DBSetting getDbSetting() {
		return dbSetting;
	}

	public void setDbSetting(DBSetting dbSetting) {
		this.dbSetting = dbSetting;
	}

	public List<DBTranslate> getDbTranslates() {
		return dbTranslates;
	}

	public void setDbTranslates(List<DBTranslate> dbTranslates) {
		this.dbTranslates = dbTranslates;
	}

	@Override
	public String toString() {
		return "DBRequest [dbSetting=" + dbSetting + ", dbTranslates="
				+ dbTranslates + "]";
	}
}
