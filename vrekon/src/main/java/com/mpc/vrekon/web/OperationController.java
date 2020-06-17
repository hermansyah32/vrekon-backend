package com.mpc.vrekon.web;

import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.service.ApplicationService;
import com.mpc.vrekon.service.OperationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class OperationController {

    @Autowired
    OperationService operationService;
    Logger log = Logger.getLogger(getClass());

    @RequestMapping(value="/manual-syncron", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper manualSyncron(HttpServletRequest servletRequest, Map<String, Object> request){
        return operationService.manualSyncron(servletRequest, request);
    }

    @RequestMapping(value="/import-file", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper importFile(HttpServletRequest servletRequest, Map<String, Object> request){
        return operationService.importFile(servletRequest, request);
    }

    @RequestMapping(value="/compare-application", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper compareApplication(HttpServletRequest servletRequest, Map<String, Object> request){
        return operationService.compareApplication(servletRequest, request);
    }
}
