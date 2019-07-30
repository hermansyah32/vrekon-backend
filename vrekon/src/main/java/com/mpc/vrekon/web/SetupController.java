package com.mpc.vrekon.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mpc.vrekon.model.DBServiceSetting;
import com.mpc.vrekon.model.RequestResponse;
import com.mpc.vrekon.model.InstitusiList;
import com.mpc.vrekon.model.SetupServiceResponse;
import com.mpc.vrekon.service.SetupService;

@Controller
public class SetupController {
	@Autowired SetupService setupService;
	Logger log = Logger.getLogger(getClass());
	
	@RequestMapping(value="/setup-service", method=RequestMethod.POST)
	@ResponseBody
	public List<SetupServiceResponse> getSetupServiceRespon(){
		log.debug("Routing to /setup-service "+getClass());
		return setupService.getSetupServiceResponse();
	}
	
	//institusi setup
	@RequestMapping(value="/institusi-tambah-default", method=RequestMethod.POST)
	@ResponseBody
	public void addInstitusi(){
		log.debug("Routing to /institusi-tambah-default");
		RequestResponse requestResponse = new RequestResponse();
		InstitusiList institusiList = new InstitusiList();
		institusiList.setIdInstitusi(1);
		institusiList.setName("IST");
		requestResponse = setupService.addInstitusi(institusiList);
		
		institusiList.setIdInstitusi(2);
		institusiList.setName("CMS");
		requestResponse = setupService.addInstitusi(institusiList);
		
		institusiList.setIdInstitusi(3);
		institusiList.setName("CORE");
		requestResponse = setupService.addInstitusi(institusiList);
	}
	
	@RequestMapping(value="/institusi-tambah-submit", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse addInstitusi(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /institusi-tambah-submit param: "+institusiList);
		RequestResponse requestResponse = new RequestResponse();
		
		requestResponse = setupService.addInstitusi(institusiList);
		log.debug("Response from Routing /institusi-tambah-submit: "+requestResponse);
		
		return requestResponse;
	}
	
	@RequestMapping(value="/institusi-ubah-submit", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse editInstitusi(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /institusi-ubah-submit param: "+institusiList);
		RequestResponse requestResponse = new RequestResponse();
		
		requestResponse = setupService.addInstitusi(institusiList);
		log.debug("Response from Routing /institusi-ubah-submit: "+requestResponse);
		
		return requestResponse;
	}
	
	@RequestMapping(value="/institusi-hapus-submit", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse deleteInstitusi(@RequestBody InstitusiList institusiList){
		log.debug("Routing to /institusi-delete-submit param: "+institusiList);
		RequestResponse requestResponse = new RequestResponse();
		
		requestResponse = setupService.deleteInstitusi(institusiList.getIdInstitusi());
		log.debug("Response from Routing /institusi-delete-submit: "+requestResponse);
		
		return requestResponse;
	}
	
	//db-setting-service
	@RequestMapping(value="/db-service-tambah-submit", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse addDBService(@RequestBody DBServiceSetting dbServiceSetting){
		log.debug("Routing to /db-service-tambah-submit param: "+dbServiceSetting);
		RequestResponse requestResponse = new RequestResponse();
		
		requestResponse = setupService.addDBServiceSetting(dbServiceSetting);
		log.debug("Response from Routing /db-service-tambah-submit: "+requestResponse);
		
		return requestResponse;
	}
	
	@RequestMapping(value="/db-service-ubah-submit", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse editDBService(@RequestBody DBServiceSetting dbServiceSetting){
		log.debug("Routing to /db-service-ubah-submit param: "+dbServiceSetting);
		RequestResponse requestResponse = new RequestResponse();
		
		requestResponse = setupService.addDBServiceSetting(dbServiceSetting);
		log.debug("Response from Routing /db-service-ubah-submit: "+requestResponse);
		
		return requestResponse;
	}
	
	@RequestMapping(value="/db-service-hapus-submit", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse deleteDBService(@RequestBody DBServiceSetting dbServiceSetting){
		log.debug("Routing to /db-service-hapus-submit param: "+dbServiceSetting);
		RequestResponse requestResponse = new RequestResponse();
		
		requestResponse = setupService.deleteDBServiceSetting(dbServiceSetting.getIdService());
		log.debug("Response from Routing /db-service-hapus-submit: "+requestResponse);
		
		return requestResponse;
	}
}