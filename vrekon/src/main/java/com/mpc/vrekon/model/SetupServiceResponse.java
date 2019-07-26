package com.mpc.vrekon.model;

import java.util.List;

public class SetupServiceResponse extends InstitusiList{
	private List<DBServiceSetting> institusiService;

	public List<DBServiceSetting> getInstitusiService() {
		return institusiService;
	}

	public void setInstitusiService(List<DBServiceSetting> institusiService) {
		this.institusiService = institusiService;
	}
}
