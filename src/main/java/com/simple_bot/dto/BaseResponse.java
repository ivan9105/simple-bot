package com.simple_bot.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ivan-melnikov
 */
public class BaseResponse {
    private String content;
    private Integer code;
    private Map<String, String> headers;
    private List<String> cookies = new ArrayList<>();

    public BaseResponse(String content) {
        this.content = content;
    }

    public BaseResponse(String content, Integer code) {
        this.content = content;
        this.code = code;
    }

    public BaseResponse(String content, Integer code, Map<String, String> headers) {
        this.content = content;
        this.code = code;
        this.headers = headers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> cookies) {
        this.headers = cookies;
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }
}
