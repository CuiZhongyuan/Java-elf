package com.javaelf.dataForger;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.util.Locale;


/**
 * 数据工厂伪造者，可以提供测试数据工厂数据：常用的姓名、电话号码、地址、随机数等
 */
@Component
public class TestDataFactory {

    /**
     * 企业微信测试数据工厂
     */
    //企业ID-正确
    public String getCorpidTrue(){
        String corpid = "ww27d6f876d80ceec6";
        return corpid;
    }

    //应用的凭证密钥-正确
    public String getCorpsecretTrue(){
        String corpsecret = "elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw8";
        return corpsecret;
    }
    //企业ID-错误
    public String getCorpidFalse(){
        String corpid = "ww27d6f876d80ceec";
        return corpid;
    }

    //应用的凭证密钥-错误
    public String getCorpsecretFalse(){
        String corpsecret = "elaiRGcWe4JLESzypgnabA3Tvpj8kLiV6zLEqa4Nbw";
        return corpsecret;
    }
    /***
     * 初始Faker对象，并使用CHINA信息
     */
    Faker faker = new Faker(new Locale("zh-CN"));
    public String getName(){
        String name = faker.phoneNumber().cellPhone();
        return name;
    }
    @Test
    public void tt(){

        System.out.println(getName());
    }



}
