package com.interfaceproject.listener;

import com.interfaceproject.utils.SendEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;


//通过配置文件执行定时任务运行testng.xml文件
@Service
public class TimeTask {
    /**
     * 邮箱服务器
     */
    @Value("${spring.mail.host}")
    private String host;
    /**
     * 发邮件的人的账户
     */
    @Value("${spring.mail.username}")
    private String userName;
    /**
     * 发邮件的人的密码
     */
    @Value("${spring.mail.password}")
    private String password;
    /**
     * 发给谁
     */
    @Value("${test.mail.send}")
    private String toSend;
    /**
     * 抄送人
     */
    @Value("${test.mail.cc}")
    private String cc;

    @Scheduled(cron = "${autoRefund.job}")
    public void runTest() throws Exception {
        System.out.println("----------开始执行测试用例----------");
        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add("src\\main\\resources\\testNG.xml");
        testNG.setTestSuites(suites);
        testNG.run();
        //打包成zip包位置
        String targetPath = "D:\\";
        //项目静态文件路径
        SendEmail.compressedFile("F:\\00_3IdeaProjects\\HttpClientUtils\\HttpClient\\src\\main\\resources\\static", targetPath);
        List<String> fileList = new ArrayList<>();
        fileList.add("D:\\static.zip");
        SendEmail.entity(host, userName, password, toSend, cc, "test", "1234123412", fileList);
    }
}
