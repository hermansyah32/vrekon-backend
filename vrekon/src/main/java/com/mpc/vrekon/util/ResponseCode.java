package com.mpc.vrekon.util;

public enum ResponseCode {
    OK("00", "Success"),
    NO_DATA("01", ""),
    INTERNAL_ERROR("02", ""),
    BAD_REQUEST("03", "");

    private String responseCode;
    private String message;

    public String getValue(){
        return this.responseCode;
    }

    public String getMessage() {
        return message;
    }

    private ResponseCode (String code, String message){
        this.responseCode = code;
        this.message = message;
    }
}
