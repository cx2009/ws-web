package com.jsxk.ws.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import com.jsxk.ws.common.errorcode.IErrorCode;
import com.jsxk.ws.common.web.ControllerResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ControllerUtils {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String renderControllerResult(final IErrorCode errorCode, final ObjectNode result) {

        ControllerResult controllerResult = new ControllerResult(errorCode, result);
        return ControllerResult.toJson(controllerResult);
    }

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; ++i) {
                if (StringUtils.equals(cookies[i].getName(), cookieName)) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    public static Map<String, String> getParams(HttpServletRequest request, final Set<String> needToRemoved) {
        Map<String, String> params = Maps.newHashMap();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            if (needToRemoved.contains(key)) continue;
            String value = StringUtils.defaultString(request.getParameter(key));
            params.put(key, value);
        }
        return params;
    }

    public static Map<String, String> getParams(HttpServletRequest request) {
        return getParams(request, new HashSet<String>());
    }

    public static void addParams(final ObjectNode result, final Map<String, String> params) {
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                result.put(param.getKey(), param.getValue());
            }
        }
    }

    public static JsonNode extractControllerResult(String msg) {

        try {
            JsonNode msgNode = OBJECT_MAPPER.readTree(msg);
            JsonNode codeNode = msgNode.get("code");
            if (codeNode != null && codeNode.asLong() == 0) {
                return msgNode.get("result");
            }
        } catch (IOException e) {
            log.error("parse return message failed, message = {}, e: ", msg, e);
        }
        return null;
    }
}
