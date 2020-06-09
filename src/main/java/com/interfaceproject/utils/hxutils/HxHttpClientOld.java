package com.interfaceproject.utils.hxutils;

import com.interfaceproject.utils.JsonUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http同步请求工具类
 *
 * @author lgl
 */
public class HxHttpClientOld {
    private static Logger logger = LoggerFactory.getLogger(HxHttpClientOld.class);

    /**
     * @param url 请求的全路径 例如：http://localhost:8088/json.html
     * @return 返回json字符串
     */
    public static String get(String url) {
        String returnStr = "";
        CloseableHttpClient httpClient = HxHttpClientPoolOld.getHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            /**
             * setConnectTimeout(20000)：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout(20000) 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的
             * setSocketTimeout(20000) 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(200000).setConnectionRequestTimeout(200000).setConnectTimeout(200000).build();
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            /*
             * 判断HTTP的返回状态码，如果正确继续解析返回的数据
             */
            //HttpStatus.SC_OK=200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                returnStr = EntityUtils.toString(response.getEntity());
            } else {
                returnStr = "{\"code\":\"500\",\"msg\":\"请求地址异常(" + response.getStatusLine().getStatusCode() + ")\",\"content\":\"\",\"extendData\":\"\"}";
                logger.info(">>>>>>>httpClient Get 请求地址异常---》url:" + url);
            }
        } catch (ClientProtocolException e) {
            logger.error(">>>>>>>httpClient Get ClientProtocolException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"601\",\"msg\":\"请求地址异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Get ClientProtocolException 异常---》url:" + url, e);
        } catch (IOException e) {
            logger.error(">>>>>>>httpClient Get IOException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"602\",\"msg\":\"IO异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Get IOException 异常---》url:" + url, e);
        }
        return returnStr;
    }

    /**
     * 带参数的get请求
     * @param url
     * @param body 参数的json字符串
     * @return
     */
    public static String get(String url,String body) {
        String returnStr = "";
        CloseableHttpClient httpClient = HxHttpClientPoolOld.getHttpClient();
        try {
            HttpWithEntity httpGet = new HttpWithEntity("GET",url,body);
            /**
             * setConnectTimeout(20000)：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout(20000) 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的
             * setSocketTimeout(20000) 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(200000).setConnectionRequestTimeout(200000).setConnectTimeout(200000).build();
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            /*
             * 判断HTTP的返回状态码，如果正确继续解析返回的数据
             */
            //HttpStatus.SC_OK=200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                returnStr = EntityUtils.toString(response.getEntity());
            } else {
                returnStr = "{\"code\":\"500\",\"msg\":\"请求地址异常(" + response.getStatusLine().getStatusCode() + ")\",\"content\":\"\",\"extendData\":\"\"}";
                logger.info(">>>>>>>httpClient Get 请求地址异常---》url:" + url);
            }
        } catch (ClientProtocolException e) {
            logger.error(">>>>>>>httpClient Get ClientProtocolException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"601\",\"msg\":\"请求地址异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Get ClientProtocolException 异常---》url:" + url, e);
        } catch (IOException e) {
            logger.error(">>>>>>>httpClient Get IOException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"602\",\"msg\":\"IO异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Get IOException 异常---》url:" + url, e);
        }
        return returnStr;
    }

    /**
     * @param url            请求的全路径 例如：http://localhost:8088/json.html
     * @param postParameters 使用post的方式请求的参数，此参数为一个map
     * @return 返回json字符串
     */
    public static String post(String url, Map<String, String> postParameters) {
        String returnStr = "";
        CloseableHttpClient httpClient = HxHttpClientPoolOld.getHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            /**
             * setConnectTimeout(20000)：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout(20000) 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的
             * setSocketTimeout(20000) 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(200000).setConnectionRequestTimeout(200000).setConnectTimeout(200000).build();
            httpPost.setConfig(requestConfig);
            /*
             * 解析传递过来的map参数，将参数解析为键值对(f=xxx)的格式放入到List
             */
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = postParameters.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, postParameters.get(key)));
            }
            // 此处设置请求参数的编码为 utf-8
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            HttpResponse response = httpClient.execute(httpPost);
            /*
             * 判断HTTP的返回状态码，如果正确继续解析返回的数据
             */
            //HttpStatus.SC_OK=200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                returnStr = EntityUtils.toString(response.getEntity());
            } else {
                returnStr = "{\"code\":\"500\",\"msg\":\"请求地址异常(" + response.getStatusLine().getStatusCode() + ")\",\"content\":\"\",\"extendData\":\"\"}";
                logger.info(">>>>>>>httpClient Post 请求地址异常---》url:" + url + ",data:" + JsonUtils.obj2json(postParameters));
            }
        } catch (ClientProtocolException e) {
            logger.error(">>>>>>>httpClient Post ClientProtocolException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"601\",\"msg\":\"请求地址异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Post ClientProtocolException 异常---》url:" + url + ",data:" + JsonUtils.obj2json(postParameters), e);
        } catch (IOException e) {
            logger.error(">>>>>>>httpClient Post IOException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"602\",\"msg\":\"IO异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Post IOException 异常---》url:" + url + ",data:" + JsonUtils.obj2json(postParameters), e);
        }
        return returnStr;
    }

    /**
     * @param url 请求的全路径 例如：http://localhost:8088/json.html
     * @param obj 使用post的方式请求的参数，此参数为一个Object,json串后直接post给接口
     * @return 返回json字符串
     */
    public static String postJson(String url, Object obj) {
        String returnStr = "";
        CloseableHttpClient httpClient = HxHttpClientPoolOld.getHttpClient();
        String jsonstr = JsonUtils.obj2json(obj);
        try {
            HttpPost httpPost = new HttpPost(url);
            /**
             * setConnectTimeout(20000)：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout(20000) 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的
             * setSocketTimeout(20000) 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(200000).setConnectionRequestTimeout(200000).setConnectTimeout(200000).build();
            httpPost.setConfig(requestConfig);
            /*
             * 对传递过来的对象进行post json
             */
            StringEntity se = new StringEntity(jsonstr, Consts.UTF_8);
            se.setContentType("application/json");
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            /*
             * 判断HTTP的返回状态码，如果正确继续解析返回的数据
             */
            //HttpStatus.SC_OK=200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                returnStr = EntityUtils.toString(response.getEntity());
            } else {
                returnStr = "{\"code\":\"500\",\"msg\":\"请求地址异常(" + response.getStatusLine().getStatusCode() + ")\",\"content\":\"\",\"extendData\":\"\"}";
                logger.info(">>>>>>>httpClient Post 请求地址异常---》url:" + url + ",data:" + jsonstr);
            }
        } catch (ClientProtocolException e) {
            logger.error(">>>>>>>httpClient Post ClientProtocolException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"601\",\"msg\":\"请求地址异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Post ClientProtocolException 异常---》url:" + url + ",data:" + jsonstr, e);
        } catch (IOException e) {
            logger.error(">>>>>>>httpClient Post IOException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"602\",\"msg\":\"IO异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Post IOException 异常---》url:" + url + ",data:" + jsonstr, e);
        }
        return returnStr;
    }

    /**
     * @param url 请求的全路径 例如：http://localhost:8088/json.html
     * @param obj 使用post的方式请求的参数，此参数为一个Object,json串后直接post给接口
     * @return 返回json字符串
     */
    public static String postJsonToken(String url, Object obj, String appId, String token) {
        String returnStr = "";
        CloseableHttpClient httpClient = HxHttpClientPoolOld.getHttpClient();
        String jsonstr = JsonUtils.obj2json(obj);
        try {
            HttpPost httpPost = new HttpPost(url);
            /**
             * setConnectTimeout(20000)：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout(20000) 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的
             * setSocketTimeout(20000) 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(200000).setConnectionRequestTimeout(200000).setConnectTimeout(200000).build();
            httpPost.setConfig(requestConfig);
            /*
             * 对传递过来的对象进行post json
             */
            StringEntity se = new StringEntity(jsonstr, Consts.UTF_8);
            se.setContentType("application/json");
            httpPost.setEntity(se);
            httpPost.setHeader("app_id", appId);
            httpPost.setHeader("auth_token", token);
            HttpResponse response = httpClient.execute(httpPost);
            /*
             * 判断HTTP的返回状态码，如果正确继续解析返回的数据
             */
            //HttpStatus.SC_OK=200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                returnStr = EntityUtils.toString(response.getEntity());
            } else {
                returnStr = "{\"code\":\"500\",\"msg\":\"请求地址异常(" + response.getStatusLine().getStatusCode() + ")\",\"content\":\"\",\"extendData\":\"\"}";
                logger.info(">>>>>>>httpClient Post 请求地址异常---》url:" + url + ",data:" + jsonstr);
            }
        } catch (ClientProtocolException e) {
            logger.error(">>>>>>>httpClient Post ClientProtocolException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"601\",\"msg\":\"请求地址异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Post ClientProtocolException 异常---》url:" + url + ",data:" + jsonstr, e);
        } catch (IOException e) {
            logger.error(">>>>>>>httpClient Post IOException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"602\",\"msg\":\"IO异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Post IOException 异常---》url:" + url + ",data:" + jsonstr, e);
        }
        return returnStr;
    }

    /**
     * 带参数的get请求
     * @param url
     * @param body 参数的json字符串
     * @return
     */
    public static String getJsonToken(String url,String body, String appId, String token) {
        String returnStr = "";
        CloseableHttpClient httpClient = HxHttpClientPoolOld.getHttpClient();
        try {
            HttpWithEntity httpGet = new HttpWithEntity("GET",url,body);
            httpGet.setHeader("app_id", appId);
            httpGet.setHeader("auth_token", token);
            /**
             * setConnectTimeout(20000)：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout(20000) 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的
             * setSocketTimeout(20000) 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(200000).setConnectionRequestTimeout(200000).setConnectTimeout(200000).build();
            httpGet.setConfig(requestConfig);
            HttpResponse response = httpClient.execute(httpGet);
            /*
             * 判断HTTP的返回状态码，如果正确继续解析返回的数据
             */
            //HttpStatus.SC_OK=200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                returnStr = EntityUtils.toString(response.getEntity());
            } else {
                returnStr = "{\"code\":\"500\",\"msg\":\"请求地址异常(" + response.getStatusLine().getStatusCode() + ")\",\"content\":\"\",\"extendData\":\"\"}";
                logger.info(">>>>>>>httpClient Get 请求地址异常---》url:" + url);
            }
        } catch (ClientProtocolException e) {
            logger.error(">>>>>>>httpClient Get ClientProtocolException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"601\",\"msg\":\"请求地址异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Get ClientProtocolException 异常---》url:" + url, e);
        } catch (IOException e) {
            logger.error(">>>>>>>httpClient Get IOException 异常---》" + e.getMessage(), e);
            returnStr = "{\"code\":\"602\",\"msg\":\"IO异常\",\"content\":\"\",\"extendData\":\"\"}";
            logger.info(">>>>>>>httpClient Get IOException 异常---》url:" + url, e);
        }
        return returnStr;
    }


    static class HttpWithEntity extends HttpEntityEnclosingRequestBase {
        private String methodType = "GET";

        @Override
        public String getMethod() {
            return methodType;
        }

        public HttpWithEntity(String methodType, String uri, String dataBody) {
            super();
            this.methodType = methodType;
            setURI(URI.create(uri));
            HttpEntity httpEntity = new StringEntity(dataBody, ContentType.APPLICATION_JSON);
            setEntity(httpEntity);
        }
    }

}
