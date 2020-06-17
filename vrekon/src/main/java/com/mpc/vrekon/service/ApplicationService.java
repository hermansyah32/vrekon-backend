package com.mpc.vrekon.service;

import com.mpc.vrekon.model.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ApplicationService {
    ResponseWrapper application (HttpServletRequest servletRequest);
    ResponseWrapper applicationGetDetail (HttpServletRequest servletRequest);
    ResponseWrapper applicationGetByID (HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper applicationAdd (HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper applicationEdit (HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper applicationDelete (HttpServletRequest servletRequest, Map<String, Object> request);
}
