package com.mpc.vrekon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpc.vrekon.model.DBServiceSetting;
import com.mpc.vrekon.model.RequestResponse;
import com.mpc.vrekon.model.InstitusiList;
import com.mpc.vrekon.model.SetupServiceResponse;
import com.mpc.vrekon.repository.InstitusiListRepository;
import com.mpc.vrekon.repository.DBServiceSettingRepository;
import com.mpc.vrekon.service.SetupService;

@Service
public class SetupServiceImpl implements SetupService{
	
	@Autowired InstitusiListRepository institusiListRepository;
	@Autowired DBServiceSettingRepository dbServiceSettingRepository;
	
	public List<SetupServiceResponse> getSetupServiceResponse(){
		List<InstitusiList> institusiList = new ArrayList<InstitusiList>();
		List<SetupServiceResponse> setupServiceList = new ArrayList<SetupServiceResponse>();
		
		institusiList = institusiListRepository.findAll();
		
		for (InstitusiList value : institusiList) {
			SetupServiceResponse setupServiceResponse = new SetupServiceResponse();
			
			setupServiceResponse.setName(value.getName());
			setupServiceResponse.setIdInstitusi(value.getIdInstitusi());
			setupServiceResponse.setInstitusiService(dbServiceSettingRepository.findByIdInstitusi(value.getIdInstitusi()));
			
			setupServiceList.add(setupServiceResponse);
		}
		
		return setupServiceList;
	}
	
	public RequestResponse addInstitusi(InstitusiList institusiList){
		InstitusiList tmpInstitusiList = new InstitusiList();
		RequestResponse requestResponse = new RequestResponse();
		tmpInstitusiList = institusiListRepository.findByIdInstitusi(institusiList.getIdInstitusi());
		
		if(tmpInstitusiList == null){			
			institusiListRepository.save(institusiList);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data Added "+institusiList.toString());
		}else{
			institusiListRepository.save(institusiList);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data is updated "+institusiList.toString());
		}
		
		return requestResponse;
	}
	
	public RequestResponse deleteInstitusi(Integer idInstitusi){
		RequestResponse requestResponse = new RequestResponse();
		
		try {
			institusiListRepository.delete(idInstitusi);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data idInstitusi "+idInstitusi.toString()+" is deleted");
		} catch (Exception e) {
			requestResponse.setStatus("Failed");
			requestResponse.setMessage("Error Delete Institusi, ErrMsg: "+e);
		}
		
		return requestResponse;
	}
	
	public RequestResponse addDBServiceSetting(DBServiceSetting dbServiceSetting){
		RequestResponse requestResponse = new RequestResponse();
		List<DBServiceSetting> dbServiceSettings = new ArrayList<DBServiceSetting>();
		dbServiceSettings = dbServiceSettingRepository.findByIdInstitusi(dbServiceSetting.getIdInstitusi());
		
		if(dbServiceSettings == null){
			dbServiceSettingRepository.save(dbServiceSetting);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data Added "+dbServiceSetting.toString());
		} else {
			dbServiceSettingRepository.save(dbServiceSetting);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data is Updated "+dbServiceSetting.toString());
		}
		
		return requestResponse;
	}
	
	public RequestResponse deleteDBServiceSetting(Integer idService){
		RequestResponse requestResponse = new RequestResponse();
	
		try {
			dbServiceSettingRepository.delete(idService);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data idService "+idService+" is deleted");
		} catch (Exception e) {
			requestResponse.setStatus("Failed");
			requestResponse.setMessage("Error Delete DBServiceSetting, ErrMsg: "+e);
		}
		
		return requestResponse;
	}
}
