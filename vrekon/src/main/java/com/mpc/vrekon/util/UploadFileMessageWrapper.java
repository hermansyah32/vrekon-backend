package com.mpc.vrekon.util;

public class UploadFileMessageWrapper{
    private final String message;
    private final boolean status;
    private final String files;

    public UploadFileMessageWrapper(String message, boolean status, String files) {
        this.message = message;
        this.status = status;
        this.files = files;
    }

    @Override
    public String toString() {
        return "UploadFileMessageWrapper{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", files='" + files + '\'' +
                '}';
    }
}