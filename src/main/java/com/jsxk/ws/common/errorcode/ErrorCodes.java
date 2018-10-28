package com.jsxk.ws.common.errorcode;

import com.jsxk.ws.common.callback.ErrorCallback;
import com.jsxk.ws.common.errorcode.ParameterError.ParameterErrorCause;

public class ErrorCodes {

    private static final IErrorCode SUCCESS_CODE = new IErrorCode() {
        public int getCode() {
            return 0;
        }

        public String getDomain() {
            return "";
        }
    };
    private static ErrorCallback systemErrorCallback = null;

    public ErrorCodes() {
    }

    public static IErrorCode success() {
        return SUCCESS_CODE;
    }

    public static IErrorCode systemError() {
        IErrorCode systemError = systemError(Thread.currentThread());
        if (null != systemErrorCallback) {
            systemErrorCallback.handle(systemError);
        }

        return systemError;
    }

    public static IErrorCode systemError(Thread thread) {
        return new SystemError(5, thread);
    }

    public static IErrorCode parameterError(ParameterErrorCause cause, String parameter) {
        return new ParameterError(2, cause, parameter, Thread.currentThread());
    }

    public static IErrorCode businessError(IErrorCode errorCode) {
        return new BusinessError(4, errorCode);
    }

    public static void setSystemErrorCallback(ErrorCallback systemErrorCallback) {
        systemErrorCallback = systemErrorCallback;
    }
}
