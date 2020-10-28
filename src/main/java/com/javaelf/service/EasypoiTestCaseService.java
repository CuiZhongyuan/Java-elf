package com.javaelf.service;

import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.utils.EasyPoiUtil;
import com.javaelf.utils.JsonUtils;
import com.javaelf.utils.LoadStaticConfigUtil;
import com.javaelf.utils.RestTemplateUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Service
public class EasypoiTestCaseService extends AbstractTestNGSpringContextTests {
    //读取excel路径
    String excelCasePath = (String) LoadStaticConfigUtil.getCommonYml("testcaseexcel.cases");
    /**
     * 根据被测接口路径划分测试对象
     * @param testCaseDto
     */
    List<InterfaceMessageDto> caseMessageList = EasyPoiUtil.importExcel(excelCasePath, 0, 1, 1, InterfaceMessageDto.class);
    public void uilCase1(TestCaseDto testCaseDto) throws Exception {
        if (testCaseDto.getRelevanceFlag().equalsIgnoreCase("0")){
        Map<String,Object> bodyMap = JsonUtils.json2map(testCaseDto.getParams());
        for (InterfaceMessageDto messageDto : caseMessageList){
            if (messageDto.getApiId().equalsIgnoreCase("1")){
                String url = messageDto.getUrl();
                ResponseEntity responseEntity = RestTemplateUtils.post(url,bodyMap,Object.class);
               Map<String,Object> map = (Map<String,Object>)responseEntity.getBody();
                System.out.println(JsonUtils.mapToJson(map));
            }
        }
        }
    }

    public void uilCase2(){

    }
    public void uilCase3(){

    }
}
