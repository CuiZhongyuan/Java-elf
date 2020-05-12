package testcase;


import com.interfaceproject.utils.HxHttpClient;
import com.interfaceproject.utils.HxHttpClientResponseData;
import com.interfaceproject.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


//早期使用的mock框架moco进行数据模拟，后面通过springboot独立写请求提供测试数据，并通过swagger2生成api文档

public class PostListCase {

//    String url = "http://127.0.0.1:8081/postjson";


    /**
     * POST---head里无签名认证用例
     *@author czy
     * @date 2020年4月28日
     */
    @Test
    public void  postCase1(){

        String url = "http://127.0.0.1:8090/postjson";

        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("name","czy");
        map.put("age","29");
        //创建HxHttpClient对象
        HxHttpClient hxHttpClient =  HxHttpClient.getInstance();
        //传入请求的url、参数
        hxHttpClient.config(url,"POST",map);
        // 接收响应参数
        HxHttpClientResponseData hxHttpClientResponseData = hxHttpClient.execute();
        //接收业务统一处理参数
        String responseContent = hxHttpClientResponseData.getContent();
        System.out.println(hxHttpClientResponseData);
        System.out.println(responseContent);
        //断言http状态码200,getcode是int类型，需要强转下string类型
        Assert.assertEquals(String.valueOf(hxHttpClientResponseData.getCode()),"200");

        //断言业务参数响应是否与预期结果一直,这里取值error是getContent的json对象，需把json转成Map对象后取error的key断言
        try {
            Assert.assertEquals(JsonUtils.json2map(hxHttpClientResponseData.getContent()).get("error"),"1000");
        } catch (Exception e) {
            e.printStackTrace();
        }

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
     * POST---上一个请求返回的某个参数作为下一个请求的入参参数
     *@author czy
     * @date 2020年05月09日
     */
    @Test
    public void  postCase3() throws Exception {


        String data = case1();
        Thread.sleep(1000);
        // todo
        case2(data);

    }

    private String case1() throws Exception {

        String url = "http://127.0.0.1:8090/postjson";

        //传入参数
        Map<String,Object> map = new HashMap<>();
        map.put("name","czy");
        map.put("age","29");
        //创建HxHttpClient对象
        HxHttpClient hxHttpClient =  HxHttpClient.getInstance();
        //传入请求的url、参数
        hxHttpClient.config(url,"POST",map);
        // 接收响应参数
        HxHttpClientResponseData hxHttpClientResponseData = hxHttpClient.execute();
        //接收业务统一处理参数
        String responseContent = hxHttpClientResponseData.getContent();
        System.out.println(hxHttpClientResponseData);
        System.out.println(JsonUtils.jsonFormatter(hxHttpClientResponseData.getContent()));
        System.out.println("=======================================");
        //断言http状态码200,getcode是int类型，需要强转下string类型
        Assert.assertEquals(String.valueOf(hxHttpClientResponseData.getCode()),"200");
        //断言业务参数响应是否与预期结果一直,这里通过JsonUtils工具类获取map中的参数error与预期1000对比
        try {
            Assert.assertEquals(JsonUtils.json2map(hxHttpClientResponseData.getContent()).get("data"),"20200509");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String) JsonUtils.json2map(hxHttpClientResponseData.getContent()).get("data");
    }



    private void case2(String data) throws Exception {
        String url = "http://127.0.0.1:8082/postheaders";

        //定义headers签名类型,headers的value自定义，模拟数据用json-paramaters-haveheaders-post.json数据
        Map<String,String> headers = new HashMap<>();
        headers.put("token","123456789%");

        HxHttpClient hxHttpClient = HxHttpClient.getInstance();
        Map<String,Object> map = new HashMap<>();
        map.put("data",data);
        map.put("age","29");
        hxHttpClient.config(url,"post",map);
        //这里注意header位置，需要位于config方法之后
        hxHttpClient.header(headers);
        HxHttpClientResponseData hxHttpClientResponseData = hxHttpClient.execute();
        System.out.println(hxHttpClientResponseData);
        //输出做下json格式打印
        System.out.println(JsonUtils.jsonFormatter(hxHttpClientResponseData.getContent()));

        //断言http返回的状态码是否为200, getcode是int类型，需要强转下string类型
        Assert.assertEquals(String.valueOf(hxHttpClientResponseData.getCode()),"200");
        //断言响应参数是否与预期结果一致,由于断言name是data的Json对象key，这里需要把json对象强转为Map对象供后dataMap.get("name")获取name值
        Map<String, Object> dataMap = (Map<String, Object>) JsonUtils.json2map(hxHttpClientResponseData.getContent()).get("data");
        try {
            Assert.assertEquals(dataMap.get("name"),"北京");
            System.out.println("name:"+dataMap.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
