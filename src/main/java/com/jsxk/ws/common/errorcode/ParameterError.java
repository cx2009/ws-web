package com.jsxk.ws.common.errorcode;

import java.util.Arrays;
import java.util.List;

public class ParameterError implements IErrorCode {

    private static final List<String> IGNORE_LIST = Arrays.asList(ParameterError.class.getCanonicalName(), ErrorCodes.class.getCanonicalName());
    private int category;
    private int cause;
    private int parameter;
    private String domain;

    public int getCode() {
        return this.category * 100000000 + this.cause * 1000000 + this.parameter;
    }

    public String getDomain() {
        return this.domain;
    }

    public ParameterError(int category, ParameterError.ParameterErrorCause cause, String parameter, Thread thread) {
        this.category = category % 10;
        this.cause = cause.code % 100;
        String param = String.valueOf(parameter);
        parameter = parameter + String.copyValueOf(new char[]{'\u001e', '\u001e', '\u001e'});
        this.parameter = this.encode(parameter.charAt(0)) * 10000 + this.encode(parameter.charAt(1)) * 100 + this.encode(parameter.charAt(2));
        StackTraceElement[] stackTraces = thread.getStackTrace();

        for(int i = 1; i < stackTraces.length; ++i) {
            StackTraceElement stackTraceElement = stackTraces[i];
            if (!IGNORE_LIST.contains(stackTraceElement.getClassName())) {
                this.domain = stackTraceElement.getClassName() + "." + param;
                break;
            }
        }

    }

    public int encode(char parameterChar) {
        return parameterChar - 30;
    }

    public static enum ParameterErrorCause {
        Missing(1),
        FormatError(2),
        Illegal(3);

        private int code;

        private ParameterErrorCause(int code) {
            this.code = code;
        }
    }

}
