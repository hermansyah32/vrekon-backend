package com.mpc.vrekon.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mpc.vrekon.model.RekonCompareRequest;
import com.mpc.vrekon.service.RekonService;
import com.mpc.vrekon.util.BindingReport;

@Controller
public class RekonController {
	@Autowired RekonService rekonService;	
	Logger log = Logger.getLogger(getClass());
	
	@RequestMapping(value="/compare-data", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> compareData(@RequestBody RekonCompareRequest rekonCompareRequest, HttpServletResponse response, 
			HttpServletRequest request, HttpSession session){
		log.debug("Routing to /compare-data param: "+rekonCompareRequest);
		
		return rekonService.startCompareData(rekonCompareRequest, response, request, session);
	}
	
	@RequestMapping(value="/compare-setting", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> getCompareSetting(){
		return rekonService.getCompareSetting();
	}
	
	@RequestMapping(value="/compare-setting-tambah", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<?>> addCompareSetting(@RequestBody RekonCompareRequest rekonCompareRequest){
		return rekonService.addCompareSetting(rekonCompareRequest);
	}
	
	@RequestMapping(value="/download-rekon/{filename}/{ext}", method=RequestMethod.GET)
	@ResponseBody
	public void generateData(@PathVariable("filename") String fileName, @PathVariable("ext") String ext, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session){
		
		log.debug("Routing to /download-rekon param: "+fileName);
		
		BindingReport.downloadFileAndSave(fileName+"."+ext, session, response);
	}
}
