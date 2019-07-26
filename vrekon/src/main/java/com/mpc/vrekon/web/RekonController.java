package com.mpc.vrekon.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mpc.vrekon.model.RekonCompareKey;
import com.mpc.vrekon.model.RekonCompareResponse;
import com.mpc.vrekon.model.RequestResponse;
import com.mpc.vrekon.model.RekonCompareRequest;
import com.mpc.vrekon.service.RekonService;

@Controller
public class RekonController {
	@Autowired RekonService rekonService;
	
	Logger log = Logger.getLogger(getClass());
	
	@RequestMapping(value="/compare-key-list", method=RequestMethod.POST)
	@ResponseBody
	public List<RekonCompareKey> listCompareKey(){
		List<RekonCompareKey> rekonCompareKeyList = new ArrayList<RekonCompareKey>();
		rekonCompareKeyList = rekonService.listRekonCompareKey();
		log.debug("Routing to /compare-key-list data: "+rekonCompareKeyList.toString());
		
		return rekonCompareKeyList;
	}
	
	@RequestMapping(value="/compare-key-tambah", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse addCompareKey(@RequestBody RekonCompareKey rekonCompareKey){
		log.debug("Routing to /compare-key-tambah param: "+rekonCompareKey);
		RequestResponse requestResponse = new RequestResponse();
		requestResponse = rekonService.addRekonCompareKey(rekonCompareKey);
		
		return requestResponse;
	}
	
	@RequestMapping(value="/compare-key-hapus", method=RequestMethod.POST)
	@ResponseBody
	public RequestResponse deleteCompareKey(@RequestBody RekonCompareKey rekonCompareKey){
		log.debug("Routing to /compare-key-hapus param: "+rekonCompareKey);
		RequestResponse requestResponse = new RequestResponse();
		requestResponse = rekonService.deleteRekonCompareKey(rekonCompareKey);
		
		return requestResponse;
	}
	
	@RequestMapping(value="/compare-data-submit", method=RequestMethod.POST)
	@ResponseBody
	public List<RekonCompareResponse> compareData(@RequestBody RekonCompareRequest rekonCompareRequest){
		log.debug("Routing to /compare-data-submit param: "+rekonCompareRequest);
		
		return rekonService.compareData(rekonCompareRequest);
	}
}
