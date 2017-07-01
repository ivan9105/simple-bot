package com.simple_bot.dto;

/**
 * @author Иван
 * @version $Id$
 */
public class BaseResponse {
    private String content;
    private Integer code;

    public BaseResponse(String content, Integer code) {
        this.content = content;
        this.code = code;
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
}
