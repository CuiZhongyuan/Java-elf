package com.interfaceproject.testngservice.transportproject;

import com.interfaceproject.utils.JsonUtils;
import com.interfaceproject.utils.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DriverCase {

    private String ip = "http://192.168.1.224:8092";


    /**
     * 车队查询接口,get请求
     * */
    @Test
    public void getDriverGroupQuery(){

        ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/driver-group/list?companyId=1260463067108610048", Map.class);
        Map<String,Object> respMap = (Map<String,Object>)responseEntity.getBody();
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        log.info(respJson);
        System.out.println(respJson);
    }


    /**
     * 车队负责人查询接口,get请求
     * */
    @Test
    public void getDriverLeaderQuery(){

        ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/driver-group/driver-group-leader?companyId=1260463067108610048&id=720580608731906048", Map.class);
        Map<String,Object> respMap = (Map<String,Object>)responseEntity.getBody();
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        log.info(respJson);
        System.out.println(respJson);
    }

    /**
     * 车队--新增接口。post方式
     * */
    @Test
    public void addICCardCase(){
        Map<String,Object> map = new HashMap<>();
        map.put("driverGroupName","test车队");
        map.put("remark","test");
        map.put("driverGroupHead","66");
        map.put("companyId","1260463067108610048");

        ResponseEntity responseEntity = RestTemplateUtils.post(ip+"/driver-group",map,Map.class);
        Map<String,Object> respMap = (Map<String, Object>) responseEntity.getBody();
//        Assert.assertEquals(respMap.get("msg"),"success");
        String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
        log.info(respJson);
        System.out.println(respJson);
    }


}
