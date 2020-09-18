package com.interfaceproject.developer.hibernatecase.controller;


import com.interfaceproject.utils.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test-api")
public class HuberTestController {

    @GetMapping(value = "/get")
    public String getTest(){
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        Map<String,String> map = new HashMap<>();
        map.put("code","200");
        map.put("masge","success");
        map.put("flag","1");
        Map<String,Object> listMap = new HashMap<>();
        listMap.put("header",headerMap);
        listMap.put("body",map);
        String data = JsonUtils.mapToJson(listMap);
        return data;
    }
}
