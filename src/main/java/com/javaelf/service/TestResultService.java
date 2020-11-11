package com.javaelf.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestResultService {
    /**
     * 获取实际测试结果
     *
     * @return
     */
    List<String> getActualTestResult();

}
