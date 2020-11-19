package com.javaelf.service.impl;

import com.javaelf.dao.TestCaseDao;
import com.javaelf.entity.TestCase;
import com.javaelf.service.TestResultService;
import com.javaelf.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestCaseDao testCaseDao;

    /**
     * 查询实际执行测试用例的结果
     *
     * */
    @Override
    public List<String> getActualTestResult() {
        List<TestCase> testCases = testCaseDao.findAll();
        List<String> getActual = new ArrayList<>();
        for (TestCase testCase : testCases){
            getActual.add(testCase.getActual());
        }
        return getActual;
    }
    /**
     * 必填项为空的用例组合---根据必填项遍历生成异常组合+非必填项
     *
     * */
    @Override
    public ArrayList<String> mandatory(String reqJson) {
        ArrayList<String> testList = new ArrayList<>();
//        String json = "{\"yes\":{\"parames1\":\"11\",\"parames2\":\"22\",\"parames3\":\"33\"},\"no\":{\"parames4\":\"44\",\"parames5\":\"55\"}}";
        Map map = JsonUtils.json2map(reqJson);
        Map yesMap = (Map) map.get("yes");
        Map noMap = (Map) map.get("no");
        Set set = yesMap.keySet();
        List<Map<String, Object>> list = (List<Map<String, Object>>) set.stream().map(m -> {
            Map<String, Object> hashMap = new HashMap<>();
            //获取需要组合的必填项参数
            hashMap.put(m.toString(), yesMap.get(m));
            return hashMap;
        }).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                Object o = j <= i ? "" : list.get(j);
                String s = JsonUtils.mapToJson(list.get(i)) + "," + JsonUtils.mapToJson(list.get(j))+","+JsonUtils.mapToJson(noMap);
                String response = s.replace("},{",",");
                testList.add(response);
            }
        }
        return testList;
    }
}
