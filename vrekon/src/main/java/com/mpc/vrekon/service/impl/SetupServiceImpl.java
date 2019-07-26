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
			requestResponse.setStatus("Failed");
			requestResponse.setMessage("Duplicate ID Institusi");
		}
		
		return requestResponse;
	}
	
	public RequestResponse deleteInstitusi(InstitusiList institusiList){
		RequestResponse requestResponse = new RequestResponse();
		
		try {
			institusiListRepository.delete(institusiList);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data Added "+institusiList.toString());
		} catch (Exception e) {
			requestResponse.setStatus("Failed");
			requestResponse.setMessage("Error Delete Institusi, ErrMsg: "+e);
		}
		
		return requestResponse;
	}
	
	public RequestResponse addDBServiceSetting(DBServiceSetting dbServiceSetting){
		RequestResponse requestResponse = new RequestResponse();
		
		try {
			dbServiceSettingRepository.save(dbServiceSetting);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data Added "+dbServiceSetting.toString());
		} catch (Exception e) {
			requestResponse.setStatus("Failed");
			requestResponse.setMessage("Error Add DBServiceSetting, ErrMsg: "+e);
		}
		
		return requestResponse;
	}
	
	public RequestResponse deleteDBServiceSetting(DBServiceSetting dbServiceSetting){
		RequestResponse requestResponse = new RequestResponse();
	
		try {
			dbServiceSettingRepository.delete(dbServiceSetting);
			requestResponse.setStatus("Success");
			requestResponse.setMessage("Data Added "+dbServiceSetting.toString());
		} catch (Exception e) {
			requestResponse.setStatus("Failed");
			requestResponse.setMessage("Error Delete DBServiceSetting"+dbServiceSetting.toString());
		}
		
		return requestResponse;
	}
}
