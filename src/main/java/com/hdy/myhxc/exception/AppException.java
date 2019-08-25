package com.hdy.myhxc.exception;

public class AppException extends RuntimeException{

    private String errorMsg;

    public AppException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
