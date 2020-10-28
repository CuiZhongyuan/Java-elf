package com.javaelf.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * http同步请求工具类
 *
 * @author lgl
 */
public class HxHttpClient {
    private static Logger logger = LoggerFactory.getLogger(HxHttpClient.class);

    private static HxHttpClient instance;
    private static CloseableHttpClient closeableHttpClient = HxHttpClientPool.getHttpClient();

    public static HxHttpClient getInstance() {
        if (instance == null) {
            //添加同步锁，防止并发
            synchronized (HxHttpClient.class) {
                if (instance == null) {
                    instance = new HxHttpClient();
                }
            }
        }
        return instance;
    }

    private HxHttpClient() {
    }


    /**
     * 构建httpclient
     */
    private static RequestConfig requestConfig;
    private static HttpWithEntity http;
    private static HttpResponse response;
    private String url;
    private String data;
    private String methodType;

    static {
        /**
         * httpclient配置项
         */
        requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(200000)
                .setConnectionRequestTimeout(200000)
                .setConnectTimeout(200000)
                .build();
    }

    /**
     * http请求的基础化实例
     *
     * @param url
     * @param methodType
     * @param params
     * @return
     */
    public HxHttpClient config(String url, String methodType, Object params) {
        this.url = url;
        this.data = JsonUtils.obj2json(params);
        this.methodType = methodType;
        // 创建请求对象
        http = new HttpWithEntity(url, methodType, this.data);
        http.setConfig(requestConfig);
        return instance;
    }

    /**
     * http请求的header
     *
     * @param map
     * @return
     */
    public HxHttpClient header(Map<String, String> map) {
        // 设置header
        for (String s : map.keySet()) {
            http.setHeader(s, map.get(s));
        }
        return instance;
    }

    /**
     * 执行http请求
     *
     * @return
     */
    public HxHttpClientResponseData execute() {
        HxHttpClientResponseData hxHttpClientResponseData = HxHttpClientResponseSuccessData.success();
        try {
            response = closeableHttpClient.execute(http);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, Consts.UTF_8);
            //彻底关闭流操作。
//            EntityUtils.consume(entity);
            /*
             * 判断HTTP的返回状态码，如果正确继续解析返回的数据
             */
            //HttpStatus.SC_OK=200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                hxHttpClientResponseData.setContent(content);
                return hxHttpClientResponseData;
            } else {
                logger.info(">>>>>>>httpClient " + methodType + " 请求地址异常---》url:" + instance.url + ",data:" + instance.data);
                return HxHttpClientResponseData.error(500, "请求地址异常(" + response.getStatusLine().getStatusCode() + ")", "", "");
            }
        } catch (ClientProtocolException e) {
            logger.error(">>>>>>>httpClient " + methodType + " ClientProtocolException 异常---》" + e.getMessage(), e);
            return HxHttpClientResponseData.error(500, "请求地址异常(" + response.getStatusLine().getStatusCode() + ")", "", "");
        } catch (IOException e) {
            logger.error(">>>>>>>httpClient " + methodType + " IOException 异常---》" + e.getMessage(), e);
            return HxHttpClientResponseData.error(500, "请求地址异常(" + response.getStatusLine().getStatusCode() + ")", "", "");
        }
    }

    /**
     * 构建http携带参数的请求体
     */
    static class HttpWithEntity extends HttpEntityEnclosingRequestBase {
        private String methodType;

        @Override
        public String getMethod() {
            return methodType;
        }


        public HttpWithEntity(String uri, String methodType, String paramJsonStr) {
            super();
            this.methodType = methodType;
            setURI(URI.create(uri));
            HttpEntity httpEntity = new StringEntity(paramJsonStr, ContentType.APPLICATION_JSON);
            setEntity(httpEntity);
        }
    }


}
