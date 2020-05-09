package com.interfaceproject.httpclient;

import com.interfaceproject.entry.User;
import com.interfaceproject.utils.HxHttpClient;
import com.interfaceproject.utils.HxHttpClientResponseData;
import com.interfaceproject.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PostJsonGeneralCase {


    /**
     * POST---有参测试(json类型入参),统一使用utils工具类方法
     *
     * @date 2020年4月28日
     */
    @Test
    public void doPostCase() {

        String url = "http://127.0.0.1:8081/postjson";

        //url请求moco的have-paramaters-post-json.json模拟数据
        //这里通过工具类，定义map类型和java bean对象即可，后面通过hxHttpClient方法转换成json格式参数入参
        Map<String,Object> map = new HashMap<>();
        map.put("name","czy");
        map.put("age","29");

        HxHttpClient hxHttpClient = HxHttpClient.getInstance();
        hxHttpClient.config(url, "POST", map);
        HxHttpClientResponseData hxHttpClientResponseData = hxHttpClient.execute();
        //断言HTTP状态200
        Assert.assertEquals(String.valueOf(hxHttpClientResponseData.getCode()),"200");
        //断言response响应参数是否符合预期结果,content是为了统一业务处理的参数定义
        try {
            Assert.assertEquals(JsonUtils.json2map(hxHttpClientResponseData.getContent()).get("error"),"200");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JsonUtils.obj2json(hxHttpClientResponseData));
        System.out.println(hxHttpClientResponseData.getContent());
    }



    /**
     * POST---head里有签名认证用例--eg:headers里常放session、token、JTW
     *@author czy
     * @date 2020年4月28日
     */
    @Test
    public void postCase2(){
        String url = "http://127.0.0.1:8082/postheaders";

        //定义headers签名类型,headers的value自定义，模拟数据用json-paramaters-haveheaders-post.json数据
        Map<String,String> headers = new HashMap<>();
        headers.put("token","123456789%");

        HxHttpClient hxHttpClient = HxHttpClient.getInstance();
        Map<String,Object> map = new HashMap<>();
        map.put("data","20200509");
        map.put("age","29");
        hxHttpClient.config(url,"post",map);
        //这里注意header位置，需要位于config方法之后
        hxHttpClient.header(headers);
        HxHttpClientResponseData hxHttpClientResponseData = hxHttpClient.execute();
        //断言http返回的状态码是否为200, getcode是int类型，需要强转下string类型
        Assert.assertEquals(String.valueOf(hxHttpClientResponseData.getCode()),"200");
        //输出做下json格式打印
        System.out.println(JsonUtils.jsonFormatter(hxHttpClientResponseData.getContent()));

    }


    /**
     * hxHttpClient.config的Object params类型测试，map类型和javabean类型
     * */
    @Test
    public void testJson() {
        //map转json
        Map<String, Object> map = new HashMap<>();
        map.put("name", "czy");
        map.put("age", "29");
        System.out.println("map 转json:"+JsonUtils.obj2json(map));

        // javabean 转 json
        User user = new User();
        user.setId(1);
        user.setRealName("aa");
        user.setUserName("11");
        System.out.println("java bean转json:"+JsonUtils.obj2json(map));
    }
}
