package com.carrentalcompany.response;

import com.alibaba.fastjson.JSONObject;

public class Response <T>{
    private String respCode;
    private String respMsg;
    private T data;

    Response(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    Response(String respCode, String respMsg, T data) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        this.data = data;
    }

    public static <T> String buildSuccessResponseString() {
        return JSONObject.toJSONString(new Response("1000", "success"));
    }

    public static <T> String buildSuccessResponseString(T data) {
        return JSONObject.toJSONString(new Response("1000", "success", data));
    }

    public static String buildFailureResponseString(String respCode, String respMsg) {
        return JSONObject.toJSONString(new Response(respCode, respMsg));
    }

    public String getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public T getData() {
        return data;
    }
}
