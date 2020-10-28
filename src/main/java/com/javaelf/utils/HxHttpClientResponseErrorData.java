package com.javaelf.utils;


/**
 * @author lgl
 * @date 2019/12/13 10:00
 */
public class HxHttpClientResponseErrorData extends HxHttpClientResponseData {
    private String exceptionClazz;

    public HxHttpClientResponseErrorData() {
        super(false, DEFAULT_ERROR_CODE, "", "", "");
    }

    public HxHttpClientResponseErrorData(Integer code) {
        super(false, code, "", "", "");
    }

    public HxHttpClientResponseErrorData(Integer code, String msg) {
        super(false, code, msg, "", "");
    }

    public HxHttpClientResponseErrorData(Integer code, String msg, String content) {
        super(false, code, msg, content, "");
    }

    public HxHttpClientResponseErrorData(Integer code, String msg, String content, String extentData) {
        super(false, code, msg, content, extentData);
    }

    public String getExceptionClazz() {
        return this.exceptionClazz;
    }

    public void setExceptionClazz(final String exceptionClazz) {
        this.exceptionClazz = exceptionClazz;
    }

    @Override
    public String toString() {
        return "ErrorResponseData(exceptionClazz=" + this.getExceptionClazz() + ")";
    }
}
