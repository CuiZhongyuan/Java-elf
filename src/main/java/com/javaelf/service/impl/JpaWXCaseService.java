package com.javaelf.service.impl;

import com.javaelf.dao.InterfaceMsgDao;
import com.javaelf.dao.TestCaseDao;
import com.javaelf.dao.VariableSubstitutionDao;
import com.javaelf.entity.InterfaceMsg;
import com.javaelf.entity.TestCase;
import com.javaelf.entity.VariableSubstitution;
import com.javaelf.utils.AssertionOverrideUtil;
import com.javaelf.utils.JsonUtils;
import com.javaelf.utils.RestTemplateUtils;
import com.javaelf.utils.VariableUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JpaWXCaseService {
    @Autowired
    private InterfaceMsgDao interfaceMsgDao;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private VariableSubstitutionDao variableSubstitutionDao;

    public void getToken() {
        List<InterfaceMsg> interfaceMsgList = interfaceMsgDao.findAll();
        List<TestCase> testCaseList = testCaseDao.findAll();
        List<VariableSubstitution> variableSubstitutionList = variableSubstitutionDao.findAll();
        List<String> responseList = new ArrayList<>();
        for (InterfaceMsg interfaceMsg : interfaceMsgList) {
            if (interfaceMsg.getId() != null) {
                for (TestCase testCase : testCaseList) {
                    String parames = testCase.getBodyParames();
                    if (testCase.getInterfacemsgId().equals(interfaceMsg.getId())) {
                        if (testCase.getInterfacemsgId().equals(interfaceMsg.getId())) {
                            //调用工具类把body的变量名替换为变量值并写入数据库中
                            parames = VariableUtil.replaceVariables(parames, variableSubstitutionList,variableSubstitutionDao);
                            //请求参数写入到allure报告中
                            Allure.addAttachment("Request", parames);
                            Map<String, Object> paramesVariableValueMap = JsonUtils.json2map(parames);
                            //get请求拼接
                            String url = interfaceMsg.getUrlAddress();
                            url = url + "?corpid=" + paramesVariableValueMap.get("corpid") + "&corpsecret=" + paramesVariableValueMap.get("corpsecret");
                            String response = RestTemplateUtils.get(url, String.class).getBody();
                            System.out.println(response);
                            //预期结果断言
                            Map<String, Object> assertExpectMap = JsonUtils.json2map(response);
                            int expected = (int) assertExpectMap.get("errcode");
                            if (expected == 0) {
                                Allure.step("测试通过!---这是预期的成功", Status.PASSED);
                                //响应结果写入allure报告中
                                Allure.addAttachment("Response", response);
                            } else {
                                Allure.step("测试通过！---这是预期的失败", Status.FAILED);
                                //响应结果写入allure报告中
                                Allure.addAttachment("Response", response);
                            }
                            AssertionOverrideUtil.verifyEquals(expected, 0);
                            //区分测试类型0是正向，1是反向
                            if (testCase.getCaseStatus()==0){
                                response = "正向测试响应结果----- : "+response;
                            }else {
                                response = "反向测试响应结果----- : "+ response;
                            }
                            //获取响应结果更新数据库
                            testCaseDao.updateActual(response, testCase.getId());
                        }
                    }
                }
            }
        }
    }

}
