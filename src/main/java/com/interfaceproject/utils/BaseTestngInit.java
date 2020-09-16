package com.interfaceproject.utils;

import com.interfaceproject.listener.ExtentTestNGIReporterListener;
import com.interfaceproject.listener.RetryListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.ArrayList;
import java.util.List;

public class BaseTestngInit {

    /**
     * 初始化testng
     */
    public  void baseTestngInitCode()  {

        //创建testng对象
        TestNG testng = new TestNG();
        //相当于在测试类上面配置注解
        // @Listeners({com.webtest.test.util.ReportListener.class,com.webtest.test.util.RetryListener.class})
        //创建报告监听器对象
        ExtentTestNGIReporterListener reportListener = new ExtentTestNGIReporterListener();
        //创建失败重试对象
        RetryListener retryListener = new RetryListener();
        //设置需要执行的测试用例类
        testng.setTestClasses(new Class[] { com.interfaceproject.testcase.PostListCase.class});
//        testng.setTestClasses(new Class[] { com.interfaceproject.testngservice.PostListCase.class});
        //添加监听器
        testng.addListener(reportListener);
        testng.addListener(retryListener);
        //运行测试
        testng.run();

    }

    /**
     * 通过testng.xml初始化testng
     * @param xmlPath
     */
    public  void baseTestngInitXml(String xmlPath){
        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add(xmlPath);
        testNG.setTestSuites(suites);
        testNG.run();
    }

    public  void baseTestngInitVirtualXml(XmlSuite... suite){
        List<XmlSuite> suites = new ArrayList<>();
        for (XmlSuite i:suite) {
            suites.add(i);
        }
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();

    }

    /*<suite name="TmpSuite" >
        <test name="TmpTest" >
             <classes>
                  <class name="test.failures.Child"  />
             <classes>
         </test>
       </suite>
    */
    /**
     * 配置虚拟testng.xml
     */
    public  XmlSuite testngSuiteConfig(String suiteName,String testName,String... classPaths){
        //创建suite节点
        XmlSuite suite = new XmlSuite();
        //设置suite名称
        suite.setName(suiteName);
        //创建test节点
        XmlTest test = new XmlTest(suite);
        //设置节点名称
        test.setName(testName);
        //创建测试对象集合
        List<XmlClass> classes = new ArrayList<>();
        for (String  classPath: classPaths) {
            classes.add(new XmlClass(classPath));
        }
        test.setXmlClasses(classes) ;
        return suite;
    }
}
