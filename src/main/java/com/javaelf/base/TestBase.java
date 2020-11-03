package com.javaelf.base;

import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.dto.VariableDto;
import com.javaelf.utils.ExcelTestResultOutputUtil;
import com.javaelf.utils.VariableUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

@SpringBootTest
@Component
public class TestBase  extends AbstractTestNGSpringContextTests {
    /**
     * 使用Easypoi获取excel数据，传递，如果使用数据库这里不引用即可
     */
    //创建对应sheet表的list集合容器接收读取测试用例数据
    public List<InterfaceMessageDto> caseMessageList = VariableUtil.caseMessageList;
    public List<TestCaseDto> testCaseDtoList =VariableUtil.testCaseDtoList;
    //过滤excel读取最后多出一个为空的数据
    public List<VariableDto> variableDtoList = VariableUtil.variablesList;
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
        //执行用例后回写
        ExcelTestResultOutputUtil.exportSheet(caseMessageList,testCaseDtoList,variableDtoList);
    }
}
