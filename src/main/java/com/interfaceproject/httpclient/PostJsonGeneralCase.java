package com.interfaceproject.httpclient;

import com.interfaceproject.entry.User;
import com.interfaceproject.utils.HxHttpClient;
import com.interfaceproject.utils.HxHttpClientResponseData;
import com.interfaceproject.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PostJsonGeneralCase {


    /**
     * POST---有参测试(json类型入参),统一使用utils工具类方法
     *
     * @date 2020年4月28日
     */
    @Test
    public void doPostCase5() {

        String url = "http://127.0.0.1:8081/postjson";

        //url请求moco的have-paramaters-post-json.json模拟数据
        //这里通过工具类，定义map类型和java bean对象即可，后面通过hxHttpClient方法转换成json格式参数入参
        Map<String,Object> map = new HashMap<>();
        map.put("name","czy");
        map.put("age","29");
        HxHttpClient hxHttpClient = HxHttpClient.getInstance();
        hxHttpClient.config(url, "POST", map);
        HxHttpClientResponseData hxHttpClientResponseData = hxHttpClient.execute();
        //断言状态是否成功
        Assert.assertEquals(String.valueOf(hxHttpClientResponseData.getCode()),"200");
        System.out.println(JsonUtils.obj2json(hxHttpClientResponseData));
        System.out.println(hxHttpClientResponseData.getContent());
        //旧的方法
//        post(httpPost, requestEntity);
    }

    /**
     * hxHttpClient.config的Object params类型测试，map类型和javabean类型
     * */
    @Test
    public void testJson() {
        //map转json
        Map<String, Object> map = new HashMap<>();
        map.put("name", "czy");
        map.put("age", "29");
        System.out.println("map 转json:"+JsonUtils.obj2json(map));

        // javabean 转 json
        User user = new User();
        user.setId(1);
        user.setRealName("aa");
        user.setUserName("11");
        System.out.println("java bean转json:"+JsonUtils.obj2json(map));
    }
}
