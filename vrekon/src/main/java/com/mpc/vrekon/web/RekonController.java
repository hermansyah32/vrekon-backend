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
	
	@RequestMapping(value="/compare-data-submit", method=RequestMethod.POST)
	@ResponseBody
	public List<RekonCompareResponse> compareData(@RequestBody RekonCompareRequest rekonCompareRequest){
		log.debug("Routing to /compare-data-submit param: "+rekonCompareRequest);
		List<RekonCompareResponse> rekonCompareResponses = new ArrayList<RekonCompareResponse>();
		
		rekonCompareResponses = rekonService.compareData(rekonCompareRequest);
		return rekonCompareResponses;
	}
}
