package com.mpc.vrekon.web;

import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.service.ApplicationService;
import com.mpc.vrekon.service.SourceConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SourceConfigController {

    @Autowired
    SourceConfigService sourceConfigService;
    Logger log = Logger.getLogger(getClass());

    @RequestMapping(value="/source-config-by-id-application", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigGetByApplication(HttpServletRequest servletRequest, Map<String, Object> request){
        return sourceConfigService.sourceConfigGetByApplication(servletRequest, request);
    }

    @RequestMapping(value="/source-config-by-id-application-detail", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigGetByApplicationDetail(HttpServletRequest servletRequest, Map<String, Object> request){
        return sourceConfigService.sourceConfigGetByApplicationDetail(servletRequest, request);
    }

    @RequestMapping(value="/source-config-by-id-source-config", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigGetByID(HttpServletRequest servletRequest, Map<String, Object> request){
        return sourceConfigService.sourceConfigGetByID(servletRequest, request);
    }

    @RequestMapping(value="/source-config-add", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigAdd(HttpServletRequest servletRequest, Map<String, Object> request){
        return sourceConfigService.sourceConfigAdd(servletRequest, request);
    }

    @RequestMapping(value="/source-config-edit", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigEdit(HttpServletRequest servletRequest, Map<String, Object> request){
        return sourceConfigService.sourceConfigEdit(servletRequest, request);
    }

    @RequestMapping(value="/source-config-delete", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigDelete(HttpServletRequest servletRequest, Map<String, Object> request){
        return sourceConfigService.sourceConfigDelete(servletRequest, request);
    }
}
