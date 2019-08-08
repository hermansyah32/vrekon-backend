package com.mpc.vrekon.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpc.vrekon.model.DBCopyStartRequest;
import com.mpc.vrekon.model.DBRequest;
import com.mpc.vrekon.model.DBResponse;
import com.mpc.vrekon.model.DBSetting;
import com.mpc.vrekon.model.DBTranslate;
import com.mpc.vrekon.model.Institusi1;
import com.mpc.vrekon.model.Institusi2;
import com.mpc.vrekon.model.Institusi3;
import com.mpc.vrekon.model.Institusi4;
import com.mpc.vrekon.model.Institusi5;
import com.mpc.vrekon.model.Institusi6;
import com.mpc.vrekon.model.InstitusiList;
import com.mpc.vrekon.model.SetupServiceResponse;
import com.mpc.vrekon.repository.DBSettingRepository;
import com.mpc.vrekon.repository.DBTranslateRepository;
import com.mpc.vrekon.repository.Institusi1Repository;
import com.mpc.vrekon.repository.Institusi2Repository;
import com.mpc.vrekon.repository.Institusi3Repository;
import com.mpc.vrekon.repository.Institusi4Repository;
import com.mpc.vrekon.repository.Institusi5Repository;
import com.mpc.vrekon.repository.Institusi6Repository;
import com.mpc.vrekon.repository.InstitusiListRepository;
import com.mpc.vrekon.service.SetupService;
import com.mpc.vrekon.util.ConnectionHelper;
import com.mpc.vrekon.util.GlobalHelper;
import com.mpc.vrekon.util.ResponseHelper;

@Service
public class SetupServiceImpl implements SetupService{
	
	@Autowired DBSettingRepository dbSettingRepository;
	@Autowired Institusi1Repository institusi1Repository;
	@Autowired Institusi2Repository institusi2Repository;
	@Autowired Institusi3Repository institusi3Repository;
	@Autowired Institusi4Repository institusi4Repository;
	@Autowired Institusi5Repository institusi5Repository;
	@Autowired Institusi6Repository institusi6Repository;
	@Autowired DBTranslateRepository dbTranslateRepository;
	@Autowired InstitusiListRepository institusiListRepository;
	@PersistenceContext EntityManager entityManager;
	
	ResponseHelper responseHelper = new ResponseHelper();
	Logger log = Logger.getLogger(getClass());
	
	public Map<String, List<?>> getSetup(){
		List<InstitusiList> institusiList = new ArrayList<InstitusiList>();
		List<SetupServiceResponse> setupServiceList = new ArrayList<SetupServiceResponse>();
		
		try {
			institusiList = institusiListRepository.findAll();
			
			for (InstitusiList value : institusiList) {
				SetupServiceResponse setupServiceResponse = new SetupServiceResponse();
				
				setupServiceResponse.setName(value.getName());
				setupServiceResponse.setId(value.getId());
				setupServiceResponse.setInstitusiService(dbSettingRepository.findByIdInstitusi(value.getId()));
				
				setupServiceList.add(setupServiceResponse);
			}
			
			responseHelper.putData(setupServiceList, "Success", "");
		} catch (Exception e) {
			responseHelper.putData(setupServiceList, "Failed",e.getMessage());
			log.debug(e.getMessage());
		}
		
		return responseHelper.getResponseData();
	}
	
	public Map<String, List<?>> addInstitusi(InstitusiList institusiList, String submitType){
		try {
			Boolean error = false;
			long countData = 0;
			int countInstitusiList = 0;
			
			if (institusiList.getInstitusiTabel() == 1) {
				countData = institusi1Repository.count();
			} else if (institusiList.getInstitusiTabel() == 2) {
				countData = institusi2Repository.count();
			} else if (institusiList.getInstitusiTabel() == 3) {
				countData = institusi3Repository.count();
			} else if (institusiList.getInstitusiTabel() == 4) {
				countData = institusi4Repository.count();
			} else if (institusiList.getInstitusiTabel() == 5) {
				countData = institusi5Repository.count();
			} else if (institusiList.getInstitusiTabel() == 6) {
				countData = institusi6Repository.count();
			}
			
			log.debug("Count Data: "+countData);
			if (countData != 0) {
				responseHelper.putData(new ArrayList<Object>(), "Failed", "Table Institusi"+institusiList.getInstitusiTabel()+" not empty, please clear all data to use this table.");
				error = true;
			}
			
			if (institusiList.getInstitusiTabel() > 6){
				responseHelper.putData(new ArrayList<Object>(), "Failed", "Only 6 tables available, please check institusiTabel value.");
				error = true;
			}
			
			if(submitType.equals("add")){
				countInstitusiList = institusiListRepository.countByInstitusiTabel(institusiList.getInstitusiTabel());
			}else{
				countInstitusiList = institusiListRepository.countByInstitusiTabelAndIdNot(institusiList.getInstitusiTabel(), institusiList.getId());
			}
			
			log.debug("Check Table Use: "+( countInstitusiList != 0 ));
			if(countInstitusiList != 0){
				responseHelper.putData(new ArrayList<Object>(), "Failed", "Table Institusi"+institusiList.getInstitusiTabel()+" already use by other Institusi");
				error = true;
			}
			
			log.debug("Error Status: "+error);
			if (!error) {
				InstitusiList tmpInstitusiList = new InstitusiList();
						
				tmpInstitusiList = institusiListRepository.save(institusiList);
				responseHelper.putData(tmpInstitusiList, "Success", "");
			}
		} catch (Exception e) {
			responseHelper.putData(new ArrayList<Object>(), "Failed", e.getMessage());
			log.debug(e.getMessage());
		}
		
		return responseHelper.getResponseData();
	}
	
