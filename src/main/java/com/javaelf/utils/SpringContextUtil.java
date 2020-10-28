package com.javaelf.utils;

import org.springframework.context.ApplicationContext;

/**
 * spring上下文
 *
 * @author yss
 * @date 2020-07-03
 */
public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    /**
     * 获取上下文
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 设置上下文
     *
     * @param applicationContext
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        com.javaelf.utils.SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 通过名字获取上下文中的bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    /**
     * 通过类型获取上下文中的bean
     *
     * @param requiredType
     * @return
     */
    public static Object getBean(Class<?> requiredType){
        return applicationContext.getBean(requiredType);
    }
}