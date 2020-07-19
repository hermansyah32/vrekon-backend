package com.mpc.vrekon.service.impl;

import com.google.gson.Gson;
import com.mpc.vrekon.model.*;
import com.mpc.vrekon.repository.ApplicationRepository;
import com.mpc.vrekon.repository.SourceConfigRepository;
import com.mpc.vrekon.repository.SourceTranslateRepository;
import com.mpc.vrekon.service.SourceConfigService;
import com.mpc.vrekon.util.*;
import com.mpc.vrekon.util.database.HibernateHelper;
import com.mpc.vrekon.util.database.connection.HibernateConfig;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
public class SourceConfigServiceImpl implements SourceConfigService {
    @Autowired
    SourceConfigRepository sourceConfigRepository;
    @Autowired
    SourceTranslateRepository sourceTranslateRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationContext context;

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
     * @serialData add table name to request
     */
    // TODO: test add source config from DB
    public ResponseWrapper sourceConfigAdd(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession) {
        SourceConfig sourceConfig = null;
        StringBuilder allFileNameSkiped = new StringBuilder();
        Application application;
        try{
            application = applicationRepository.findOne(Integer.valueOf(request.get("idApplication").toString()));
            if (application == null){
                throw new EntityNotFoundException("No application with ID: " + request.get("id").toString());
            }

            String sourceType = request.get("sourceType").toString();
            if (!sourceType.equals("files")){
                sourceConfig = new SourceConfig(
                        Integer.valueOf(request.get("idApplication").toString()),
                        sourceType,
                        request.get("dbHost").toString(),
                        Integer.valueOf(request.get("dbPort").toString()),
                        request.get("dbName").toString(),
                        request.get("dbUsername").toString(),
                        request.get("dbPassword").toString(),
                        "",
                        request.get("tableName").toString(),
                        UtilHelper.convertDateToDB());
                sourceConfigRepository.save(sourceConfig);

                //create source translate
                //delimiter for field array is "," (comma)
                String[] originalFieldName =request.get("originalFieldName").toString().split(",");
                String[] temporaryFieldName =request.get("temporaryFieldName").toString().split(",");
                if (originalFieldName.length == temporaryFieldName.length){
                    for (int fieldIndex = 0; fieldIndex < originalFieldName.length; fieldIndex++) {
                        SourceTranslate sourceTranslate = new SourceTranslate(sourceConfig.getId(),originalFieldName[fieldIndex], temporaryFieldName[fieldIndex]);
                        sourceTranslateRepository.save(sourceTranslate);
                    }
                }else {
                    throw new Exception("original field and temporary field have different field length");
                }

                //create temporary table and synchronize data
                //temporarySession for temporary table
                //sourceSession for target table
                TemporaryTable temporaryTable = new TemporaryTable();
                temporaryTable.tableName("temporary"+application.getId());
                SessionFactory temporaryFactory = HibernateHelper.getSessionFactory(
                        new AnnotationConfiguration().configure(context.getResource("classpath:config/data/hibernate-config.xml").getFile()).setNamingStrategy(temporaryTable));
                Session temporarySession = temporaryFactory.openSession();
                SessionFactory sessionFactory;
                Session sourceSession;
                Integer sourceRowCount;
                HibernateConfig config = new HibernateConfig(sourceConfig);
                sessionFactory = HibernateHelper.getSessionFactory(config);
                sourceSession = sessionFactory.openSession();

                //start to copy data
                if (sourceSession != null){
                    sourceSession.beginTransaction();
                    log.debug(new Gson().toJson(sourceConfig));
                    SQLQuery query = sourceSession.createSQLQuery("SELECT COUNT(*) FROM " + sourceConfig.getTableName());
                    sourceRowCount = ((BigInteger) query.uniqueResult()).intValue();
                    Map record = null;
                    for (int row = 0; row < sourceRowCount; row++) {
                        if (sourceConfig.getSourceType().equals("mysql")){
                            query = sourceSession.createSQLQuery("SELECT "+ UtilHelper.arrayToString(originalFieldName) +" FROM " + sourceConfig.getTableName() +" LIMIT 1 OFFSET "+ row);
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            record = (Map) query.list().get(0);
                        }

                        if (sourceConfig.getSourceType().equals("oracle")){
                            query = sourceSession.createSQLQuery("SELECT "+ UtilHelper.arrayToString(originalFieldName) +" FROM (SELECT ROWNUM RNUM, A.* FROM "+ sourceConfig.getTableName() +" A WHERE ROWNUM <= "+ (row + 1) +") WHERE RNUM >="+ (row + 1));
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            record = (Map) query.list().get(0);
                        }

                        if (record != null){
                            temporarySession.beginTransaction();
                            temporaryTable.translateField(temporaryFieldName, originalFieldName, record);
                            log.debug("TEST");
                            log.debug(new Gson().toJson(temporaryTable));
                            temporarySession.save(temporaryTable);
                        }
                    }
                }
                //update application temporary table name
                application.setTemporaryTabel(application.getId());
                applicationRepository.save(application);
            }
            else {
                StringBuilder allFileName = new StringBuilder();
                if (files!= null){
                    sourceConfig = new SourceConfig(
                            Integer.valueOf(request.get("idApplication").toString()),
                            sourceType,
                            "",
                            0,
                            "",
                            "",
                            "",
                            "",
                            "",
                            UtilHelper.convertDateToDB());
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
                            throw new FileUploadException("Unknown file");
                        }
                        //check file exists
                        if (!fileTransfer.exists()){
                            allFileNameSkiped.append(fileExtention).append("/").append(fileName).append("%");
                            continue;
                        }
                        allFileName.append(fileExtention).append("/").append(fileName).append("%");
                    }
                    //delimiter for each uploaded file
                    allFileName.append(";");
                    allFileName = new StringBuilder(allFileName.toString().replace("%;", ""));
                    log.debug("Skiped File: " + allFileNameSkiped);

                    sourceConfig.setFileNames(allFileName.toString());
                    sourceConfigRepository.save(sourceConfig);

                    //create source translate
                    //delimiter for field array is "," (comma)
                    String[] originalFieldName =request.get("originalFieldName").toString().split(",");
                    String[] temporaryFieldName =request.get("temporaryFieldName").toString().split(",");
                    if (originalFieldName.length == temporaryFieldName.length){
                        for (int fieldIndex = 0; fieldIndex < originalFieldName.length; fieldIndex++) {
                            SourceTranslate sourceTranslate = new SourceTranslate(sourceConfig.getId(),originalFieldName[fieldIndex], temporaryFieldName[fieldIndex]);
                            sourceTranslateRepository.save(sourceTranslate);
                        }
                    }else {
                        throw new Exception("original field and temporary field have different field length");
                    }
                }else{
                    throw new FileNotFoundException("Uploaded files not found");
                }

                //create source translate
                String[] originalFieldName =request.get("originalFieldName").toString().split(",");
                String[] temporaryFieldName =request.get("temporaryFieldName").toString().split(",");
                if (originalFieldName.length == temporaryFieldName.length){
                    for (int fieldIndex = 0; fieldIndex < originalFieldName.length; fieldIndex++) {
                        SourceTranslate sourceTranslate = new SourceTranslate(sourceConfig.getId(),originalFieldName[fieldIndex], temporaryFieldName[fieldIndex]);
                        sourceTranslateRepository.save(sourceTranslate);
                    }
                }else {
                    throw new Exception("original field and temporary field have different field lenght");
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

                //create temporary table
                TemporaryTable temporaryTable = new TemporaryTable();
                temporaryTable.tableName("temporary"+application.getId());
                SessionFactory temporaryFactory = HibernateHelper.getSessionFactory(
                        new AnnotationConfiguration().configure("classpath:config/data/hibernate-config.xml")
                                .setNamingStrategy(temporaryTable));
                Session temporarySession = temporaryFactory.openSession();
            }

            return responseWrapper;
        }catch (Exception e){
            e.printStackTrace();
            if (sourceConfig != null)
                sourceConfigRepository.delete(sourceConfig);
            responseWrapper = new ResponseWrapper<String>();
            responseWrapper.systemError(e.getMessage());
            return responseWrapper;
        }
    }

    // TODO: not done yet
    public ResponseWrapper sourceConfigEdit(HttpServletRequest servletRequest, Map<String, Object> request, MultipartFile[] files, HttpSession httpSession) {
        SourceConfig sourceConfig = null;
        StringBuilder allFileNameSkiped = new StringBuilder();
        try{
            SourceConfig tempData = sourceConfigRepository.findOne(Integer.valueOf(request.get("id").toString()));
            if (tempData == null){
                throw new EntityNotFoundException("No data with ID: " + request.get("id").toString());
            }

            String sourceType = request.get("sourceType").toString();
            if (sourceType.equals("mysql") || sourceType.equals("oracle")){
                sourceConfig = new SourceConfig(
                        Integer.valueOf(request.get("idApplication").toString()),sourceType,request.get("dbHost").toString(),
                        Integer.valueOf(request.get("dbPort").toString()), request.get("dbName").toString(), request.get("dbUsername").toString(),
                        request.get("dbPassword").toString(), "", UtilHelper.convertDateToDB(), request.get("tableName").toString());
                sourceConfig.setId(tempData.getId());
                sourceConfigRepository.save(sourceConfig);

                //remove all source translate
                String removeSourceTranslateSQL = "DELETE FROM source_config WHERE id_source_config = ?";
                Query query = entityManager.createNativeQuery(removeSourceTranslateSQL);
                query.setParameter(1, sourceConfig.getId());
                query.executeUpdate();
                //create source translate
                String[] originalFieldName =request.get("originalFieldName").toString().split(",");
                String[] temporaryFieldName =request.get("temporaryFieldName").toString().split(",");
                if (originalFieldName.length == temporaryFieldName.length){
                    for (int fieldIndex = 0; fieldIndex < originalFieldName.length; fieldIndex++) {
                        SourceTranslate sourceTranslate = new SourceTranslate(sourceConfig.getId(),originalFieldName[fieldIndex], temporaryFieldName[fieldIndex]);
                        sourceTranslateRepository.save(sourceTranslate);
                    }
                }else {
                    throw new Exception("original field and temporary field have different field lenght");
                }
            }else {
                StringBuilder allFileName = new StringBuilder();
                if (files!= null){
                    sourceConfig = new SourceConfig(
                            Integer.valueOf(request.get("idApplication").toString()),sourceType,"",
                            0, "", "","", "", UtilHelper.convertDateToDB(), "");
                    sourceConfig.setId(tempData.getId());
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

            //create source translate
            String[] originalFieldName =request.get("originalFieldName").toString().split(",");
            String[] temporaryFieldName =request.get("temporaryFieldName").toString().split(",");
            if (originalFieldName.length == temporaryFieldName.length){
                for (int fieldIndex = 0; fieldIndex < originalFieldName.length; fieldIndex++) {
                    SourceTranslate sourceTranslate = new SourceTranslate(sourceConfig.getId(),originalFieldName[fieldIndex], temporaryFieldName[fieldIndex]);
                    sourceTranslateRepository.save(sourceTranslate);
                }
            }else {
                throw new Exception("original field and temporary field have different field lenght");
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
            if (sourceConfig == null)
                sourceConfigRepository.delete(sourceConfig);
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
