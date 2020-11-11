package com.javaelf.base;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Component
@SpringBootTest
public class TestBase  extends AbstractTestNGSpringContextTests {
    /**
     * base测试类
     */
    /**
     * BeforeClass
     */
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        //todo 需要执行前的操作
    }
    /**
     * AfterClass
     */
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //todo 执行用例后回写
    }
}
