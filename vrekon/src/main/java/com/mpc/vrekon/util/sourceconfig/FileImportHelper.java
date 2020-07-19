package com.mpc.vrekon.util.sourceconfig;

import com.google.gson.Gson;
import com.mpc.vrekon.model.SourceConfig;
import com.mpc.vrekon.model.SourceTranslate;
import com.mpc.vrekon.util.sourceconfig.spreadsheet.SpreadSheetISheet;
import com.mpc.vrekon.util.sourceconfig.spreadsheet.SpreadSheetWrapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileImportHelper {

    private SourceType sourceType;
    private SourceConfig sourceConfig;
    private SourceTranslate sourceTranslate;
    private String[] sourceFiles;

    private Logger log = Logger.getLogger(getClass());


    public FileImportHelper(SourceConfig sourceConfig, SourceTranslate sourceTranslate){
        this.sourceConfig = sourceConfig;
        this.sourceTranslate = sourceTranslate;
        this.sourceFiles = sourceConfig.getFileNames().split(";");
        // set source type
        for (String sourceFile: sourceFiles){
            for (SourceType sourceType: SourceType.values()){
                if (sourceType.getExtention().equals(FilenameUtils.getExtension(sourceFile))){
                    this.sourceType = sourceType;
                    break;
                }
            }

            try{
                //read the file
                switch (this.sourceType){
                    case RPT_FILE:{
                        loadRPTFile(sourceFile);
                    }

                    case XLS_FILE:
                    case XLSX_FILE:{
                        loadXLSFile(sourceFile);
                    }

                    default:{
                        throw new Exception("Unknown file type");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean loadXLSFile(String sourceFile){
        boolean result = false;
        try {
            SpreadSheetWrapper workbook = new SpreadSheetWrapper(sourceFile);
            SpreadSheetISheet sheet = workbook.getSheet(0);
            //excel from puload
            for (int row = 0; row < sheet.getSheet().getLastRowNum(); row++) {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean loadRPTFile(String sourceFile){
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        Map<String, Object> reportMap = null;
        Map<String, Object> tableMap = null;
        try {
            Integer tableIndex = 0;
            // get table map
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile))){
                for (String line; (line = bufferedReader.readLine()) != null;){
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
                        reportMap.put("REPORT DATE", line.split(": ")[1]);
                        continue;
                    }
                    if (line.contains("BANK/INSTITUTION")){
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

                    for(int indexTable = 0; indexTable < header.length; indexTable++){
                        tableMap.put(String.valueOf(indexTable), header[indexTable]);
                    }

                    //save to database
                    log.debug(new Gson().toJson(reportList));
                    //only first index table
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
