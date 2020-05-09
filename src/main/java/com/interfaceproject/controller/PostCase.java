package com.interfaceproject.controller;

import com.google.inject.internal.ErrorsException;
import com.interfaceproject.entry.User;
import com.interfaceproject.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *PostCase类供测试类方法调用
 *@author czy
 * @date 2020年05月09日
 */
//@RequestMapping("test")
//@Api(value = "test接口",tags = {"接口测试用例"})
@RestController
public class PostCase {

//   其中@PostMapping注解=@RequestMapping(value = "/postjson", method = RequestMethod.POST)
//    @RequestMapping(value = "/postjson", method = RequestMethod.POST)
//    @ApiOperation(value = "postCase1测试",notes = "测试用例1",httpMethod = "post")
    @PostMapping("/postjson")
    public Map<String, Object> jsonpost(@RequestBody Map<String, Object> reqMap) throws Exception {
        String name = (String) reqMap.get("name");
        String age = (String) reqMap.get("age");
        if ((null == name || "".equals(name)) || (null == age || "".equals(age))) {
            throw new Exception("参数异常");
        }

        //这里用两种方式返回。1、Javabean对象 2、Map对象
        User user = new User(12,"121","121","121");
        //这里需要把javabean对象转成字符串然后在转成map对象
        return JsonUtils.json2map(JsonUtils.obj2json(user));

        //Map对象返回响应
//        Map<String, Object> resMap = new HashMap<>();
//        resMap.put("error", "1000");
//        resMap.put("mages", "success");
//        resMap.put("data","20200509");
//        return resMap;
    }
}
