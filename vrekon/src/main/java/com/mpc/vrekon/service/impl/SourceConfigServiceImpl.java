package com.mpc.vrekon.service.impl;

import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.repository.SourceConfigRepository;
import com.mpc.vrekon.service.SourceConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@Transactional
public class SourceConfigServiceImpl implements SourceConfigService {
    @Autowired
    SourceConfigRepository sourceConfigRepository;

    @PersistenceContext
    EntityManager entityManager;
    Logger log = Logger.getLogger(getClass());
    ResponseWrapper responseWrapper;


    public ResponseWrapper sourceConfigGetByID(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }

    public ResponseWrapper sourceConfigAdd(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }

    public ResponseWrapper sourceConfigEdit(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }

    public ResponseWrapper sourceConfigDelete(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }

    public ResponseWrapper sourceConfigGetByApplication(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }

    public ResponseWrapper sourceConfigGetByApplicationDetail(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }
}
