package com.mpc.vrekon.service;

import com.mpc.vrekon.model.ResponseWrapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface OperationService {
    ResponseWrapper manualSyncron(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper importFile(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession);
    ResponseWrapper compareApplication(HttpServletRequest servletRequest, Map<String, Object> request, HttpSession httpSession);
}
