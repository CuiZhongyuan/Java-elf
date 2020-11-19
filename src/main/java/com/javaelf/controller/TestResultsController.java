package com.javaelf.controller;

import com.javaelf.service.TestResultService;
import com.javaelf.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PostMapping(value = "/test/mandatory")
    public ArrayList<String> MandatoryTestCase(@RequestBody String reqJson){
        return testResultService.mandatory(reqJson);
    }
}
