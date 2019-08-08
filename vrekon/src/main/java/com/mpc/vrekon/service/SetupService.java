package com.mpc.vrekon.service;

import java.util.List;
import java.util.Map;

import com.mpc.vrekon.model.DBCopyStartRequest;
import com.mpc.vrekon.model.DBRequest;
import com.mpc.vrekon.model.DBSetting;
import com.mpc.vrekon.model.InstitusiList;

public interface SetupService {
	Map<String, List<?>> getSetup();
	
	Map<String, List<?>> addInstitusi(InstitusiList institusiList, String submitType);
	
	Map<String, List<?>> deleteInstitusi(Integer idInstitusi);
	
	Map<String, List<?>> getDBSetting(DBSetting dbSetting);
	
	Map<String, List<?>> addDBSetting(DBRequest dbRequest, String submitType);
	
	Map<String, List<?>> deleteDBSetting(DBSetting dbSetting);
	
	Map<String, List<?>> dbCopyStart(DBCopyStartRequest dbCopyStartRequest);
 }
