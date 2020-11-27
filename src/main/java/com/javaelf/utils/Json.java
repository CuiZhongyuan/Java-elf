package com.javaelf.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Json {

    private static ObjectMapper defaultObjectMapper = new ObjectMapper();


    public static String toJacksonStr(Object obj) {
        String content = "";
        try {
            content = defaultObjectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return content;
    }

    public static  <T> T jsonToObject(Class<T> type,String jsonStr) {
        try {
           return defaultObjectMapper.readValue(jsonStr,type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把一个类型的数据转换为另一个类型的数据
     *
     * @param data 输入数据
     * @param type 转换的目标类型, 可传入com.fasterxml.jackson.databind.JavaType类型, 用于处理泛型
     */
    public static <T> T conversion(Object data, Class<T> type) {
        try {
            if (data instanceof InputStream) {
                Map<?, ?> map = defaultObjectMapper.readValue((InputStream) data, Map.class);
                data = defaultObjectMapper.writeValueAsString(map);
            }
            String stringValue = null;
            if (data instanceof String) {
                stringValue = (String) data;
            } else {
                stringValue = defaultObjectMapper.writeValueAsString(data);
            }
            T newData = defaultObjectMapper.readValue(stringValue, type);
            return newData;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
