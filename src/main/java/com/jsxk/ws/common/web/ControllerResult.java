package com.jsxk.ws.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsxk.ws.common.errorcode.IErrorCode;

import java.io.IOException;

public class ControllerResult {

    private static ObjectMapper mapper = new ObjectMapper();
    private int code;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String domain;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode result;

    private ControllerResult() {
    }

    public ControllerResult(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.domain = errorCode.getDomain();
    }

    public ControllerResult(IErrorCode errorCode, Object obj) {
        this.code = errorCode.getCode();
        this.domain = errorCode.getDomain();
        if (null != obj) {
            if (JsonNode.class.isAssignableFrom(obj.getClass())) {
                this.result = (JsonNode)obj;
            } else {
                this.result = (JsonNode)mapper.convertValue(obj, JsonNode.class);
            }
        }

    }

    public int getCode() {
        return this.code;
    }

    public JsonNode getResult() {
        return this.result;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setResult(Object obj) {
        if (JsonNode.class.isAssignableFrom(obj.getClass())) {
            this.result = (JsonNode)obj;
        } else {
            this.result = (JsonNode)mapper.convertValue(obj, JsonNode.class);
        }

    }

    public static String toJson(ControllerResult result) {
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException var2) {
            return null;
        }
    }

    public static ControllerResult valueOf(String jsonText) {
        try {
            JsonNode jsonNode = (JsonNode)mapper.readValue(jsonText, JsonNode.class);
            ControllerResult controllerResult = new ControllerResult();
            controllerResult.code = jsonNode.get("code").asInt();
            controllerResult.domain = null != jsonNode.get("domain") ? jsonNode.get("domain").asText() : "";
            controllerResult.result = jsonNode.get("result");
            return controllerResult;
        } catch (IOException var3) {
            return null;
        }
    }
}
