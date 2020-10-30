package com.javaelf.base;

import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.dto.VariableDto;
import com.javaelf.service.EasypoiTestCaseService;
import com.javaelf.utils.EasyPoiUtil;
import com.javaelf.utils.ExcelTestResultOutputUtil;
import com.javaelf.utils.LoadStaticConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Component
public class TestBase  extends AbstractTestNGSpringContextTests {
    @Autowired
    EasypoiTestCaseService easypoiTestCaseService;
    String excelCasePath = (String) LoadStaticConfigUtil.getCommonYml("testcaseexcel.path");
    //创建对应sheet表的list集合容器接收读取测试用例数据
    public List<InterfaceMessageDto> caseMessageList = EasyPoiUtil.importExcel(excelCasePath, 0, 1, 1, InterfaceMessageDto.class);
    public List<TestCaseDto> testCaseDtoList = EasyPoiUtil.importExcel(excelCasePath, 1, 1, 1, TestCaseDto.class);
    //过滤excel读取最后多出一个为空的数据
    public List<TestCaseDto> writeBackTestCaseList = testCaseDtoList.stream().filter(loginData -> loginData.getId() != null).collect(Collectors.toList());
    public List<VariableDto> variableDtoList = EasyPoiUtil.importExcel(excelCasePath, 2, 1, 1, VariableDto.class);

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
        ExcelTestResultOutputUtil.exportSheet(caseMessageList,writeBackTestCaseList,variableDtoList);
    }
}
