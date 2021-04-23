package testcase;

import com.javaelf.utils.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestCaseDemo {

    @Test
    public void case1(){
        String str1 = "qzcsbj";
        String str2 = "qzcsbj";
        String str3 = new String("qzcsbj");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
    }

    @Test
    public void case3(){
        String url = "http://192.168.1.57:811/platform-product/commodity/page-others";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzIxNDAwMTQ5NjAzMzg5NDQwIiwiYXVkIjoiMDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjYiLCJjb21wYW55SWRzIjpbMTI2MDQ2MzA2NzEwODYxMDA0OF0sIm5iZiI6MTYwNDAyMTM3MSwiaXNzIjoicmVzdGFwaXVzZXIiLCJleHAiOjE2MDQwMjg1NzEsInVzZXJJZCI6MTMyMTQwMDE0OTYwMzM4OTQ0MCwiaWF0IjoxNjA0MDIxMzcxLCJqdGkiOiJiMzBmZjJjMi1kZTUxLTQ4ODYtYjNkMy01ODQ2YWIxOTc4NjkifQ.d73DENmtduK7M53HqVhIYb4Aq_1nOWAtvfBzOk3Wmzk";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        ResponseEntity responseEntity = RestTemplateUtils.get(url,headers,Object.class,"");
        Map<String,Object> map = (Map<String, Object>) responseEntity.getBody();
        System.out.println(JsonUtils.mapToJson(map));
    }

    @Test
    public String case4(){
        String str = "\ncom.javaelf.service.TestDataFactory\n";
        String[] out = str.split("\n");
        for (int i=0;i<out.length;i++){
            return str;
        }
        return null;
    }
    @Test
    public void case5(){
        System.out.println(case4());
    }

    @Test
    public void case6(){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        String url1 = " http://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww27d6f876d80ceec6&corpsecret=elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw8";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Content-Type","application/json");
        Map<String,Object> map = new HashMap<>();
        map.put("corpid","ww27d6f876d80ceec6");
        map.put("corpsecret","elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw8");
        String json = JsonUtils.obj2json(map);
//        String responseEntity = RestTemplateUtils.get(url,String.class,json).getBody();
        String responseEntity = RestTemplateUtils.get(url,String.class,json).getBody();
        System.out.println(responseEntity);
    }
    @Test
    public void  case7(){
        //字符串转map类型Map<String, Object>，再转 Map<String, String>，放入 httpHeaders.setAll(mapStr);
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        String header = "{\"corpid\":\"${corpidTrue}\",\"corpsecret\":\"${corpidFalse}\"}";
        Map<String, Object> map1 = JsonUtils.json2map(header);
        Map<String, String> mapStr = new HashMap<>();
        map1.keySet().stream().peek(mapKey -> {
            mapStr.put(mapKey, map1.get(mapKey).toString());
        }).collect(Collectors.toList());
        System.out.println(mapStr);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(mapStr);
        String responseEntity = RestTemplateUtils.get(url,httpHeaders,String.class).getBody();
        System.out.println(responseEntity);
    }

    /**
     * 二分查找,方法
     * */
    @Test
    public int case8(int[] num,int startNum,int endNum,int findNum){
        if (startNum <= endNum){
            //算出中间位置
            int middle = (startNum+endNum)/2;
            //数组中间数字
            int middleValue = num[middle];
            //二分查找
            if (findNum == middleValue){
                return middle;
            }else if (findNum < middle){
                //小于中间值，在中间值之前的数据中查找，使用递归
                return case8(num,startNum,middle-1,findNum);
            }else {
                //大于中间值在，在中间值之前查找
                return case8(num,middle+1,endNum,findNum);
            }
        }
        return -1;
    }

    @Test
    public void case9(){
        //要查到的数组
        int[] binaryNums = new int[100];
        //一次循环加入数据
        for (int i=0;i<100;i++){
            binaryNums[i] =(i+1);
        }
        //定义要查找到的值
        int findNum = 65;
        int findResult = case8(binaryNums,0,binaryNums.length-1,findNum);
        System.out.println("元素的位置是："+(findResult+1));
    }
    @Test
    public void mandatory() {
        String json = "{\"yes\":{\"parames1\":\"11\",\"parames2\":\"22\",\"parames3\":\"33\",\"parames4\":\"44\"},\"no\":{\"parames8\":\"88\",\"parames9\":\"99\"}}";
        Map map = JsonUtils.json2map(json);
        Map yesMap = (Map) map.get("yes");
        Map noMap = (Map) map.get("no");
        Set set = yesMap.keySet();
        List<Map<String, Object>> list = (List<Map<String, Object>>) set.stream().map(m -> {
            Map<String, Object> hashMap = new HashMap<>();
            //获取需要组合的必填项参数
            hashMap.put(m.toString(), yesMap.get(m));
            return hashMap;
        }).collect(Collectors.toList());
        Map<String, Object> objectMap = new HashMap<>();
        Map<String, Object> valueMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int finalI = i;
            list.stream().peek(mapParam -> {
                if (!list.get(finalI).equals(mapParam)) {
                    stringBuilder.append(JsonUtils.mapToJson(mapParam));
                }
            }).collect(Collectors.toList());
            stringBuilder.append(JsonUtils.mapToJson(noMap));
            objectMap.put("必填项" + list.get(i) + "为空的请求参数===>", stringBuilder.toString().replace("}{", ","));
        }
        System.out.println(JsonUtils.mapToJson(objectMap));
    }

    @Test
    public void mandatory22() {
        String json = "{\"yes\":{\"parames1\":\"11\",\"parames2\":\"22\",\"parames3\":\"33\",\"parames4\":\"44\",\"parames5\":\"55\"},\"no\":{\"parames8\":\"88\",\"parames9\":\"99\"}}";
        String json2 = "{\"yes\": {\"parames1\": \"11\",\"parames2\": \"22\",\"parames3\": \"33\",\"parames4\": \"44\",\"parames5\": \"55\"},\"no\": {\"parames4\": \"88\",\"parames5\": \"99\"}}";
        Map map = JsonUtils.json2map(json2);
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
    }
    @Test
    public void mandatory1() {
        String json = "{\"parames1\": \"11\",\"parames2\": \"22\",\"parames3\": \"33\"}";
            Map map = JsonUtils.json2map(json);
            Set set = map.keySet();
            Map requestMap = new HashMap();
            List<Map<String, Object>> list = (List<Map<String, Object>>) set.stream().map(m -> {
                Map<String, Object> hashMap = new HashMap<>();
                //获取需要组合的必填项参数
                hashMap.put(m.toString(), map.get(m));
                return hashMap;
            }).collect(Collectors.toList());
            for (int i = 0; i < list.size(); i++) {
                int finalI = i;
                set.stream().forEach(k ->{
                    requestMap.put("参数值："+k+"为空返回结果",map.get(k));
                });
            }
            String str = JsonUtils.mapToJson(requestMap);
//            str = StringEscapeUtils.unescapeJava(str);
//            str=str.replace(" \":\"{"," \":{");
//            str=str.replace("}\",\"","},\"");
//            str=str.replace("}\"}","}}");
            System.out.println(str);
    }

    @Test
    public void hybird(){
        String json = "{\"parames1\": \"11\",\"parames2\": \"22\",\"parames3\": \"33\"}";
        Map map = JsonUtils.json2map(json);
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
        System.out.println(JsonUtils.mapToJson(treeMap1));
    }
    //测试随机数据的生成
    @Test
    public void getTestRandom(){
        String str = RandomDataKit.randomDataStringInspect("测试-随机数Random(Long[3])");
        System.out.println(str);
    }

}
