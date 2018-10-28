package com.jsxk.ws.common.errorcode;

import java.util.Arrays;
import java.util.List;

public class SystemError implements IErrorCode {

    private static final List<String> IGNORE_LIST = Arrays.asList(SystemError.class.getCanonicalName(), ErrorCodes.class.getCanonicalName());
    private int category;
    private int threadId;
    private int lineNumb;
    private String domain;

    public SystemError(int category, Thread thread) {
        this.category = category % 10;
        this.threadId = Long.valueOf(thread.getId()).intValue() % 10000;
        StackTraceElement[] stackTraces = thread.getStackTrace();
        int line = 0;

        for(int i = 1; i < stackTraces.length; ++i) {
            StackTraceElement stackTraceElement = stackTraces[i];
            if (!IGNORE_LIST.contains(stackTraceElement.getClassName())) {
                line = stackTraceElement.getLineNumber();
                this.domain = stackTraceElement.getClassName();
                break;
            }
        }

        this.lineNumb = line % 10000;
    }

    public int getCode() {
        return this.category * 100000000 + this.threadId * 10000 + this.lineNumb;
    }

    public String getDomain() {
        return this.domain;
    }
}
