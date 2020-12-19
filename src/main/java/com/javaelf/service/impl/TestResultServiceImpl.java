package com.javaelf.service.impl;

import com.javaelf.dao.TestCaseDao;
import com.javaelf.entity.TestCase;
import com.javaelf.service.TestResultService;
import com.javaelf.utils.JsonUtils;
import org.apache.commons.lang.StringEscapeUtils;
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

    /**
     * 异常测试--必填项缺省
     *接受正确参数--返回某个必填项参数缺省的入参参数
     * 默认取第一个参数为必填项，使用时需注意
     * @reqJson
     * */
    public String getMissParams(String reqJson){
         Map<String,Object> reqMap = JsonUtils.json2map(reqJson);
         Set set = reqMap.keySet();
         List<Map<String,Object>> list = (List<Map<String, Object>>) set.stream().map(m ->{
             Map respMap = new HashMap();
             respMap.put(m.toString(),reqMap.get(m));
             return respMap;
         }).collect(Collectors.toList());
         list.remove(0);
         String respStr = JsonUtils.obj2json(list);
         if (respStr.contains("},{")){
             respStr = respStr.replace("},{",",");
         }
         return respStr;
    }
    /**
     * 异常测试--必填项为""
     *接受正确参数--返回某个必填项参数值为：”“
     * 默认取第一个参数为必填项，使用时需注意
     * @reqJson
     * */
    public String getParamsNull(String reqJson){
         Map<String,Object> reqMap = JsonUtils.json2map(reqJson);
         Set set = reqMap.keySet();
         List<Map<String,Object>> list = (List<Map<String, Object>>) set.stream().map(m ->{
           Map respMap = new HashMap();
           respMap.put(m.toString(),reqMap.get(m));
           return respMap;
         }).collect(Collectors.toList());
         Map replaceMap = list.get(0);
         Set set1 = replaceMap.keySet();
         set1.forEach(key ->{
             replaceMap.put(key,"");
         });
         String respStr = JsonUtils.obj2json(list);
        if (respStr.contains("},{")){
            respStr = respStr.replace("},{",",");
        }
        return respStr;
    }
    /**
     * 异常测试--必填项参数值长度超长测试
     *接受正确参数--返回某个必填项参数值超过设置长度
     * 默认取第一个参数为必填项，使用时需注意
     * @reqJson
     * */
    public String getTooLong(String reqJson){
        Map<String,Object> reqMap = JsonUtils.json2map(reqJson);
        Set set = reqMap.keySet();
        List<Map<String,Object>> list = (List<Map<String, Object>>) set.stream().map(m ->{
            Map respMap = new HashMap();
            respMap.put(m.toString(),reqMap.get(m));
            return respMap;
        }).collect(Collectors.toList());
        Map replaceMap = list.get(0);
        Set set1 = replaceMap.keySet();
        set1.forEach(key ->{
            //由于参数值长度不固定，暂时不动态获取赋值，目前写死长度为256，有点low了，^ -^ 数据库varchar类型默认是255
            replaceMap.put(key,"TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" +
                    "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" +
                    "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" +
                    "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" +
                    "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" +
                    "TTTTTT");
        });
        String respStr = JsonUtils.obj2json(list);
        if (respStr.contains("},{")){
            respStr = respStr.replace("},{",",");
        }
        return respStr;
    }
    /**
     * 异常测试--必填项参数类型不一致
     *接受正确参数--返回某个必填项参数类型不一致
     * 默认取第一个参数为必填项，使用时需注意
     * @reqJson
     * */
    public String getTypeError(String reqJson){
        Map<String,Object> reqMap = JsonUtils.json2map(reqJson);
        Set set = reqMap.keySet();
        List<Map<String,Object>> list = (List<Map<String, Object>>) set.stream().map(m ->{
            Map respMap = new HashMap();
            respMap.put(m.toString(),reqMap.get(m));
            return respMap;
        }).collect(Collectors.toList());
        Map replaceMap = list.get(0);
        Set set1 = replaceMap.keySet();
        set1.forEach(key ->{
            //获取value类型
            if ( replaceMap.get(key) instanceof String){
                //Integer.parseInt()就是把String类型转化为int类型
                replaceMap.put(key,Integer.parseInt((String) replaceMap.get(key)));
            }else if (replaceMap.get(key) instanceof Integer){
                replaceMap.put(key,replaceMap.get(key).toString());
            }
        });
        String respStr = JsonUtils.obj2json(list);
        if (respStr.contains("},{")){
            respStr = respStr.replace("},{",",");
        }
        return respStr;

    }

}
