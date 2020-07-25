package com.mpc.vrekon.service.impl;

import com.google.gson.Gson;
import com.mpc.vrekon.model.Application;
import com.mpc.vrekon.model.ResponseWrapper;
import com.mpc.vrekon.model.SourceConfig;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.repository.SourceConfigRepository;
import com.mpc.vrekon.repository.SourceTranslateRepository;
import com.mpc.vrekon.service.OperationService;
import com.mpc.vrekon.util.ResponseCode;
import com.mpc.vrekon.util.UploadFileMessageWrapper;
import com.mpc.vrekon.util.UtilHelper;
import com.mpc.vrekon.util.database.HibernateHelper;
import com.mpc.vrekon.util.database.connection.HibernateConfig;
import com.mpc.vrekon.util.spreadsheet.SpreadSheetISheet;
import com.mpc.vrekon.util.spreadsheet.SpreadSheetType;
import com.mpc.vrekon.util.spreadsheet.SpreadSheetWrapper;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    @Autowired
    SourceConfigRepository sourceConfigRepository;
    @Autowired
    SourceTranslateRepository sourceTranslateRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationContext context;

    Logger log = Logger.getLogger(getClass());
    ResponseWrapper responseWrapper;

    // TODO: not done yet
    public ResponseWrapper manualSyncron(HttpServletRequest servletRequest, Map<String, Object> request) {
        try {
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

    // TODO: all going well, except other than string value of compare result will return string (e.g. double of 50104873 will give return 5.0104873E7
    public ResponseWrapper compareApplication(HttpServletRequest servletRequest, Map<String, Object> request, HttpSession httpSession) {
        Map<String, Object> resultMap = new HashMap<>();
        try{
            String tempFrom = "temporary"+ request.get("idApplicationFrom").toString();
            String tempTo = "temporary"+ request.get("idApplicationTo").toString();
            Integer sourceFrom = Integer.valueOf(request.get("idSourceFrom").toString());
            Integer sourceTo = Integer.valueOf(request.get("idSourceTo").toString());

            Map[] compareFields = new Gson().fromJson(request.get("compareField").toString(), Map[].class);

            SessionFactory sessionFactory;
            Session session;
            HibernateConfig config = new HibernateConfig(new AnnotationConfiguration().configure(context.getResource("classpath:config/data/hibernate-config.xml").getFile()));
            sessionFactory = HibernateHelper.getSessionFactory(config);
            session = sessionFactory.openSession();
            Transaction compareTransaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(compareQueryBuilder(tempFrom, tempTo,sourceFrom, sourceTo, compareFields));
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List list = query.list();
            compareTransaction.commit();

            SpreadSheetWrapper workbook = new SpreadSheetWrapper(SpreadSheetType.XLSX);
            SpreadSheetISheet sheet =  workbook.getSheet();

            //create sheet header
            sheet.cell("A1", true).setValue("No");
            int indexField = 1;
            Map record = (Map) list.get(0);
            for (Object keys : record.keySet()) {
                String key = String.valueOf(keys);
                sheet.cell(indexField, 0, true).setValue(key);
                indexField++;
            }
            //create sheet content
            for (int recordIndex = 0; recordIndex < list.size(); recordIndex++) {
                record = (Map) list.get(recordIndex);
                sheet.cell("A", recordIndex +2, true).setValue(recordIndex+1);
                int col = 1;

                for (Object values : record.values()) {
                    String value = String.valueOf(values);
                    sheet.cell(col, recordIndex +1, true).setValue(value);
                    col++;
                }
            }
            String fileName = "compare report " + UtilHelper.convertNowDateWithFormat("yyyyMMdd") + ".xlsx";
            workbook.SaveAs(httpSession.getServletContext().getRealPath("/uploadFile/report/" + fileName));

            resultMap.put("compareResult", list);
            resultMap.put("compareReport", fileName);

            responseWrapper = new ResponseWrapper(ResponseCode.OK, resultMap);
            return responseWrapper;
        }catch (Exception e){
            e.printStackTrace();
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    public String compareQueryBuilder(String tempFrom, String tempTo, Integer sourceFrom, Integer sourceTo, Map[] compareFields){
        try {
            String query = "SELECT "+ UtilHelper.arrayToStringBacktick(compareFields, "keyName") +", \n" +
                    "       (CASE WHEN COUNT(*) > 1 THEN \"match\" ELSE \"unmatch\" END) AS \"status\" \n" +
                    "FROM ((SELECT " + UtilHelper.arrayToString(compareFields, "keyName", tempFrom)+ " \n" +
                    "       FROM "+tempFrom +" WHERE id_source_config = " + sourceFrom + "\n" +
                    "      ) UNION ALL \n" +
                    "      (SELECT " + UtilHelper.arrayToString(compareFields, "keyName", tempTo)+ " \n" +
                    "       FROM "+tempTo +" WHERE id_source_config = " + sourceTo + "\n" +
                    "      )\n" +
                    "     ) compare \n" +
                    "GROUP BY " + UtilHelper.arrayToStringBacktick(compareFields, "keyName");
            return query;
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
