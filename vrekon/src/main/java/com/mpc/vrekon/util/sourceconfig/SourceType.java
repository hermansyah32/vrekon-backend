package com.mpc.vrekon.util.sourceconfig;

import java.util.HashMap;
import java.util.Map;

public enum SourceType{
    RPT_FILE("rpt"),
    XLS_FILE("xlx");

    private static final Map<String, SourceType> enumList = new HashMap<String, SourceType>();

    private String extention;

    public String getExtention(){
        return this.extention;
    }

    private SourceType(String extention){
        this.extention = extention;
    }
}