	public Map<String, List<?>> deleteInstitusi(Integer idInstitusi){		
		try {
			institusiListRepository.delete(idInstitusi);
			responseHelper.putData(new ArrayList<Object>(), "Success", "");
		} catch (Exception e) {
			responseHelper.putData(new ArrayList<Object>(), "Failed", e.getMessage());
			log.debug(e.getMessage());
		}
		
		return responseHelper.getResponseData();
	}
	
	public Map<String, List<?>> getDBSetting(DBSetting dbSetting){
		try {
			DBResponse dbResponse = new DBResponse();
			DBSetting dbSettingTmp = dbSettingRepository.findOne(dbSetting.getId());
			List<DBTranslate> dbTranslates = new ArrayList<DBTranslate>();
			
			dbTranslates = dbTranslateRepository.findByIdService(dbSettingTmp.getId());
			dbResponse.setDbHost(dbSettingTmp.getDbHost());
			dbResponse.setDbName(dbSettingTmp.getDbName());
			dbResponse.setDbPassword(dbSettingTmp.getDbPassword());
			dbResponse.setDbTableName(dbSettingTmp.getDbTableName());
			dbResponse.setDbType(dbSettingTmp.getDbType());
			dbResponse.setDbUsername(dbSettingTmp.getDbUsername());
			dbResponse.setStatus(dbSettingTmp.getStatus());
			dbResponse.setIdInstitusi(dbSettingTmp.getIdInstitusi());
			dbResponse.setId(dbSettingTmp.getId());
			dbResponse.setDbTranslates(dbTranslates);
			
			responseHelper.putData(dbResponse, "Success", "");
		} catch (Exception e) {
			responseHelper.putData(new ArrayList<Object>(), "Failed", e.getMessage());
		}
		return responseHelper.getResponseData();
	}
	
	public Map<String, List<?>> addDBSetting(DBRequest dbRequest, String submitType){
		try {
			List<DBTranslate> dbTranslates = new ArrayList<DBTranslate>();
			DBSetting dbSetting = dbRequest.getDbSetting();
			Boolean error = false;
			
			if(institusiListRepository.findOne(dbSetting.getIdInstitusi()) == null){
//			if(institusiListRepository.findOne(dbSetting.getIdInstitusi()) == null && submitType.equals("tambah")){
				responseHelper.putData(new ArrayList<Object>(), "Failed", "idInstitusi not found in table institusiList, please check your input again.");
				error = true;
			}
			
//			log.debug(dbSetting);
//			if(institusiListRepository.findByInstitusiTabelAndIdNot(dbSetting.getIdInstitusi(), dbSetting.getId()) != null && submitType.equals("ubah")){
//				responseHelper.putData(new ArrayList<Object>(), "Failed", "Table has been used by another service.");
//				error = true;
//			}
			
			if(!error){
				dbSetting = dbSettingRepository.save(dbSetting);
				
				for (DBTranslate dbTranslate : dbRequest.getDbTranslates()) {
					DBTranslate dbTranslateTmp = null;
					
					dbTranslate.setIdService(dbSetting.getId());
					dbTranslateTmp = dbTranslateRepository.save(dbTranslate);
					dbTranslates.add(dbTranslateTmp);
				}
				
				dbRequest.setDbSetting(dbSetting);
				dbRequest.setDbTranslates(dbTranslates);
				responseHelper.putData(dbRequest, "Success", "");	
			}
		} catch (Exception e) {
			responseHelper.putData(new ArrayList<Object>(), "Failed", e.getMessage());
			log.debug(e.getMessage());
		}
		
		return responseHelper.getResponseData();
	}
	
	public Map<String, List<?>> deleteDBSetting(DBSetting dbSetting){
		try {
			dbSettingRepository.delete(dbSetting);
			Query query = entityManager.createNativeQuery("DELETE FROM dbtranslate WHERE id_service = "+dbSetting.getId());
			responseHelper.putData(new ArrayList<Object>(), "Success", "");
		} catch (Exception e) {
			responseHelper.putData(new ArrayList<Object>(), "Failed", e.getMessage());
			log.debug(e.getMessage());
		}
		
		return responseHelper.getResponseData();
	}
	
