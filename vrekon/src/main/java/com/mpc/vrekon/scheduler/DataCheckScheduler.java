package com.mpc.vrekon.scheduler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mpc.vrekon.model.SourceConfig;
import com.mpc.vrekon.repository.SourceConfigRepository;
import com.mpc.vrekon.service.SourceConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import com.mpc.vrekon.util.UtilHelper;

public class DataCheckScheduler{
	
	@Autowired
	private SourceConfigRepository sourceConfigRepository;
	
	@Autowired
	private SourceConfigService sourceConfigService;

	Logger log = Logger.getLogger(getClass());
	HttpServletRequest request = null;

	public void databaseCheck() {
		log.info("==== Sceduller Message Database Check =====");
		try {
			List<SourceConfig> sourceConfigs = sourceConfigRepository.findBySourceTypeOrSourceType("mysql", "oracle");
			
			for (SourceConfig sourceConfig : sourceConfigs) {
				HttpSession session = null;
				
//				DBCopyStartRequest dbCopyStartRequest = new DBCopyStartRequest();
//				dbCopyStartRequest.setIdInstitusi(dbSetting.getIdInstitusi());
//				dbCopyStartRequest.setIdService(dbSetting.getId());
//
//				setupService.dbCopyStart(this.request, dbCopyStartRequest, session, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("==== End Sceduller Message Database Check =====");
	}
	
	public void directoryExcelCheck() {
		 log.info("==== Sceduller Message Excel Directory Check =====");
		 try {
			List<SourceConfig> sourceConfigs = sourceConfigRepository.findBySourceType("excel");
				
			for (SourceConfig sourceConfig : sourceConfigs) {
				HttpSession session = null;
				
//				DBCopyStartRequest dbCopyStartRequest = new DBCopyStartRequest();
//				dbCopyStartRequest.setIdInstitusi(dbSetting.getIdInstitusi());
//				dbCopyStartRequest.setIdService(dbSetting.getId());
//
//				setupService.dbCopyStart(this.request, dbCopyStartRequest, session, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 log.info("==== End Sceduller Message Excel Directory Check =====");
	}
}
