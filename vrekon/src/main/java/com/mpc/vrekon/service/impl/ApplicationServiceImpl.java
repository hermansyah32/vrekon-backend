package com.mpc.vrekon.service.impl;

import com.google.gson.Gson;
import com.mpc.vrekon.model.Application;
import com.mpc.vrekon.util.ResponseCode;
import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.service.ApplicationService;
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
            responseWrapper = new ResponseWrapper<List<Application>>(ResponseCode.OK, applicationRepository.findAll());
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
            responseWrapper = new ResponseWrapper<Application>(ResponseCode.OK, application);
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
