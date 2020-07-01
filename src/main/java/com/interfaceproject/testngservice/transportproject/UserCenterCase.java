package com.interfaceproject.testngservice.transportproject;

import cn.hutool.http.Header;
import com.interfaceproject.utils.JsonUtils;
import com.interfaceproject.utils.RestTemplateUtils;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;



public class UserCenterCase {

        private String ip = "http://192.168.1.239:6081";
        private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxODI4MjcyMzg0NSIsImF1ZCI6IjA5OGY2YmNkNDYyMWQzNzNjYWRlNGU4MzI2MjdiNGY2IiwibmJmIjoxNTkxNzc4ODg1LCJpc3MiOiJyZXN0YXBpdXNlciIsImV4cCI6MTU5MTc4NjA4NSwidXNlcklkIjoiMTI3MDU5OTg1NjM2NDMyNjkxMiIsImlhdCI6MTU5MTc3ODg4NSwidXNlckNvZGUiOiIxODI4MjcyMzg0NSIsImp0aSI6ImI3MDY2OGQwLTI2ZWEtNGJjNS1iZjNjLWI0MTI0NmNkYTJlOCJ9.h_7HGaYHLWGunUC4OnZZL_NTeG1SCIs-zMrJX7DtEkY";


        @Test
        public void testCase() throws Exception {
           userPermissionsCase(token);
           System.out.println("========================");
           Thread.sleep(1000);
           String id =  userMsgCase(token);
           System.out.println("========================");
           Thread.sleep(1000);
           userAlterCase(id);

        }

        /**
         * 修改接口，修改用户信息，put请求
         * */
        public void userAlterCase(String id){
            Map<String,Object> map = new HashMap<>();
            map.put("id",id);
            map.put("userName","test-zcdd");
            map.put("phone","15238305317");
            ResponseEntity responseEntity = RestTemplateUtils.put(ip+"/user",map,Object.class);
            Map<String,Object> respMap = (Map<String, Object>) responseEntity.getBody();
            String respJson = JsonUtils.jsonFormatter(JsonUtils.mapToJson(respMap));
            System.out.println(respJson);
        }

        /**
         * 获取用户权限列表，get请求
         * */
        public void  userPermissionsCase(String token){
            Map<String,String> map = new HashMap<>();
            map.put(Header.AUTHORIZATION.getValue(),token);
            ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/user/permissions",map,Map.class);
            Map<String,Object> repMap = (Map<String, Object>) responseEntity.getBody();
            String repJson = JsonUtils.jsonFormatter(JsonUtils.obj2json(repMap));
            System.out.println(repJson);

        }

        /**
         * 获取用户信息接口,get请求
         * */
        public String userMsgCase(String token){

            Map<String,String> map = new HashMap<>();
            map.put(Header.AUTHORIZATION.getValue(),token);
            ResponseEntity responseEntity = RestTemplateUtils.get(ip+"/user", map, Map.class);
            Map<String,Object> repMap =(Map<String, Object>)responseEntity.getBody();
            String repJson = JsonUtils.jsonFormatter(JsonUtils.obj2json(repMap));
            System.out.println(repJson);
            Map<String,Object> dataMap = (Map<String, Object>) repMap.get("data");
            String id = (String) dataMap.get("id");
            System.out.println(id);
            return id;

        }

        /**
         * 登录接口获取token授权，post请求
         * */
        public void loginCase()  {

            Map<String,Object> map = new HashMap<>();
            map.put("account","18282723845");
            map.put("password","123123");

            ResponseEntity responseEntity = RestTemplateUtils.post(ip+"/login",map,Map.class);
            Map<String,Object> repMap =(Map<String, Object>)responseEntity.getBody();
            Map<String,Object> dataMap = (Map<String, Object>) repMap.get("data");
            String token = (String) dataMap.get("token");
            System.out.println(token);
        }
}
