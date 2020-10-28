package com.javaelf.utils;


/**
 * @author lgl
 * @date 2019/12/13 10:00
 */
public class HxHttpClientResponseSuccessData extends HxHttpClientResponseData {
    public HxHttpClientResponseSuccessData() {
        super(true, DEFAULT_SUCCESS_CODE, "请求数据成功", "", "");
    }

    public HxHttpClientResponseSuccessData(String msg) {
        super(true, DEFAULT_SUCCESS_CODE, msg, "", "");
    }

    public HxHttpClientResponseSuccessData(String msg, String content) {
        super(true, DEFAULT_SUCCESS_CODE, msg, content, "");
    }

    public HxHttpClientResponseSuccessData(String msg, String content, String extendData) {
        super(true, DEFAULT_SUCCESS_CODE, msg, content, extendData);
    }
}
