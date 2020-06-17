package com.mpc.vrekon.service;

import com.mpc.vrekon.model.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SourceConfigService {

    ResponseWrapper sourceConfigGetByID(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigAdd(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigEdit(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigDelete(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigGetByApplication(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigGetByApplicationDetail(HttpServletRequest servletRequest, Map<String, Object> request);

}
