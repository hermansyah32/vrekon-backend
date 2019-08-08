package com.mpc.vrekon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mpc.vrekon.model.ResponseMessage;

public class ResponseHelper {
	private Map<String, List<?>> responseData = new HashMap<String, List<?>>();
	private Logger log = Logger.getLogger(getClass());
	
	public void putData(Object data, String status, String message){
		List<Object> listData = new ArrayList<Object>();
		listData.add(data);
		
		List<Object> listLog = new ArrayList<Object>();
		ResponseMessage responseMessage = new ResponseMessage();
		
		responseMessage.setMessage(message);
		responseMessage.setStatus(status);
		
		listLog.add(responseMessage);		
		this.responseData.put("response", listData);
		this.responseData.put("log", listLog);
		
		log.debug("Response Message: "+this.responseData);
	}
	
	public void putData(List<Object> data, String status, String message){
		List<Object> listLog = new ArrayList<Object>();
		ResponseMessage responseMessage = new ResponseMessage();
		
		responseMessage.setMessage(message);
		responseMessage.setStatus(status);
		
		listLog.add(responseMessage);		
		
		this.responseData.put("response", data);
		this.responseData.put("log", listLog);
		
		log.debug("Response Message: "+this.responseData);
	}

	public Map<String, List<?>> getResponseData() {
		return responseData;
	}
	
}
