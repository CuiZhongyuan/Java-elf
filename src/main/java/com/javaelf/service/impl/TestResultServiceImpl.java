package com.javaelf.service.impl;

import com.javaelf.dao.TestCaseDao;
import com.javaelf.entity.TestCase;
import com.javaelf.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestCaseDao testCaseDao;

    @Override
    public List<String> getActualTestResult() {
        List<TestCase> testCases = testCaseDao.findAll();
        List<String> getActual = new ArrayList<>();
        for (TestCase testCase : testCases){
            getActual.add(testCase.getActual());
        }
        return getActual;
    }
}
