package com.mpc.vrekon.service.impl;

import com.google.gson.Gson;
import com.mpc.vrekon.model.Application;
import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.model.SourceConfig;
import com.mpc.vrekon.model.SourceTranslate;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.repository.SourceConfigRepository;
import com.mpc.vrekon.repository.SourceTranslateRepository;
import com.mpc.vrekon.service.SourceConfigService;
import com.mpc.vrekon.util.*;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SourceConfigServiceImpl implements SourceConfigService {
    @Autowired
    SourceConfigRepository sourceConfigRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    @PersistenceContext
    EntityManager entityManager;
    Logger log = Logger.getLogger(getClass());
    ResponseWrapper responseWrapper;

    // TODO : add source translate to response
    public ResponseWrapper sourceConfigGetByID(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            SourceConfig sourceConfig = sourceConfigRepository.findOne(Integer.valueOf(request.get("id").toString()));
            if (sourceConfig == null)
                throw new EntityNotFoundException("No data with data ID: " + request.get("idApplication").toString());

            responseWrapper = new ResponseWrapper<SourceConfig>(ResponseCode.OK, sourceConfig);
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    /**
     * Add source config.
     * @serialData sourceType: mysql, oracle, files(used for upload file)
     * @return response wrapper
     */
    public ResponseWrapper sourceConfigAdd(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession) {
        SourceConfig sourceConfig;
        StringBuilder allFileNameSkiped = new StringBuilder();
        try{
            if (applicationRepository.findOne(Integer.valueOf(request.get("idApplication").toString())) == null){
                throw new EntityNotFoundException("No application with ID: " + request.get("id").toString());
            }

            String sourceType = request.get("sourceType").toString();
            if (sourceType.equals("mysql") || sourceType.equals("oracle")){
                sourceConfig = new SourceConfig(
                        Integer.valueOf(request.get("idApplication").toString()),sourceType,request.get("dbHost").toString(),
                        Integer.valueOf(request.get("dbPort").toString()), request.get("dbName").toString(), request.get("dbUsername").toString(),
                        request.get("dbPassword").toString(), "", UtilHelper.convertDateToDB(), request.get("tableName").toString());
                sourceConfigRepository.save(sourceConfig);

                //TODO: create source translate, still confuse
            }else {
                StringBuilder allFileName = new StringBuilder();
                if (files!= null){
                    sourceConfig = new SourceConfig(
                            Integer.valueOf(request.get("idApplication").toString()),sourceType,"",
                            0, "", "","", "", UtilHelper.convertDateToDB(), "");
                    sourceConfig = sourceConfigRepository.save(sourceConfig);

                    for(int indexFile = 0; indexFile < files.length; indexFile++){
                        String fileName = sourceConfig.getId()+"_"+UtilHelper.convertDateToView(new Date(), "yyyyMMddHHmmss")
                                +"_"+indexFile+"."+UtilHelper.getFileExtension(files[indexFile].getOriginalFilename());
                        String fileExtention = UtilHelper.getFileExtension(files[indexFile].getOriginalFilename());
                        log.debug("Nama file: " +fileName);
                        File fileTransfer;
                        if(fileExtention.equals("xlsx") || fileExtention.equals("xls")
                                || fileExtention.equals("txt") || fileExtention.equals("rpt")){
                            fileTransfer = new File(httpSession.getServletContext().getRealPath("/uploadFile/documents/" + fileExtention + "/"), fileName);
                            files[indexFile].transferTo(fileTransfer);
                            log.debug("files transfered");
                        }else {
                            //unknown file extention, cancel transaction, delete incoming record
                            sourceConfigRepository.delete(sourceConfig.getId());
                            throw new FileUploadException("Unknown file");
                        }
                        //check file exists
                        if (!fileTransfer.exists()){
                            allFileNameSkiped.append(fileExtention).append("/").append(fileName).append("%");
                            continue;
                        }
                        allFileName.append(fileExtention).append("/").append(fileName).append("%");
                    }
                    allFileName.append(";");
                    allFileName = new StringBuilder(allFileName.toString().replace("%;", ""));
                    log.debug("Skiped File: " + allFileNameSkiped);

                    sourceConfig.setFileNames(allFileName.toString());
                    sourceConfigRepository.save(sourceConfig);

                    //TODO: create source translate, still confuse
                }else{
                    throw new FileNotFoundException("Uploaded files not found");
                }
            }

            //if some files failed to upload, set other message
            if (allFileNameSkiped.length()<1){
                responseWrapper = new ResponseWrapper<UploadFileMessageWrapper>(
                        ResponseCode.OK,
                        new UploadFileMessageWrapper("Some files failed to upload", false, allFileNameSkiped.toString()));
            }else {
                responseWrapper = new ResponseWrapper<UploadFileMessageWrapper>(
                        ResponseCode.OK,
                        new UploadFileMessageWrapper("Success upload all files", true, ""));
            }
            return responseWrapper;
        }catch (Exception e){
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    // TODO: Penambahan parameter request "idSourceConfig"
    public ResponseWrapper sourceConfigEdit(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession) {
        try{
            SourceConfig sourceConfig = sourceConfigRepository.findOne(Integer.valueOf(request.get("idSourceConfig").toString()));
            if (sourceConfig == null){
                throw new EntityNotFoundException("No data with ID: " + request.get("id").toString());
            }

            String sourceType = request.get("sourceType").toString();
            if (sourceType.equals("mysql") || sourceType.equals("oracle")){
                sourceConfig = new SourceConfig(
                        Integer.valueOf(request.get("idApplication").toString()),sourceType,request.get("dbHost").toString(),
                        Integer.valueOf(request.get("dbPort").toString()), request.get("dbName").toString(), request.get("dbUsername").toString(),
                        request.get("dbPassword").toString(), "", UtilHelper.convertDateToDB(), request.get("tableName").toString());
                //create source translate
            }else {
                StringBuilder allFileName = new StringBuilder();
                if (files!= null){
                    sourceConfig = new SourceConfig(
                            Integer.valueOf(request.get("idApplication").toString()),sourceType,"",
                            0, "", "","", "", UtilHelper.convertDateToDB(), "");

                    log.debug("Start to save file");
                    Integer i = 1;
                    for(MultipartFile file : files){
                        String fileName = sourceConfig.getId()+"_"+UtilHelper.convertDateToView(new Date(), "yyyyMMddHHmmss")+"_"+i+"."+UtilHelper.getFileExtension(file.getOriginalFilename());
                        String fileExtention = UtilHelper.getFileExtension(file.getOriginalFilename());

                        if(fileExtention.equals("xlsx") || fileExtention.equals("xls")
                                ||fileExtention.equals("txt") || fileExtention.equals("rpt")){
                            File fileTransfer = new File(httpSession.getServletContext().getRealPath("/uploadFile/documents/" + fileExtention + "/"), fileName);
                            file.transferTo(fileTransfer);
                        }else {
                            //unknown file extention, cancel transaction, delete incomming record
                            sourceConfigRepository.delete(sourceConfig.getId());
                            throw new FileUploadException("Unknown file");
                        }
                        allFileName.append(fileExtention).append("/").append(fileName).append("%");
                        i++;
                    }
                    allFileName.append(";");
                    allFileName = new StringBuilder(allFileName.toString().replace("%;", ""));
                    log.debug("Done to save file");

                    log.debug("Update files record");
                    sourceConfig.setFileNames(allFileName.toString());
                    sourceConfigRepository.save(sourceConfig);
                }else{
                    throw new FileNotFoundException("Uploaded files not found");
                }
            }
            sourceConfigRepository.save(sourceConfig);
            responseWrapper = new ResponseWrapper<List<String>>(ResponseCode.OK, new ArrayList<String>());
            return responseWrapper;
        }catch (Exception e){
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    public ResponseWrapper sourceConfigDelete(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            sourceConfigRepository.delete(Integer.valueOf(request.get("id").toString()));
            responseWrapper = new ResponseWrapper<List<String>>(ResponseCode.OK, new ArrayList<String>());
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    public ResponseWrapper sourceConfigGetByApplication(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            List<SourceConfig> sourceConfig = sourceConfigRepository.findByIdApplication(Integer.valueOf(request.get("idApplication").toString()));
            if (sourceConfig.isEmpty())
                throw new EntityNotFoundException("No data with Application ID: " + request.get("idApplication").toString());

            responseWrapper = new ResponseWrapper<>(ResponseCode.OK, sourceConfig);
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    // TODO: add more detail to response
    public ResponseWrapper sourceConfigGetByApplicationDetail(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            List<SourceConfig> sourceConfig = sourceConfigRepository.findByIdApplication(Integer.valueOf(request.get("idApplication").toString()));
            if (sourceConfig.isEmpty())
                throw new EntityNotFoundException("No data with Application ID: " + request.get("idApplication").toString());

            responseWrapper = new ResponseWrapper<>(ResponseCode.OK, sourceConfig);
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }


}
