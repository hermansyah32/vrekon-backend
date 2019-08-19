package com.mpc.vrekon.web;

import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpc.vrekon.model.DBCopyStartRequest;
import com.mpc.vrekon.model.DBRequest;
import com.mpc.vrekon.model.DBSetting;
import com.mpc.vrekon.model.InstitusiList;
import com.mpc.vrekon.service.SetupService;
import com.mpc.vrekon.util.GlobalHelper;

@Controller
public class SetupController {
	@Autowired SetupService setupService;
	
	Logger log = Logger.getLogger(getClass());
	
	@RequestMapping(value="setup-service", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> getSetupServiceRespon(HttpServletRequest request){
		log.debug(request.getRequestURL().toString());		
		
		return setupService.getSetup(request);
	}
	
	@RequestMapping(value="institusi-tambah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> addInstitusi(@RequestBody InstitusiList institusiList, HttpServletRequest request){
		log.debug("Routing to /institusi-tambah param: "+institusiList);
		
		return setupService.addInstitusi(request, institusiList);
	}
	
	@RequestMapping(value="institusi-cari", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> searchInstitusi(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /institusi-cari param: "+institusiList);
		
		return setupService.searchInstitusi(institusiList.getId());
	}
	
	@RequestMapping(value="institusi-ubah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> editInstitusi(@RequestBody InstitusiList institusiList, HttpServletRequest request){
		log.debug("Routing to /institusi-ubah param: "+institusiList);
		
		return setupService.editInstitusi(request, institusiList);
	}
	
	@RequestMapping(value="institusi-hapus", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> deleteInstitusi(@RequestBody InstitusiList institusiList, HttpServletRequest request){
		log.debug("Routing to /institusi-hapus param: "+institusiList);
		
		return setupService.deleteInstitusi(request, institusiList.getId());
	}
	
	//db-setting-service
	@RequestMapping(value="db-service-by-id", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> getDBSettingById(@RequestBody DBSetting dbSetting){
		log.debug("Routing to /db-service param: "+dbSetting);
		
		return setupService.getDBSettingById(dbSetting);	
	}
	
	@RequestMapping(value="db-service-by-id-institusi", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> getDBSetting(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /db-service param: "+institusiList);
		
		return setupService.getDBSettingByIdInstitusi(institusiList);	
	}
	
	@RequestMapping(value="db-service-tambah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> addDBSetting(@RequestParam(value="files", required=false) MultipartFile[] files, @RequestParam("json") String json, HttpServletRequest request, HttpSession httpSession){
		log.debug("Routing to /db-service-tambah param: "+json);
		log.debug(files);
		return setupService.addDBSetting(request, json, files, httpSession);	
	}
	
	@RequestMapping(value="db-service-ubah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> editDBSetting(@RequestParam(value="files", required=false) MultipartFile[] files, @RequestParam("json") String json, HttpServletRequest request, HttpSession httpSession){
		log.debug("Routing to /db-service-ubah param: "+json);
		
		return setupService.editDBSetting(request, json, files, httpSession);
	}
	
	@RequestMapping(value="db-service-hapus", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> deleteDBsetting(@RequestBody DBSetting dbSetting, HttpServletRequest request){
		log.debug("Routing to /db-service-hapus param: "+dbSetting);
		
		return setupService.deleteDBSetting(request, dbSetting);
	}
	
	@RequestMapping(value="db-copy-start", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> copyDB(@RequestBody DBCopyStartRequest dbCopyStartRequest, HttpSession session, HttpServletRequest request){
		log.debug("Routing to /db-copy-startparam: "+dbCopyStartRequest);
		
		return setupService.dbCopyStart(request, dbCopyStartRequest, session);
	}
	
	@RequestMapping(value="db-clear-tmp-service", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> clearTmpService(@RequestBody DBSetting dbSetting, HttpServletRequest request) {
		log.debug("Routing to /db-clear-tmp-service: "+dbSetting);
		
		return setupService.clearTmpService(request, dbSetting);
	}
}