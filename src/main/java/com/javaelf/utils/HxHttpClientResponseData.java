package com.javaelf.utils;

import lombok.Data;

/**
 * @author lgl
 * @date 2019/12/13 10:00
 */
@Data
public class HxHttpClientResponseData {
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final Integer DEFAULT_ERROR_CODE = 500;
    private Boolean success;
    private Integer code;
    private String msg;
    private String content;
    private String extendData;

    public HxHttpClientResponseData() {

    }

    public HxHttpClientResponseData(Boolean success, Integer code, String msg, String content, String extendData) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.content = content;
        this.extendData = extendData;
    }

    public static HxHttpClientResponseSuccessData success() {
        return new HxHttpClientResponseSuccessData();
    }

    public static HxHttpClientResponseErrorData error(Integer code, String msg, String content, String extendData) {
        return new HxHttpClientResponseErrorData(code, msg, content, extendData);
    }
}
