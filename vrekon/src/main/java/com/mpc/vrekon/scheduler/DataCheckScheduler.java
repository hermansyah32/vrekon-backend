package com.mpc.vrekon.scheduler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.mpc.vrekon.model.DBCopyStartRequest;
import com.mpc.vrekon.model.DBSetting;
import com.mpc.vrekon.repository.DBSettingRepository;
import com.mpc.vrekon.service.SetupService;
import com.mpc.vrekon.util.UtilHelper;

public class DataCheckScheduler{
	
	@Autowired
	private DBSettingRepository dbSettingRepository;
	
	@Autowired
	private SetupService setupService;

	Logger log = Logger.getLogger(getClass());
	UtilHelper utilHelper = new UtilHelper();
	HttpServletRequest request = null;

	public void databaseCheck() {
		log.info("==== Sceduller Message Database Check =====");
		try {
			List<DBSetting> dbSettings = dbSettingRepository.findByDbTypeOrDbType("mysql", "oracle");
			
			for (DBSetting dbSetting : dbSettings) {
				HttpSession session = null;
				
				DBCopyStartRequest dbCopyStartRequest = new DBCopyStartRequest();
				dbCopyStartRequest.setIdInstitusi(dbSetting.getIdInstitusi());
				dbCopyStartRequest.setIdService(dbSetting.getId());
				
				setupService.dbCopyStart(this.request, dbCopyStartRequest, session, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("==== End Sceduller Message Database Check =====");
	}
	
	public void directoryExcelCheck() {
		 log.info("==== Sceduller Message Excel Directory Check =====");
		 try {
			List<DBSetting> dbSettings = dbSettingRepository.findByDbType("excel");
				
			for (DBSetting dbSetting : dbSettings) {
				HttpSession session = null;
				
				DBCopyStartRequest dbCopyStartRequest = new DBCopyStartRequest();
				dbCopyStartRequest.setIdInstitusi(dbSetting.getIdInstitusi());
				dbCopyStartRequest.setIdService(dbSetting.getId());
				
				setupService.dbCopyStart(this.request, dbCopyStartRequest, session, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 log.info("==== End Sceduller Message Excel Directory Check =====");
	}
}
