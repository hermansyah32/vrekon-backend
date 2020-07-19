package com.mpc.vrekon.model;

import com.mpc.vrekon.util.ResponseCode;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ResponseWrapper<T> {
    private String code;
    private String message;
    private T dataDetail;
    private String timeStamp;

    private Logger log = Logger.getLogger(getClass());

    public ResponseWrapper() {
    }

    public ResponseWrapper(ResponseCode responseCode, T dataDetail) {
        this.code = responseCode.getValue();
        this.message = responseCode.getMessage();
        this.dataDetail = dataDetail;
        this.timeStamp = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").format(new Date());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        log.debug("Response Wrapper: code => " + this.code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        log.debug("Response Wrapper: message => " + this.message);
    }

    public T getDataDetail() {
        return dataDetail;
    }

    public void setDataDetail(T dataDetail) {
        this.dataDetail = dataDetail;
        log.debug("Response Wrapper: message => " + this.dataDetail.toString());
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        log.debug("Response Wrapper: timestamp => " + this.dataDetail);
    }

    public void systemError(String message){
        this.setCode(ResponseCode.INTERNAL_ERROR.getValue());
        this.setMessage(message);
        this.timeStamp = new SimpleDateFormat("yyyy-M-dd hh:mm:ss").format(new Date());
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", dataDetail=" + dataDetail +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
