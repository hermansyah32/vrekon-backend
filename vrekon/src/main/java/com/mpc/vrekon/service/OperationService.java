package com.mpc.vrekon.service;

import com.mpc.vrekon.model.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface OperationService {
    ResponseWrapper manualSyncron(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper importFile(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper compareApplication(HttpServletRequest servletRequest, Map<String, Object> request);
}
