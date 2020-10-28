package com.javaelf.utils;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * httpclient
 *
 * @author lgl
 */
public class HxHttpClientPool {
    private static PoolingHttpClientConnectionManager cm;
    private static CloseableHttpClient httpClient = null;
    private static final String MY_HTTP = "http";
    private static final String MY_HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static SSLContextBuilder builder = null;
    private final static Object SYNC_LOCK = new Object();

    static {
        builder = new SSLContextBuilder();
        // 全部信任 不做身份鉴定
        try {
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(MY_HTTP, new PlainConnectionSocketFactory())
                    .register(MY_HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager();
            //连接池最大的连接数
            cm.setMaxTotal(1000);
            //默认的每个路由上最大的连接数（不能超过连接池总连接数）
            cm.setDefaultMaxPerRoute(200);
            //空闲永久连接检查间隔，这个牵扯的还比较多官方推荐使用这个来检查永久链接的可用性，而不推荐每次请求的时候才去检查（ms）
            cm.setValidateAfterInactivity(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            synchronized (SYNC_LOCK) {
                if (httpClient == null) {
                    httpClient = createHttpClient();
                }
            }
        }
        return httpClient;
    }

    public static CloseableHttpClient createHttpClient() {
        ConnectionKeepAliveStrategy myStrategy = (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator
                    (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase
                        (param)) {
                    return Long.parseLong(value) * 1000;
                }
            }
            //如果没有约定，则默认定义时长为60s
            return 60 * 1000;
        };
        //限制重试次数
        long limitCount = 3;
        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            // 如果已经重试了3次，就放弃
            if (executionCount >= limitCount) {
                return false;
            }
            // 如果服务器丢掉了连接，那么就重试
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            // 不要重试SSL握手异常
            if (exception instanceof SSLHandshakeException) {
                return false;
            }
            // 超时
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            // 目标服务器不可达
            if (exception instanceof UnknownHostException) {
                return false;
            }
            // SSL握手异常
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        return HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(myStrategy).setDefaultRequestConfig(globalConfig).setRetryHandler(httpRequestRetryHandler).build();
    }

}
