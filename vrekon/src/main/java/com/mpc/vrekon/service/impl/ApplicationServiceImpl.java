package com.mpc.vrekon.service.impl;

import com.google.gson.Gson;
import com.mpc.vrekon.model.Application;
import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.model.SourceConfig;
import com.mpc.vrekon.model.SourceTranslate;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.repository.SourceConfigRepository;
import com.mpc.vrekon.repository.SourceTranslateRepository;
import com.mpc.vrekon.service.ApplicationService;
import com.mpc.vrekon.util.ResponseCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    SourceConfigRepository sourceConfigRepository;
    @Autowired
    SourceTranslateRepository sourceTranslateRepository;

    @PersistenceContext
    EntityManager entityManager;
    Logger log = Logger.getLogger(getClass());
    ResponseWrapper responseWrapper;

    public ResponseWrapper application(HttpServletRequest servletRequest) {
        try {
            List<Application> applications = applicationRepository.findAll();
            log.debug(new Gson().toJson(applications.get(0)));
            responseWrapper = new ResponseWrapper<>(ResponseCode.OK, applications);
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    // TODO: Mengambil semua data beserta source config dan source translatenya
    public ResponseWrapper applicationGetDetail(HttpServletRequest servletRequest) {
        try {
            List<Application> applications = applicationRepository.findAll();
            List<Map<String, Object>> applicationDetails = new ArrayList<>();
            for(Application application: applications){
                List<SourceConfig> sourceConfigs =sourceConfigRepository.findByIdApplication(application.getId());
                List<Map<String, Object>> sourceConfigsMap = new ArrayList<>();
                for(SourceConfig sourceConfig: sourceConfigs){
                    List<SourceTranslate> sourceTranslates = sourceTranslateRepository.findByIdSourceConfig(sourceConfig.getId());
                    sourceConfig.addToMap("sourceTranslate", sourceTranslates);
                    sourceConfigsMap.add(sourceConfig.toHashMap());
                }
                application.addToMap("sourceConfig", sourceConfigsMap);
                applicationDetails.add(application.toHashMap());
            }
            responseWrapper = new ResponseWrapper<>(ResponseCode.OK, applicationDetails);
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    // TODO: Mengambil data berdasarkan ID beserta source config dan source translatenya
    public ResponseWrapper applicationGetByID(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            Application application = applicationRepository.findOne(Integer.valueOf(request.get("id").toString()));
            if (application == null)
                throw new EntityNotFoundException("No data with ID: " + request.get("id").toString());
            List<SourceConfig> sourceConfigs =sourceConfigRepository.findByIdApplication(application.getId());
            List<Map<String, Object>> sourceConfigsMap = new ArrayList<>();
            for(SourceConfig sourceConfig: sourceConfigs){
                List<SourceTranslate> sourceTranslates = sourceTranslateRepository.findByIdSourceConfig(sourceConfig.getId());
                sourceConfig.addToMap("sourceTranslate", sourceTranslates);
                sourceConfigsMap.add(sourceConfig.toHashMap());
            }
            application.addToMap("sourceConfig", sourceConfigsMap);
            responseWrapper = new ResponseWrapper<>(ResponseCode.OK, application.toHashMap());
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    public ResponseWrapper applicationAdd(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            Application application = new Application(
                    Integer.valueOf(request.get("temporaryTable").toString()),
                    request.get("applicationName").toString()
            );
            applicationRepository.save(application);
            responseWrapper = new ResponseWrapper<List<String>>(ResponseCode.OK, new ArrayList<String>());
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    public ResponseWrapper applicationEdit(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            Application application = applicationRepository.findOne(Integer.valueOf(request.get("id").toString()));
            if (application == null)
                throw new EntityNotFoundException("No data with ID: " + request.get("id").toString());
            application.setTemporaryTabel(Integer.valueOf(request.get("temporaryTable").toString()));
            application.setApplicationName(request.get("applicationName").toString());
            applicationRepository.save(application);
            responseWrapper = new ResponseWrapper<List<String>>(ResponseCode.OK, new ArrayList<String>());
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    public ResponseWrapper applicationDelete(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            applicationRepository.delete(Integer.valueOf(request.get("id").toString()));
            responseWrapper = new ResponseWrapper<List<String>>(ResponseCode.OK, new ArrayList<String>());
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }


}
