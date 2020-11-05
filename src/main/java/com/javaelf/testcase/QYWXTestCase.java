package com.javaelf.testcase;

import com.javaelf.base.TestBase;
import com.javaelf.service.easypoihandle.EasypoiWxCaseService;
import com.javaelf.service.jpahandle.JpaWXCaseService;
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
    @Autowired
    EasypoiWxCaseService easypoiWxCaseService;

    @Severity( SeverityLevel.BLOCKER)
    @Description("easypi示例--企业微信获取token覆盖测试")
    @Test
    public void gettokenJpaCase() {
        easypoiWxCaseService.gettoken();
    }
    @Severity( SeverityLevel.BLOCKER)
    @Description("jpa示例--企业微信获取token覆盖测试")
    @Test
    public void gettokenEasypoiCase()  {
        jpaWXCaseService.getToken();
    }
}
