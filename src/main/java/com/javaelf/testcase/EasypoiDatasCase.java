package com.javaelf.testcase;

import com.javaelf.base.TestBase;
import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.utils.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;


/**
 * 使用开源easypoi文档工具进行读写excel测试用例池数据（数据驱动测试）
 * @author czy
 * @date 2020/3/8
 */
@Listeners(com.javaelf.listener.AssertListener.class)
public class EasypoiDatasCase extends TestBase {

    @Severity( SeverityLevel.BLOCKER)
    @Description("登录接口覆盖测试")
    @Test
    public void loginCase() throws Exception {
        for (InterfaceMessageDto interfaceMessageDto : caseMessageList){
            if (interfaceMessageDto.getApiId() != null){
                for (TestCaseDto testCaseDto : writeBackTestCaseList){
                    if (testCaseDto.getApiId().equalsIgnoreCase(interfaceMessageDto.getApiId())){
                        String parames = testCaseDto.getParams();
                        if (parames != null & testCaseDto.getApiId().equalsIgnoreCase("1")){
                            String url = interfaceMessageDto.getUrl();
                            //调用工具类把body的变量名替换为变量值
                            parames = VariableUtil.replaceVariables(parames);
                            Map<String,Object> bodyMap = JsonUtils.json2map(parames);
                            String allureBody = JsonUtils.mapToJson(bodyMap);
                            //请求参数写入到allure报告中
                            Allure.addAttachment("Request",allureBody );
                            //调用请求工具
                            ResponseEntity responseEntity = RestTemplateUtils.post(url,bodyMap,Object.class);
                            Map<String,Object> responseBody = (Map<String, Object>) responseEntity.getBody();
                            String resp = JsonUtils.mapToJson(responseBody);
                            System.out.println(resp);
                            testCaseDto.setActual(resp);
                            //响应结果写入allure报告中
                            Allure.addAttachment("Response",resp);
                            //预期结果断言
                            int expected = (int) responseBody.get("ret");
                            AssertionOverrideUtil.verifyEquals(0,expected);
                        }
                    }
                }
            }
        }
    }

    @Severity( SeverityLevel.NORMAL)
    @Description("分页查询接口覆盖测试")
    @Test
    public void serachCase() throws Exception {
        for (InterfaceMessageDto interfaceMessageDto : caseMessageList){
            if (interfaceMessageDto.getApiId() != null){
                for (TestCaseDto testCaseDto : writeBackTestCaseList){
                    if (testCaseDto.getApiId().equalsIgnoreCase(interfaceMessageDto.getApiId())){
                        String header = testCaseDto.getHeaders();
                        header = VariableUtil.replaceVariables(header);
                        if (header != null & testCaseDto.getApiId().equalsIgnoreCase("2")){
                            Map<String, Object> map = JsonUtils.json2map(header);
                            String getHeader = (String) map.get("Authorization");
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.set("Authorization",getHeader);
                            httpHeaders.set("Content-Type","application/json");
                            String url = interfaceMessageDto.getUrl();
                            String allureRequest = httpHeaders.toString()+url;
                            //请求参数写入到allure报告中
                            Allure.addAttachment("Request",allureRequest);
                            //调用请求工具
                            ResponseEntity responseEntity = RestTemplateUtils.get(url,httpHeaders,Object.class,"");
                            Map<String,Object> responseBody = (Map<String, Object>) responseEntity.getBody();
                            String resp = JsonUtils.mapToJson(responseBody);
                            System.out.println(resp);
                            testCaseDto.setActual(resp);
                            //响应结果写入allure报告中
                            Allure.addAttachment("Response",resp);
                            //预期结果断言
                            int expected = (int) responseBody.get("ret");
                            AssertionOverrideUtil.verifyEquals(0,expected);
                        }
                    }
                }
            }
        }
    }

}
