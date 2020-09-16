package com.interfaceproject.testngservice.transportproject;

import cn.hutool.http.Header;
import com.interfaceproject.utils.BaseTestngInit;
import com.interfaceproject.utils.JsonUtils;
import com.interfaceproject.utils.RestTemplateUtils;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ICCardCase {

    private String ip = "http://192.168.1.239:6081";
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMxMjMiLCJhdWQiOiIwOThmNmJjZDQ2MjFkMzczY2FkZTRlODMyNjI3YjRmNiIsIm5iZiI6MTU5MTc1MjAyOCwiaXNzIjoicmVzdGFwaXVzZXIiLCJleHAiOjE1OTE3NTkyMjgsInVzZXJJZCI6IjEyNjc2NjUwMjM5Mjk1NTI4OTYiLCJpYXQiOjE1OTE3NTIwMjgsInVzZXJDb2RlIjoiMTIzMTIzIiwianRpIjoiYjkxMTc3MjktMjVjNi00ZDE2LTk2ZDYtODhlYjQ1MDMxM2U4In0.R5bIJv_dbV0fAjetQQ-9mK22EKQPBU78qo2biuJxYsQ";

//    @Test
//    public void testCase(){
//        pageQueryCase();
//        System.out.println("================================");
//        addICCardCase();
//        System.out.println("================================");
//        putICCardCase();
//        System.out.println("================================");
//        getCompanyIdCase();
//        System.out.println("================================");
//        deleteCompanyIdCase();
//        System.out.println("================================");
//        getDriverIdCase();
//    }

    /**
     * 根据企业id和唯一id查询唯一数据,get请求
     * */
    @Test
    public void getDriverIdCase(){
        ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/ic-card/driver-list?companyId=222&icCardId=720284140217303040",Map.class);
        Map<String,Object> respMap = (Map<String, Object>) responseEntity.getBody();
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        System.out.println(respJson);

    }

    /**
     * 根据企业id和唯一id查询唯一数据,delete请求
     * */
    @Test
    public void deleteCompanyIdCase(){
        ResponseEntity responseEntity = RestTemplateUtils.delete(ip+"/ic-card/222/720284140217303040",Map.class);
        Map<String,Object> respMap = (Map<String,Object>)responseEntity.getBody();
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        System.out.println(respJson);
    }

    /**
     * 根据企业id和唯一id查询唯一数据,get请求
     * */
    @Test
    public void getCompanyIdCase(){
        ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/ic-card/222/720284140217303040",Map.class);
        Map<String,Object> respMap = (Map<String,Object>)responseEntity.getBody();
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        System.out.println(respJson);
    }

    /**
     * IC卡管理--更新接口。put方式[缺陷只能首次更新]
     * */
    @Test
    public void putICCardCase(){
        Map<String,Object> map = new HashMap<>();
        map.put("id","720333544089452544");
        map.put("icCode","55");
        map.put("icName","66");
        map.put("driverId","66");
        map.put("remark","77788");
        map.put("companyId","6666");
        ResponseEntity responseEntity = RestTemplateUtils.put(ip+"/ic-card",map,Map.class);
        Map<String,Object> respMap = (Map<String, Object>) responseEntity.getBody();
        String resJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        System.out.println(resJson);
    }

    /**
     * IC卡管理--新增接口。post方式
     * */
    @Test
    public void addICCardCase(){
        Map<String,Object> map = new HashMap<>();
        map.put("icCode","55");
        map.put("icName","66");
        map.put("driverId","66");
        map.put("remark","888");
        map.put("companyId","6666");

        ResponseEntity responseEntity = RestTemplateUtils.post(ip+"/ic-card",map,Map.class);
        Map<String,Object> respMap = (Map<String, Object>) responseEntity.getBody();
//        Assert.assertEquals(respMap.get("msg"),"success");
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        System.out.println(respJson);
    }

    /**
     * IC卡管理--分页查询接口。get方式
     * */
    @Test
    public void pageQueryCase(){
//        Map<String,String> map1 = new HashMap<>();
//        map1.put("icCode","");
//        map1.put("icName","");
//        map1.put("companyId","1260463067108610048");

        ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/ic-card/page?companyId=222",Map.class);
        Map<String,Object> respMap = (Map<String, Object>) responseEntity.getBody();
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        System.out.println(respJson);
    }

//    /**
//     * IC卡管理--分页查询。get方式
//     * */
//    public void pageQueryCase(String token){
//        Map<String,String> map = new HashMap<>();
//        map.put(Header.AUTHORIZATION.getValue(),token);
//        Map<String,String> map1 = new HashMap<>();
////        map1.put("icCode","");
////        map1.put("icName","");
//        map1.put("companyId","1260463067108610048");
//        ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/ic-card/page",map,Map.class,map1);
//        Map<String,Object> respMap = (Map<String, Object>) responseEntity.getBody();
//        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
//        System.out.println(respJson);
//    }

}
