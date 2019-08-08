package com.mpc.vrekon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

@Service
@Transactional
public class RekonServiceImpl implements RekonService{
	@Autowired Institusi1Repository institusi1Repository;
	@Autowired Institusi2Repository institusi2Repository;
	@Autowired Institusi3Repository institusi3Repository;
	@Autowired Institusi4Repository institusi4Repository;
	@Autowired Institusi5Repository institusi5Repository;
	@Autowired Institusi6Repository institusi6Repository;
	
	@PersistenceContext(unitName="persistenceUnit") EntityManager entityManager;
	Logger log = Logger.getLogger(getClass());
	
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
	public List<RekonCompareResponse> compareData(RekonCompareRequest rekonCompareRequest){
		List<RekonCompareResponse> rekonCompareResponses = new ArrayList<RekonCompareResponse>();
		String compare = getCompareKey(rekonCompareRequest);
		String sql = "SELECT t1.id as id1, t1.acquirer as acquirer1, t1.id_institusi as id_institusi1, t1.local_date as local_date1, t1.local_time as local_time1, t1.name as name1, "
					 +"t2.id as id2, t2.acquirer as acquirer2, t2.id_institusi as id_institusi2, t2.local_date as local_date2, t2.local_time as local_time2, t2.name as name2"
					 +" FROM institusi"+rekonCompareRequest.getIdInstitusiFrom()+" t1 "
					 +"LEFT JOIN institusi"+rekonCompareRequest.getIdInstitusiTo()+" t2 "
					 +"ON "+compare
					 +"UNION "
					 +"SELECT t1.id as id1, t1.acquirer as acquirer1, t1.id_institusi as id_institusi1, t1.local_date as local_date1, t1.local_time as local_time1, t1.name as name1, "
					 +"t2.id as id2, t2.acquirer as acquirer2, t2.id_institusi as id_institusi2, t2.local_date as local_date2, t2.local_time as local_time2, t2.name as name2"
					 +" FROM institusi"+rekonCompareRequest.getIdInstitusiFrom()+" t1 "
					 +"RIGHT JOIN institusi"+rekonCompareRequest.getIdInstitusiTo()+" t2 "
					 +"ON "+compare;

		try {
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> dataResponse = query.getResultList();
			
			for(Object[] value : dataResponse){
				RekonCompareResponse rekonCompareResponse = new RekonCompareResponse();
				rekonCompareResponse.setId1((Integer) value[0]);
				rekonCompareResponse.setAcquirer1((String) value[1]);
				rekonCompareResponse.setIdInstitusi1((Integer) value[2]);
				rekonCompareResponse.setLocalDate1((String) value[3]);
				rekonCompareResponse.setLocalTime1((String) value[4]);
				rekonCompareResponse.setName1((String) value[5]);
				
				rekonCompareResponse.setId2((Integer) value[6]);
				rekonCompareResponse.setAcquirer2((String) value[7]);
				rekonCompareResponse.setIdInstitusi2((Integer) value[8]);
				rekonCompareResponse.setLocalDate2((String) value[9]);
				rekonCompareResponse.setLocalTime2((String) value[10]);
				rekonCompareResponse.setName2((String) value[11]);
				
				log.debug("ID: "+rekonCompareResponse);
				if(rekonCompareResponse.getIdInstitusi1() == null || rekonCompareResponse.getIdInstitusi2() == null){
					rekonCompareResponse.setStatus("unmatch");
				}else{
					rekonCompareResponse.setStatus("match");
				}
				rekonCompareResponses.add(rekonCompareResponse);
			}
			
		} catch (Exception e) {
			log.debug("Error: "+e);
		}
	
		return rekonCompareResponses;
	}
}
