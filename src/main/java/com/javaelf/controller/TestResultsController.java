package com.javaelf.controller;

import com.javaelf.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 测试后实际结果查询
 *
 **/
@RestController
@RequestMapping("/test")
public class TestResultsController {

    @Autowired
    TestResultService testResultService;

    @GetMapping(value = "/actual")
    public List<String> getACtual(){
        return testResultService.getActualTestResult();
    }
}
