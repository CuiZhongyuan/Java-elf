package com.javaelf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 此工具类的base64是基于JDK1.8实现的，jdk必须为1.8以上
 *
 * @author lgl
 */
public class Base64Util {
    public static final String UTF8 = "UTF-8";
    public static final String GBK = "GBK";
    private static Logger logger = LoggerFactory.getLogger(Base64Util.class);

    public Base64Util() {
    }

    public static String decodeBase64(String base64String) {
        return decodeBase64(base64String, UTF8);
    }

    public static String encodeBase64(String encodeData) {
        return encodeBase64(encodeData, UTF8);
    }

    /**
     * base64解密
     *
     * @param base64String
     * @param charset
     * @return
     */
    public static String decodeBase64(String base64String, String charset) {
        try {
            return new String(Base64.getDecoder().decode(base64String), charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
            return null;
        }
    }

    /**
     * base64加密
     *
     * @param encodeData
     * @param charset
     * @return
     */
    public static String encodeBase64(String encodeData, String charset) {
        try {
            return Base64.getEncoder().encodeToString(encodeData.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
            return null;
        }
    }

    /**
     * base64字符串转换成图片
     *
     * @param imgStr      base64字符串
     * @param imgFilePath 图片存放路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static boolean base64ToImage(String imgStr, String imgFilePath, String imgName) { // 对字节数组字符串进行Base64解码并生成图片
        // 图像数据为空
        if (imgStr == null || "".equals(imgStr)) {
            return false;
        }
        try {
            // Base64解码
            byte[] b = Base64.getDecoder().decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath + "/" + imgName + ".jpg");
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
