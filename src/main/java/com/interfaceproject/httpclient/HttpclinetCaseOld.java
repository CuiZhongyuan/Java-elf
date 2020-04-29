package com.interfaceproject.httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpclinetCaseOld {

    /**
     * GET---无参测试
     *
     * @date 2020年4月28日
     */
    @Test
    public void doGetCase1() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求，URL请求为moco中的no-paramaters-get1.json的模拟数据
        HttpGet httpGet = new HttpGet("http://localhost:8081/demo");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * POST---无参测试
     *
     * @date 2020年4月28日
     */
//    @Test
    public void doPostCase2() {
        // 创建Post请求 uri地址请求moco中的no-paramaters-post.json模拟数据
        HttpPost httpPost = new HttpPost("http://localhost:8081/demo");
        post(httpPost, null);
    }

    /**
     * GET---有参测试 (将参数放入键值对类中,再放入URI中,从而通过URI得到HttpGet实例)
     *
     * @date 2020年4月28日
     */

//    @Test
    public void doGetCase3() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("name", "czy"));
            params.add(new BasicNameValuePair("age", "29"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            // url请求为moco 中的have-paramaters-get.json模拟数据
            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(8081).setPath("/getdemo")
                    .setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * POST---有参测试(表单类型入参)
     *
     * @date 2020年4月28日
     */
    @Test
    public void doPostCase4() {
        //url请求moco的have-paramaters-post.json模拟数据
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8081/postwithparam");
        httpPost.setHeader("Content-Type","multipart/form-data");
        List<BasicNameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("name","czy"));
        params.add(new BasicNameValuePair("age","29"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);
        httpPost.setEntity(formEntity);
        post(httpPost, formEntity);
    }


    //抽离出post上面的公有方法
    public String post(HttpPost httpPost, HttpEntity httpEntity) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        if (null != httpEntity) {
            httpPost.setEntity(httpEntity);
        }
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("HTTP响应状态为："+ response.getStatusLine().getStatusCode());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            if (responseEntity != null){
                System.out.println("HTTP响应内容长度为：" + responseEntity.getContentLength() );
                //主动设置编码，来防止响应乱码
                String responseStr = EntityUtils.toString(responseEntity,StandardCharsets.UTF_8);
                Map<String, Object> map = (Map<String, Object>) JSONObject.parse(responseStr);
                System.out.println("map:"+map);
                Assert.assertEquals(map.get("error"), "200");
                System.out.println("HTTP响应内容为：" + map.get("mages"));
                return responseStr;
            }
        }catch (ParseException | IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
