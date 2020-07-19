package com.mpc.vrekon.web;

import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.service.OperationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class OperationController {

    @Autowired
    OperationService operationService;
    Logger log = Logger.getLogger(getClass());

    @RequestMapping(value="/manual-syncron", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper manualSyncron(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        return operationService.manualSyncron(servletRequest, request);
    }

    @RequestMapping(value="/import-file", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper importFile(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request,
                                      @RequestParam(value="fileName", required=false) MultipartFile[] fileName,
                                      HttpSession httpSession){
        return operationService.importFile(servletRequest, request, fileName, httpSession);
    }

    @RequestMapping(value="/compare-application", method= RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper compareApplication(HttpServletRequest servletRequest, @RequestBody Map<String, Object> request){
        return operationService.compareApplication(servletRequest, request);
    }
}
