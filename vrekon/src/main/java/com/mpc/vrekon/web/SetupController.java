package com.mpc.vrekon.web;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	
	@RequestMapping(value="/setup-service", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> getSetupServiceRespon(){
		log.debug("Routing to /setup-service "+getClass());		
		
		return setupService.getSetup();
	}
	
	@RequestMapping(value="/institusi-tambah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> addInstitusi(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /institusi-tambah param: "+institusiList);
		
		return setupService.addInstitusi(institusiList,"add");
	}
	
	@RequestMapping(value="/institusi-ubah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> editInstitusi(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /institusi-ubah param: "+institusiList);
		
		return setupService.addInstitusi(institusiList,"edit");
	}
	
	@RequestMapping(value="/institusi-hapus", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> deleteInstitusi(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /institusi-hapus param: "+institusiList);
		
		return setupService.deleteInstitusi(institusiList.getId());
	}
	
	//db-setting-service
	@RequestMapping(value="/db-service", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> getDBSetting(@RequestBody DBSetting dbSetting){
		log.debug("Routing to /db-service param: "+dbSetting);
		
		return setupService.getDBSetting(dbSetting);	
	}
	
	@RequestMapping(value="/db-service-tambah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> addDBSetting(@RequestBody DBRequest dbRequest){
		log.debug("Routing to /db-service-tambah param: "+dbRequest);
		
		return setupService.addDBSetting(dbRequest,"add");	
	}
	
	@RequestMapping(value="/db-service-ubah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> editDBSetting(@RequestBody DBRequest dbRequest){
		log.debug("Routing to /db-service-ubah param: "+dbRequest);
		
		return setupService.addDBSetting(dbRequest,"edit");
	}
	
	@RequestMapping(value="/db-service-hapus", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> deleteDBsetting(@RequestBody DBSetting dbSetting){
		log.debug("Routing to /db-service-hapus param: "+dbSetting);
		
		return setupService.deleteDBSetting(dbSetting);
	}
	
	@RequestMapping(value="/db-copy-start", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> copyDB(@RequestBody DBCopyStartRequest dbCopyStartRequest){
		log.debug("Routing to /db-copy-startparam: "+dbCopyStartRequest);
		
		return setupService.dbCopyStart(dbCopyStartRequest);
	}
	
	@RequestMapping(value="/helper", method=RequestMethod.POST)
	@ResponseBody
	public void helper(){
		String str = "acquirer_testing_variable";
		str = GlobalHelper.StrConvertToVar(str);
		log.debug(str);
	}
}