package com.mpc.vrekon.model;

import java.util.List;

public class ResponseWrapper<T> {
    private Integer code;
    private String message;
    private T dataDetail;
    private String timestap;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDataDetail() {
        return dataDetail;
    }

    public void setDataDetail(T dataDetail) {
        this.dataDetail = dataDetail;
    }

    public String getTimestap() {
        return timestap;
    }

    public void setTimestap(String timestap) {
        this.timestap = timestap;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", dataDetail=" + dataDetail +
                ", timestap='" + timestap + '\'' +
                '}';
    }
}
