package com.jsxk.ws.common.errorcode;

public class BusinessError implements IErrorCode {


    public int category;
    public int errorCode;
    public String domain;

    public BusinessError(int category, IErrorCode errorCode) {
        this.category = category % 10;
        this.errorCode = errorCode.getCode() % 100000000;
        this.domain = errorCode.getDomain();
    }

    public int getCode() {
        return this.category * 100000000 + this.errorCode;
    }

    public String getDomain() {
        return this.domain;
    }
}
