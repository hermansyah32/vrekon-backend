package com.mpc.vrekon.service.impl;

import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.service.OperationService;
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
public class OperationServiceImpl implements OperationService {

    @PersistenceContext
    EntityManager entityManager;
    Logger log = Logger.getLogger(getClass());
    ResponseWrapper responseWrapper;

    public ResponseWrapper manualSyncron(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }

    public ResponseWrapper importFile(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }

    public ResponseWrapper compareApplication(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }
}
