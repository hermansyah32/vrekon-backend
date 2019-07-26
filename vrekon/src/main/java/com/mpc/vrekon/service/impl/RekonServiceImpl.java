package com.mpc.vrekon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpc.vrekon.model.Institusi;
import com.mpc.vrekon.model.Institusi1;
import com.mpc.vrekon.model.RekonCompareRequest;
import com.mpc.vrekon.model.RekonCompareResponse;
import com.mpc.vrekon.repository.Institusi1Repository;
import com.mpc.vrekon.repository.Institusi2Repository;
import com.mpc.vrekon.repository.Institusi3Repository;
import com.mpc.vrekon.repository.Institusi4Repository;
import com.mpc.vrekon.repository.Institusi5Repository;
import com.mpc.vrekon.repository.Institusi6Repository;
import com.mpc.vrekon.service.RekonService;

@Service
public class RekonServiceImpl implements RekonService{
	@Autowired Institusi1Repository institusi1Repository;
	@Autowired Institusi2Repository institusi2Repository;
	@Autowired Institusi3Repository institusi3Repository;
	@Autowired Institusi4Repository institusi4Repository;
	@Autowired Institusi5Repository institusi5Repository;
	@Autowired Institusi6Repository institusi6Repository;
	Logger log = Logger.getLogger(getClass());
	
	public List<RekonCompareResponse> compareData(RekonCompareRequest rekonCompareRequest){
		List<RekonCompareResponse> rekonCompareResponses = new ArrayList<RekonCompareResponse>();
		List<Institusi> dataInstitusiFrom = new ArrayList<Institusi>();
		List<Institusi> dataInstitusiTo = new ArrayList<Institusi>();
		
		if(rekonCompareRequest.getIdInstitusiFrom() == 1){
			dataInstitusiFrom = (List<Institusi>)(Object) institusi1Repository.findAll();
		}else if(rekonCompareRequest.getIdInstitusiFrom() == 2){
			dataInstitusiFrom = (List<Institusi>)(Object) institusi2Repository.findAll();
		}else if(rekonCompareRequest.getIdInstitusiFrom() == 3){
			dataInstitusiFrom = (List<Institusi>)(Object) institusi3Repository.findAll();
		}else if(rekonCompareRequest.getIdInstitusiFrom() == 4){
			dataInstitusiFrom = (List<Institusi>)(Object) institusi4Repository.findAll();
		}else if(rekonCompareRequest.getIdInstitusiFrom() == 5){
			dataInstitusiFrom = (List<Institusi>)(Object) institusi5Repository.findAll();
		}else if(rekonCompareRequest.getIdInstitusiFrom() == 6){	
			dataInstitusiFrom = (List<Institusi>)(Object) institusi6Repository.findAll();
		}
	

		return rekonCompareResponses;
	}
}
