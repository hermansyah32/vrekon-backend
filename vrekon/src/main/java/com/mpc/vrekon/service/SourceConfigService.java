package com.mpc.vrekon.service;

import com.mpc.vrekon.model.ResponseWrapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface SourceConfigService {

    ResponseWrapper sourceConfigGetByID(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigAdd(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession);
    ResponseWrapper sourceConfigEdit(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession);
    ResponseWrapper sourceConfigDelete(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigGetByApplication(HttpServletRequest servletRequest, Map<String, Object> request);
    ResponseWrapper sourceConfigGetByApplicationDetail(HttpServletRequest servletRequest, Map<String, Object> request);

}
