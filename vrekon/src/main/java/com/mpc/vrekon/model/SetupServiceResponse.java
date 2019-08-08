package com.mpc.vrekon.model;

import java.util.List;

public class SetupServiceResponse extends InstitusiList{
	private List<DBSetting> institusiService;

	public List<DBSetting> getInstitusiService() {
		return institusiService;
	}

	public void setInstitusiService(List<DBSetting> institusiService) {
		this.institusiService = institusiService;
	}
}
