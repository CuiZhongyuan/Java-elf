package com.interfaceproject.testngservice;

import org.testng.annotations.*;

import java.text.DateFormat;
import java.util.Date;

public class TestNgOrderTest {

    Date date = new Date();

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("@BeforeSuite注解执行");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("@AfterSuite注解执行");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("@BeforeClass注解执行");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("@AfterClass注解执行");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("@BeforeMethod注解");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("@AfterMethod注解执行");
    }
    @Test(groups = "service")
    public void testService(){
        System.out.println("@Test(groups = \"service\")分组注解执行");
    }
    @Test(groups = "client")
    public void testClient(){
        System.out.println("@Test(groups = \"client\")分组注解执行");
    }

    @BeforeGroups("clinet")
    public void clinetBeforeGroups(){
        System.out.println("clinet分组注解之前执行");
    }
    @AfterGroups("client")
    public void clinetAfterGroups(){
        System.out.println("client分组注解之后执行");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("@BeforeTest注解执行");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("@AfterTest注解执行");
    }
//    三个不同的线程调用函数parallelTest5次。此外，1秒的超时保证没有任何线程将永远在该线程上阻塞。
    @Test(threadPoolSize = 1,invocationCount = 10,timeOut = 1000)
    public void parallelTest(){
        DateFormat df4 = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
        System.out.println("并发测试007"+"-----"+df4.format(date));
    }
}
