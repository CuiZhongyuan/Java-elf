package com.interfaceproject.developer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// post请求上一个参数传入下一个请求示例
@RestController
@Api(value = "api",tags = "接口之间的相互调用--测试用例")
public class ParaPost {

    @Autowired
    private PostCase postCase;

    @ApiOperation("case3--case2接口调用case1的接口")
    @PostMapping("/parapost")
    public Map<String, Object>  postCase3(HttpServletRequest request, @RequestBody Map<String, Object> reqMap) throws Exception {
        try {
            Map<String, Object> resMap = postCase.postCase1(reqMap);
            String data = (String) resMap.get("data");
            Map<String, Object> headerReqMap2 = new HashMap<>();
            headerReqMap2.put("data", data);
            headerReqMap2.put("age", reqMap.get("age"));
            return postCase.postCase2(request, headerReqMap2);
        } catch (Exception e) {
            throw  new Exception("请求失败");
        }
    }

}
