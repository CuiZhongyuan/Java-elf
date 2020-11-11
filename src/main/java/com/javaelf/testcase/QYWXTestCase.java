package com.javaelf.testcase;

import com.javaelf.base.TestBase;
import com.javaelf.service.impl.JpaWXCaseService;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.javaelf.listener.AssertListener.class)
public class QYWXTestCase extends TestBase {
    @Autowired
    JpaWXCaseService jpaWXCaseService;
    @Severity( SeverityLevel.BLOCKER)
    @Description("jpa示例--企业微信获取token覆盖测试")
    @Test
    public void gettokenJpaCase()  {
        jpaWXCaseService.getToken();
    }
}
