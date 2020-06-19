package com.mpc.vrekon.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONWriter;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportWrapper {

    private String sourceFile;
    private SourceType sourceType;
    private List<String> stringList;

    private Logger log = Logger.getLogger(getClass());

    private enum SourceType{
        RPT_FILE("rpt"),
        XLS_FILE("xlx");

        private static final Map<String, SourceType> enumList = new HashMap<String, SourceType>();

        private String extention;

        private String getExtention(){
            return this.extention;
        }

        private SourceType(String extention){
            this.extention = extention;
        }
    }

    public ImportWrapper(String sourceFile){
        this.sourceFile = sourceFile;
        // set source type
        for (SourceType sourceType: SourceType.values()){
            if (sourceType.getExtention().equals(FilenameUtils.getExtension(this.sourceFile))){
                this.sourceType = sourceType;
                break;
            }
        }

        try{
            //read the file

            switch (this.sourceType){
                case RPT_FILE:{
                    loadRPTFile();
                }

                default:{
                    throw new Exception("Unknown file type");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean loadRPTFile(){
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        Map<String, Object> reportMap = null;
        Map<String, Object> tableMap = null;
        try {
            Integer tableIndex = 0;
            File file = new File(this.sourceFile);
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            // get table map
            for (String line: lines){
                if (line.contains("REPORT ID")){
                    if (reportMap != null){
                        reportList.add(reportMap);
                        tableMap = null;
                    }
                    // new report
                    reportMap = new HashMap<String, Object>();
                    reportMap.put("REPORT ID", line.split(": ")[1]);
                    continue;
                }
                if (line.contains("REPORT DATE")){
                    if (reportMap != null) reportList.add(reportMap);
                    reportMap = new HashMap<String, Object>();
                    reportMap.put("REPORT DATE", line.split(": ")[1]);
                    continue;
                }
                if (line.contains("BANK/INSTITUTION")){
                    if (reportMap != null) reportList.add(reportMap);
                    reportMap = new HashMap<String, Object>();
                    reportMap.put("BANK/INSTITUTION", line.split(": ")[1]);
                    continue;
                }
                if (line.contains("-")){
                    // create table
                    if (tableMap == null){
                        tableIndex++;
                        tableMap = new HashMap<String, Object>();
                        continue;
                    }else {
                        reportMap.put(String.valueOf(tableIndex), tableMap);
                        reportList.add(reportMap);
                        tableMap = null;
                        continue;
                    }
                }

                // read table header
                String[] header = line.split("\\t");
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String jsonString = objectMapper.writeValueAsString(header);
                    log.debug(jsonString);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for(int indexTable = 0; indexTable < header.length; indexTable++){
                    tableMap.put(String.valueOf(indexTable), header[indexTable]);
                }

                //save to database
                //only first index table

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
