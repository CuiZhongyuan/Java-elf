package com.javaelf.testcase;

import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.service.EasypoiTestCaseService;
import com.javaelf.utils.EasyPoiUtil;
import com.javaelf.utils.LoadStaticConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Component
public class EapoiDatasCase  extends AbstractTestNGSpringContextTests {

    @Autowired
    EasypoiTestCaseService easypoiTestCaseService;

    /**
     * 使用开源easypoi文档工具进行读写excel测试用例池数据（数据驱动测试）
     *
     * @author czy
     * @date 2019/3/8
     */
    @Test
    public void easypoiTestCase() throws Exception {
        //获取excel测试数据源
        //读取excel路径
        String excelCasePath = (String) LoadStaticConfigUtil.getCommonYml("testcaseexcel.cases");
        //创建对应sheet表的list集合容器接收读取测试用例数据
        List<TestCaseDto> testCaseDtoList = EasyPoiUtil.importExcel(excelCasePath, 1, 1, 1, TestCaseDto.class);
        //过滤excel读取最后多出一个为空的数据
        List<TestCaseDto> collect = testCaseDtoList.stream().filter(loginData -> loginData.getId() != null).collect(Collectors.toList());
        for (TestCaseDto testCaseDto : collect) {
            if (testCaseDto.getApiId().equalsIgnoreCase("1")) {
                //如果测试用例读取的ApiId=1则执行被测信息为1的接口地址测试
                easypoiTestCaseService.uilCase1(testCaseDto);
            } else if (testCaseDto.getApiId().equalsIgnoreCase("2")) {
                //如果测试用例读取的ApiId=2则执行被测信息为2的接口地址测试
                easypoiTestCaseService.uilCase2();
            } else if (testCaseDto.getApiId().equalsIgnoreCase("3")) {
                //如果测试用例读取的ApiId=3则执行被测信息为3的接口地址测试
                easypoiTestCaseService.uilCase3();
            }
            //判断被测接口地址
            //进入相应接口进行用例测试
        }
    }
}
