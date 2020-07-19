package com.mpc.vrekon.service.impl;

import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.model.SourceConfig;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.repository.SourceConfigRepository;
import com.mpc.vrekon.service.OperationService;
import com.mpc.vrekon.util.ResponseCode;
import com.mpc.vrekon.util.UploadFileMessageWrapper;
import com.mpc.vrekon.util.UtilHelper;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    @Autowired
    SourceConfigRepository sourceConfigRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    Logger log = Logger.getLogger(getClass());
    ResponseWrapper responseWrapper;

    // TODO: not done yet
    public ResponseWrapper manualSyncron(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
            log.debug(request.get("id").toString());
            SourceConfig sourceConfig = sourceConfigRepository.findOne(Integer.valueOf(request.get("id").toString()));
            if (sourceConfig == null)
                throw new EntityNotFoundException("No data with data ID: " + request.get("idApplication").toString());

            responseWrapper = new ResponseWrapper<List<String>>(ResponseCode.OK, new ArrayList<String>());
            return responseWrapper;
        }catch (Exception e) {
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    // TODO: not done yet
    public ResponseWrapper importFile(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession) {
        SourceConfig sourceConfig;
        StringBuilder allFileNameSkiped = new StringBuilder();
        try{
            sourceConfig = sourceConfigRepository.findOne(Integer.valueOf(request.get("id").toString()));
            if (sourceConfig == null){
                throw new EntityNotFoundException("No data with ID: " + request.get("id").toString());
            }

            if (sourceConfig.getSourceType().equals("files")){
                StringBuilder allFileName = new StringBuilder();
                if (files!= null){
                    for(int indexFile = 0; indexFile < files.length; indexFile++){
                        String fileName = sourceConfig.getId()+"_"+UtilHelper.convertDateToView(new Date(), "yyyyMMddHHmmss")
                                +"_"+indexFile+"."+UtilHelper.getFileExtension(files[indexFile].getOriginalFilename());
                        String fileExtention = UtilHelper.getFileExtension(files[indexFile].getOriginalFilename());

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

    // TODO: not done yet
    public ResponseWrapper compareApplication(HttpServletRequest servletRequest, Map<String, Object> request) {
        return null;
    }
}
