package com.mpc.vrekon.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpc.vrekon.model.RekonCompareKey;
import com.mpc.vrekon.model.RekonCompareRequest;
import com.mpc.vrekon.model.RekonCompareResponse;
import com.mpc.vrekon.repository.Institusi1Repository;
import com.mpc.vrekon.repository.Institusi2Repository;
import com.mpc.vrekon.repository.Institusi3Repository;
import com.mpc.vrekon.repository.Institusi4Repository;
import com.mpc.vrekon.repository.Institusi5Repository;
import com.mpc.vrekon.repository.Institusi6Repository;
import com.mpc.vrekon.service.RekonService;
import com.mpc.vrekon.util.BindingReport;
import com.mpc.vrekon.util.ResponseHelper;

@Service
@Transactional
public class RekonServiceImpl implements RekonService{
	@Autowired Institusi1Repository institusi1Repository;
	@Autowired Institusi2Repository institusi2Repository;
	@Autowired Institusi3Repository institusi3Repository;
	@Autowired Institusi4Repository institusi4Repository;
	@Autowired Institusi5Repository institusi5Repository;
	@Autowired Institusi6Repository institusi6Repository;
	
	@PersistenceContext EntityManager entityManager;
	Logger log = Logger.getLogger(getClass());
	ResponseHelper responseHelper = new ResponseHelper();
	
	private String getCompareKey(RekonCompareRequest rekonCompareRequest){
		String compare = "";
		for (RekonCompareKey rekonCompareKey : rekonCompareRequest.getRekonCompareKey()) {
			compare += "t1."+rekonCompareKey.getKeyName()+" = t2."+rekonCompareKey.getKeyName()+" AND ";
		}
		compare += "1=1";
		compare = compare.replace("AND 1=1", "");
		return compare;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, List<?>> compareData(RekonCompareRequest rekonCompareRequest, HttpServletResponse response, 
			HttpServletRequest request, HttpSession session){
		List<RekonCompareResponse> rekonCompareResponses = new ArrayList<RekonCompareResponse>();
		Map<String, Object> responseObject = new HashMap<String, Object>();
		
		String compare = getCompareKey(rekonCompareRequest);
		String sql = "SELECT t1.id as id1, t1.acquirer as acquirer1, t1.id_service as id_service1, t1.local_date as local_date1, t1.local_time as local_time1, "
				 +"t2.id as id2, t2.acquirer as acquirer2, t2.id_service as id_service2, t2.local_date as local_date2, t2.local_time as local_time2 "
				 +"FROM institusi"+rekonCompareRequest.getIdInstitusiFrom()+" t1 "
				 +"LEFT JOIN institusi"+rekonCompareRequest.getIdInstitusiTo()+" t2 "
				 +"ON "+compare+" WHERE t1.id_service LIKE '%"+rekonCompareRequest.getIdServiceFrom()+"%' AND t2.id_service LIKE '"+rekonCompareRequest.getIdInstitusiTo()+"'"
				 +"UNION "
				 +"SELECT t1.id as id1, t1.acquirer as acquirer1, t1.id_service as id_service1, t1.local_date as local_date1, t1.local_time as local_time1, "
				 +"t2.id as id2, t2.acquirer as acquirer2, t2.id_service as id_service2, t2.local_date as local_date2, t2.local_time as local_time2 "
				 +"FROM institusi"+rekonCompareRequest.getIdInstitusiFrom()+" t1 "
				 +"RIGHT JOIN institusi"+rekonCompareRequest.getIdInstitusiTo()+" t2 "
				 +"ON "+compare+" WHERE t1.id_service LIKE '%"+rekonCompareRequest.getIdServiceFrom()+"%' AND t2.id_service LIKE '"+rekonCompareRequest.getIdInstitusiTo()+"'";
		
		log.debug(sql);

		try {
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> dataResponse = query.getResultList();
			Map<String, Object> paramReport = new HashMap<String, Object>();
			Date nowDate = new Date();
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(nowDate)+".xlsx";
			
			for(Object[] value : dataResponse){
				RekonCompareResponse rekonCompareResponse = new RekonCompareResponse();
				rekonCompareResponse.setId1((Integer) value[0]);
				rekonCompareResponse.setAcquirer1((String) value[1]);
				rekonCompareResponse.setIdService1((Integer) value[2]);
				rekonCompareResponse.setLocalDate1((String) value[3]);
				rekonCompareResponse.setLocalTime1((String) value[4]);
				
				rekonCompareResponse.setId2((Integer) value[5]);
				rekonCompareResponse.setAcquirer2((String) value[6]);
				rekonCompareResponse.setIdService2((Integer) value[7]);
				rekonCompareResponse.setLocalDate2((String) value[8]);
				rekonCompareResponse.setLocalTime2((String) value[9]);
				
				log.debug("ID: "+rekonCompareResponse);
				if(rekonCompareResponse.getIdService1() == null || rekonCompareResponse.getIdService2() == null){
					rekonCompareResponse.setStatus("unmatch");
				}else{
					rekonCompareResponse.setStatus("match");
				}
				rekonCompareResponses.add(rekonCompareResponse);
			}
			responseObject.put("resultRekon", rekonCompareResponses);
			responseObject.put("downloadLink", request.getRequestURL().toString().replace(request.getRequestURI(), "")+"/"+fileName.replace(".", "/"));
			
			BindingReport.createExcelFile(rekonCompareResponses, paramReport, "rekon-report", fileName, response, session, false);
			responseHelper.putData(responseObject, "success","");
		} catch (Exception e) {
			log.debug("Error: "+e);
			responseHelper.putData(new ArrayList<Object>(), "Failed",e.getMessage());
		}
	
		return responseHelper.getResponseData();
	}
}
