package com.mpc.vrekon.web;

import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.service.ApplicationService;
import com.mpc.vrekon.service.SourceConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SourceConfigController {

    @Autowired
    SourceConfigService sourceConfigService;
    Logger log = Logger.getLogger(getClass());

    @RequestMapping(value="/source-config-by-id-application", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigGetByApplication(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        return sourceConfigService.sourceConfigGetByApplication(servletRequest, request);
    }

    @RequestMapping(value="/source-config-by-id-application-detail", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigGetByApplicationDetail(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        return sourceConfigService.sourceConfigGetByApplicationDetail(servletRequest, request);
    }

    @RequestMapping(value="/source-config-by-id-source-config", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigGetByID(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        return sourceConfigService.sourceConfigGetByID(servletRequest, request);
    }

    @RequestMapping(value="/source-config-add", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigAdd(HttpServletRequest servletRequest, @RequestParam Map<String, Object> request,
                                           @RequestParam(value="fileName", required=false) MultipartFile[] fileName,
                                           HttpSession httpSession){
        return sourceConfigService.sourceConfigAdd(servletRequest, request, fileName, httpSession);
    }

    @RequestMapping(value="/source-config-edit", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigEdit(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request,
                                            @RequestParam(value="fileName", required=false) MultipartFile[] files,
                                            HttpSession httpSession){
        return sourceConfigService.sourceConfigEdit(servletRequest, request, files, httpSession);
    }

    @RequestMapping(value="/source-config-delete", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper sourceConfigDelete(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        return sourceConfigService.sourceConfigDelete(servletRequest, request);
    }
}
