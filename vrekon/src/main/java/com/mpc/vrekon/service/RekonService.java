package com.mpc.vrekon.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mpc.vrekon.model.CompareRekonSetting;
import com.mpc.vrekon.model.RekonCompareRequest;

public interface RekonService {
	
	Map<String, List<?>> compareData(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, 
			HttpServletRequest request, HttpSession session);
	
	Map<String, List<?>> startCompareData(CompareRekonSetting compareRekonSetting, HttpServletResponse response, 
			HttpServletRequest request, HttpSession session);
	
	Map<String, List<?>> getCompareSetting();
	
	Map<String, List<?>> addCompareSetting(RekonCompareRequest rekonCompareRequest);
}
