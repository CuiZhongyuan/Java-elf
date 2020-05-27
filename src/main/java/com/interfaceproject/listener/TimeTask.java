package com.interfaceproject.listener;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;


//通过配置文件执行定时任务运行testng.xml文件
@Service
public class TimeTask {

    @Scheduled(cron = "${autoRefund.job}")
    public void runTest() {
        System.out.println("----------开始执行测试用例----------");
        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add("src\\main\\resources\\testNG.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
