package com.mpc.vrekon.web;

import com.mpc.vrekon.model.Application;
import com.mpc.vrekon.service.ApplicationService;
import com.mpc.vrekon.service.RekonService;
import com.mpc.vrekon.util.ResponseCode;
import com.mpc.vrekon.model.ResponseWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    Logger log = Logger.getLogger(getClass());

    @RequestMapping(value="/application", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper application(HttpServletRequest servletRequest){
        return applicationService.application(servletRequest);
    }

    @RequestMapping(value="/application-detail", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper applicationGetDetail(HttpServletRequest servletRequest){
        return applicationService.applicationGetDetail(servletRequest);
    }

    @RequestMapping(value="/application-get-by-id", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper applicationGetByID(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        log.debug("Routing to /application-get-by-id: id =>"+ request.get("id").toString());
        return applicationService.applicationGetByID(servletRequest, request);
    }

    @RequestMapping(value="/application-add", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper applicationAdd(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        log.debug("Routing to /application-add: temporaryTable => "+ request.get("temporaryTable").toString());
        log.debug("Routing to /application-add: applicationName => "+ request.get("applicationName").toString());
        return applicationService.applicationAdd(servletRequest, request);
    }

    @RequestMapping(value="/application-edit", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper applicationEdit(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        log.debug("Routing to /application-add: id => "+ request.get("id").toString());
        log.debug("Routing to /application-add: temporaryTable => "+ request.get("temporaryTable").toString());
        log.debug("Routing to /application-add: applicationName => "+ request.get("applicationName").toString());
        return applicationService.applicationEdit(servletRequest, request);
    }

    @RequestMapping(value="/application-delete", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper applicationDelete(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        log.debug("Routing to /application-add: id => "+ request.get("id").toString());
        return applicationService.applicationDelete(servletRequest, request);
    }
}
