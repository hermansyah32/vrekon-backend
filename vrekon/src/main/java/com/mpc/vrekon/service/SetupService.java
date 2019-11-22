package com.mpc.vrekon.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.mpc.vrekon.model.DBCopyStartRequest;
import com.mpc.vrekon.model.DBSetting;
import com.mpc.vrekon.model.InstitusiList;

public interface SetupService {
	Map<String, List<?>> getSetup(HttpServletRequest request);
	
	Map<String, List<?>> addInstitusi(HttpServletRequest request, InstitusiList institusiList);
	
	Map<String, List<?>> editInstitusi(HttpServletRequest request, InstitusiList institusiList);
	
	Map<String, List<?>> searchInstitusi(Integer idInstitusi);
	
	Map<String, List<?>> deleteInstitusi(HttpServletRequest request, Integer idInstitusi);
	
	Map<String, List<?>> getDBSettingById(DBSetting dbSetting);
	
	Map<String, List<?>> getDBSettingByIdInstitusi(InstitusiList institusiList);
	
	Map<String, List<?>> addDBSetting(HttpServletRequest request, String json, MultipartFile[] file, HttpSession httpSession);
	
	Map<String, List<?>> editDBSetting(HttpServletRequest request, String json, MultipartFile[] file, HttpSession httpSession);
	
	Map<String, List<?>> deleteDBSetting(HttpServletRequest request, DBSetting dbSetting);
	
	Map<String, List<?>> dbCopyStart(HttpServletRequest request, DBCopyStartRequest dbCopyStartRequest, HttpSession session, Boolean scheduler);
	
	Map<String, List<?>> clearTmpService(HttpServletRequest request, DBSetting dbSetting);
 }
