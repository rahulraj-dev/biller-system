package com.setu.biller.error;

import org.apache.commons.lang3.StringUtils;

public class ErrorObject {

    private String code = StringUtils.EMPTY;
    private String title = StringUtils.EMPTY;
    private String traceID = StringUtils.EMPTY;
    private String description = StringUtils.EMPTY;
    private String param = StringUtils.EMPTY;
    private String docURL = StringUtils.EMPTY;

    public ErrorObject() {
    }

    public ErrorObject(String code, String title, String traceID, String description, String param, String docURL) {
        this.code = code;
        this.title = title;
        this.traceID = traceID;
        this.description = description;
        this.param = param;
        this.docURL = docURL;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String traceID) {
        this.traceID = traceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getDocURL() {
        return docURL;
    }

    public void setDocURL(String docURL) {
        this.docURL = docURL;
    }
}
