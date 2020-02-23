package com.spring5.nestedjson.models;

import lombok.Setter;

import java.util.Map;

public class DashboardResponse {

    private Integer statusCode;
    private ErrorMsgs errorMsgs;
    private ResponseData responseData;

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setErrorMsgs(ErrorMsgs errorMsgs) {
        this.errorMsgs = errorMsgs;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    static class ErrorMsgs {

        private Map<String, Object> respError;

        public void setRespError(Map<String, Object> respError) {
            this.respError = respError;
        }
    }

    static class ResponseData {

        private Map<String, Object> responseData;

        public void setResponseData(Map<String, Object> responseData) {
            this.responseData = responseData;
        }
    }
}
