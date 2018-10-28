package com.jsxk.ws.common;

import com.google.common.collect.Maps;
import com.jsxk.ws.common.errorcode.IErrorCode;


import java.util.concurrent.ConcurrentMap;

public enum  WebErrorCode implements IErrorCode {

    SUCCESS(0, "success"),
    SYSTEM_ERROR(10001, "发生异常啦"),
    PARAM_ERROR(10002, "参数异常"),

    ACCESS_DENIAL(20001, "没有访问权限"),
    COOKIE_ERROR(20002, "COOKIE解析错误"),
    NO_LOGIN(20003, "用户未登录")



            ;





    private int code;
    private String domain;

    private static final ConcurrentMap<Integer, WebErrorCode> maps = Maps.newConcurrentMap();

    static {
        for (WebErrorCode webErrorCode : WebErrorCode.values()) {
            maps.put(webErrorCode.getCode(), webErrorCode);
        }
    }

    WebErrorCode(int code) {
        this.code = code;
    }

    WebErrorCode(int code, String domain) {
        this.code = code;
        this.domain = domain;
    }

    public WebErrorCode getByCode() {
        return maps.get(code);
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDomain() {
        return domain;
    }


}
