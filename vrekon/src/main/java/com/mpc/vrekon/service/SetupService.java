package com.mpc.vrekon.service;

import java.util.List;

import com.mpc.vrekon.model.DBServiceSetting;
import com.mpc.vrekon.model.RequestResponse;
import com.mpc.vrekon.model.InstitusiList;
import com.mpc.vrekon.model.SetupServiceResponse;

public interface SetupService {
	List<SetupServiceResponse> getSetupServiceResponse();
	
	RequestResponse addInstitusi(InstitusiList institusiList);
	
	RequestResponse deleteInstitusi(InstitusiList institusiList);
	
	RequestResponse addDBServiceSetting(DBServiceSetting dbServiceSetting);
	
	RequestResponse deleteDBServiceSetting(DBServiceSetting dbServiceSetting);
 }
