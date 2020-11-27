package com.javaelf.controller;

import com.javaelf.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 测试后实际结果查询
 *
 **/
@RestController
@RequestMapping("/api")
public class TestResultsController {

    @Autowired
    TestResultService testResultService;

    /**
     * 获取实际测试的结果--json格式
     *
     **/
    @PostMapping(value = "/test/actual")
    public List<String> getACtual(){
        return testResultService.getActualTestResult();
    }
    /**
     * 区分必填项和非必填项
     * 通过组合算法动态生成参数某一必填项未传的异常测试用例
     *
     **/
    @PostMapping(value = "/test/mandatoryParams")
    public String MandatoryTest(@RequestBody String reqJson){
        return testResultService.mandatory(reqJson);
    }
    /**
     * 不区分参数的必填项和非必须
     * 通过组合算法动态生成参数某一填项未传的测试用例组
     *
     **/
    @PostMapping(value = "/test/hybridParams")
    public String Mandatory(@RequestBody String reqJson){
        return testResultService.hybrid(reqJson);
    }
}
