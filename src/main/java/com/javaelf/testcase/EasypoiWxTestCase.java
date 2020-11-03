package com.javaelf.testcase;

import com.javaelf.base.TestBase;
import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.utils.AssertionOverrideUtil;
import com.javaelf.utils.JsonUtils;
import com.javaelf.utils.RestTemplateUtils;
import com.javaelf.utils.VariableUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.model.Status;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * 企业微信接口测试
 */
@Listeners(com.javaelf.listener.AssertListener.class)
public class EasypoiWxTestCase extends TestBase {
    @Severity( SeverityLevel.BLOCKER)
    @Description("企业微信获取token覆盖测试")
    @Test
    public void gettokenCase() throws Exception {
        for (InterfaceMessageDto interfaceMessageDto : caseMessageList) {
            if (interfaceMessageDto.getApiId() != null) {
                for (TestCaseDto testCaseDto : testCaseDtoList) {
                    if (testCaseDto.getApiId().equalsIgnoreCase(interfaceMessageDto.getApiId())) {
                        String parames = testCaseDto.getParams();
                        if (parames != null & testCaseDto.getApiId().equalsIgnoreCase("1")) {
                            //调用工具类把body的变量名替换为变量值
                            parames = VariableUtil.replaceVariables(parames);
                            //请求参数写入到allure报告中
                            Allure.addAttachment("Request", parames);
                            Map<String,Object> paramesMap = JsonUtils.json2map(parames);
                            //get请求拼接
                            String url = interfaceMessageDto.getUrl();
                            url = url+"?corpid="+paramesMap.get("corpid")+"&corpsecret="+paramesMap.get("corpsecret");
                            String response = RestTemplateUtils.get(url,String.class).getBody();
                            System.out.println(response);
                            //响应结果回写到测试用例中
                            testCaseDto.setActual(response);
                            //响应结果写入allure报告中
                            Allure.addAttachment("Response", response);
                            //预期结果断言
                            Map<String,Object> assertExpectMap = JsonUtils.json2map(response);
                            int expected = (int) assertExpectMap.get("errcode");
                            if (expected ==0){
                                Allure.step("测试通过", Status.PASSED);
                            }else {
                                Allure.step("断言失败", Status.FAILED);
                                AssertionOverrideUtil.verifyEquals(0, expected);
                            }
                        }
                    }
                }
            }
        }
    }
}
