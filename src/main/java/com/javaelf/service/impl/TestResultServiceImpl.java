package com.javaelf.service.impl;

import com.javaelf.dao.TestCaseDao;
import com.javaelf.entity.TestCase;
import com.javaelf.service.TestResultService;
import com.javaelf.utils.JsonUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
     * 必填项未传的用例组合---根据参数必填项某一个未传+非必传参数，组合生成新的请求参数用例
     *
     *
     * @return*/
    public String mandatory(String reqJson) {
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
        Map<String,Object> objectMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int finalI = i;
            list.stream().peek(mapParam -> {
                if (!list.get(finalI).equals(mapParam)) {
                    stringBuilder.append(JsonUtils.mapToJson(mapParam));
                }
            }).collect(Collectors.toList());
            stringBuilder.append(JsonUtils.mapToJson(noMap));
            objectMap.put("必填项" + list.get(i) + "为空的请求参数: ", stringBuilder.toString().replace("}{", ","));
        }
        String str = JsonUtils.mapToJson(objectMap);
        str = StringEscapeUtils.unescapeJava(str);
        str=str.replace(" \":\"{"," \":{");
        str=str.replace("}\",\"","},\"");
        str=str.replace("}\"}","}}");
        System.out.println(str);
        return str;
    }

    /**
     * 根据参数数生成某一个参数缺省测试用例集
     *
     *
     * @return*/
    @Override
    public String hybrid(String reqJson) {
         Map map = JsonUtils.json2map(reqJson);
         TreeMap treeMap = new TreeMap(map);
         Set set = treeMap.keySet();
         TreeMap<String,Object> treeMap1 = new TreeMap();
         set.forEach(key ->{
             Map<String,String> reqMap = new HashMap();
             treeMap.forEach((k,v)->{
                 if (key.equals(k)){
                     reqMap.put(k.toString(),"");
                 }else {
                     reqMap.put(k.toString(),v.toString());
                 }
             });
             String format = String.format("参数值：%s为空返回结果", key);
             treeMap1.put(format,reqMap);
         });

        return JsonUtils.mapToJson(treeMap1);
    }


}