	public Map<String, List<?>> dbCopyStart(DBCopyStartRequest dbCopyStartRequest){
		try {
			DBSetting dbSetting = dbSettingRepository.findOne(dbCopyStartRequest.getIdService());			
			Boolean error = false;
			Integer countExDatabase = 0;
			
			if(dbSetting == null){
				responseHelper.putData(dbCopyStartRequest, "Failed", "idService is not exist in database.");
				error = true;
			}
			
			InstitusiList institusiList = institusiListRepository.findOne(dbSetting.getIdInstitusi());
			if(institusiList == null){
				responseHelper.putData(dbCopyStartRequest, "Failed", "idInstitusi is not exist in database.");
				error = true;
			}
			
			if(!error){
				ConnectionHelper connectionHelper = new ConnectionHelper(dbSetting);
				Connection connection = connectionHelper.SetConnnection();
				List<DBTranslate> dbTranslates = new ArrayList<DBTranslate>();
				
				String select = "";
				String sql = "SELECT COUNT(*) AS count FROM "+dbSetting.getDbTableName();
				
				dbTranslates = dbTranslateRepository.findByIdService(dbSetting.getId());
				select = connectionHelper.setSelectedField(dbTranslates);
				
				ResultSet resultSet = connectionHelper.executeQuery(connection, sql);
				resultSet.next();
				countExDatabase = new Integer(resultSet.getString("count"));
				connection.close();
				
				dbTranslates = dbTranslateRepository.findByIdService(dbSetting.getId());
				
				for (int i = 0; i < countExDatabase; i++) {
					connection = connectionHelper.SetConnnection();
					if (dbSetting.getDbType().equals("mysql")) {						
						sql = "SELECT "+select+" FROM "+dbSetting.getDbTableName()+" LIMIT 1 OFFSET "+i;
					} if (dbSetting.getDbType().equals("oracle")) {	
						sql = "SELECT "+select+" FROM (SELECT ROWNUM RNUM, A.* FROM "+dbSetting.getDbTableName()+" A WHERE ROWNUM <= "+(i+1)+") WHERE RNUM >="+(i+1);
					}
					
					log.debug("SQL: "+sql);
					resultSet = connectionHelper.executeQuery(connection, sql);
					resultSet.next();
					
					if (institusiList.getInstitusiTabel() == 1) {
						Institusi1 institusi = new Institusi1();
						for (DBTranslate dbTranslate : dbTranslates) {
							BeanUtils.setProperty(
									institusi, 
									GlobalHelper.StrConvertToVar(dbTranslate.getTargetTableName()), 
									resultSet.getString(dbTranslate.getTargetTableName())
								);
						}	
						institusi1Repository.save(institusi);
						
						log.debug(institusi);
					} else if (institusiList.getInstitusiTabel() == 2) {
						Institusi2 institusi = new Institusi2();
						for (DBTranslate dbTranslate : dbTranslates) {
							BeanUtils.setProperty(
									institusi, 
									GlobalHelper.StrConvertToVar(dbTranslate.getTargetTableName()), 
									resultSet.getString(dbTranslate.getTargetTableName())
								);
						}						
						institusi2Repository.save(institusi);
						
						log.debug(institusi);
					} else if (institusiList.getInstitusiTabel() == 3) {
						Institusi3 institusi = new Institusi3();
						for (DBTranslate dbTranslate : dbTranslates) {
							BeanUtils.setProperty(
									institusi, 
									GlobalHelper.StrConvertToVar(dbTranslate.getTargetTableName()), 
									resultSet.getString(dbTranslate.getTargetTableName())
								);
						}						
						institusi3Repository.save(institusi);
						
						log.debug(institusi);
					} else if (institusiList.getInstitusiTabel() == 4) {
						Institusi4 institusi = new Institusi4();
						for (DBTranslate dbTranslate : dbTranslates) {
							BeanUtils.setProperty(
									institusi, 
									GlobalHelper.StrConvertToVar(dbTranslate.getTargetTableName()), 
									resultSet.getString(dbTranslate.getTargetTableName())
								);
						}						
						institusi4Repository.save(institusi);
						
						log.debug(institusi);
					} else if (institusiList.getInstitusiTabel() == 5) {
						Institusi5 institusi = new Institusi5();
						for (DBTranslate dbTranslate : dbTranslates) {
							BeanUtils.setProperty(
									institusi, 
									GlobalHelper.StrConvertToVar(dbTranslate.getTargetTableName()), 
									resultSet.getString(dbTranslate.getTargetTableName())
								);
						}						
						institusi5Repository.save(institusi);
						
						log.debug(institusi);
					} else if (institusiList.getInstitusiTabel() == 6) {
						Institusi6 institusi = new Institusi6();
						for (DBTranslate dbTranslate : dbTranslates) {
							BeanUtils.setProperty(
										institusi, 
										GlobalHelper.StrConvertToVar(dbTranslate.getTargetTableName()), 
										resultSet.getString(dbTranslate.getTargetTableName())
									);
						}						
						institusi6Repository.save(institusi);
						
						log.debug(institusi);
					}
					
					connection.close();
				}
			}
			responseHelper.putData(new ArrayList<Object>(), "Success", countExDatabase+" data migrated.");
		} catch (Exception e) {
			responseHelper.putData(new ArrayList<Object>(), "Failed", e.getMessage());
			log.debug(e);
		}
		
		return responseHelper.getResponseData();
	}
}
