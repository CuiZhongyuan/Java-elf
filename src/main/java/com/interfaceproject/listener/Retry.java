package com.interfaceproject.listener;

import java.util.List;

import org.testng.IRetryAnalyzer;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * 测试失败重新调用该test
 * @author jw
 *
 */
public class Retry implements IRetryAnalyzer {
    private int retryCount = 1;
    private static int maxRetryCount = 2;//重试次数

    public boolean retry(ITestResult result) {
        System.out.println("执行用例："+result.getName()+"，第"+retryCount+"次失败");
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
