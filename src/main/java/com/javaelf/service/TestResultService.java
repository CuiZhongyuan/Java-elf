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
    /**
     * 动态生成必填项未传异常测试用例集
     *
     * @return
     */
    String mandatory(String reqJson);
    /**
     * 动态生成混合参数的测试用例集
     *
     * @return
     */
    String hybrid(String reqJson);
}
