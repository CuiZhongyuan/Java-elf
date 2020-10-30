package com.javaelf.service;

import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.utils.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import java.util.Map;

@SpringBootTest
@Service
public class EasypoiTestCaseService extends AbstractTestNGSpringContextTests {
    /**
     * 根据被测接口路径划分测试对象
     * @param testCaseDto
     */
    public void uilCase1(TestCaseDto testCaseDto,InterfaceMessageDto interfaceMessageDto) throws Exception {
            //接收变量获取到的body
            String parames = testCaseDto.getParams();
            if (parames!=null){
                //调用工具类把body的变量名替换为变量值
                parames = VariableUtil.replaceVariables(parames);
                Map<String, Object> bodyMap = JsonUtils.json2map(parames);
                String url = interfaceMessageDto.getUrl();
                ResponseEntity responseEntity = RestTemplateUtils.post(url, bodyMap, Object.class);
                Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
                System.out.println(JsonUtils.mapToJson(map));

            }else {
                System.out.println("请求参数不能为空："+parames);
            }
    }
//    public void uilCase2(TestCaseDto testCaseDto,InterfaceMessageDto interfaceMessageDto) throws Exception {
//        //接收变量获取到的body
//        String parames = testCaseDto.getParams();
//        if (parames!=null){
//            //调用工具类把body的变量名替换为变量值
//            parames = VariableUtil.replaceVariables(parames);
//            Map<String, Object> bodyMap = JsonUtils.json2map(parames);
//            String url = interfaceMessageDto.getUrl();
//            HttpHeaders header = testCaseDto.getHeaders();
//            Map<String, Object> getBodyMap = JsonUtils.json2map(parames);
//            ResponseEntity responseEntity = RestTemplateUtils.get(url,header,Object.class,getBodyMap);
//            Map<String, Object> map = (Map<String, Object>) responseEntity.getBody();
//            System.out.println(JsonUtils.mapToJson(map));
//        }else {
//            System.out.println("请求参数不能为空："+parames);
//        }
//    }

}
