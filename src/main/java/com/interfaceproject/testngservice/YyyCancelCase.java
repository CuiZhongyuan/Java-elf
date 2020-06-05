package com.interfaceproject.testngservice;

import com.interfaceproject.utils.BaseTestngInit;
import com.interfaceproject.utils.JsonUtils;
import com.interfaceproject.utils.RestTemplateUtils;
import com.interfaceproject.utils.hxutils.DateUtils;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YyyCancelCase {


    @Test
    public void testrun(){
        readFile();;
        BaseTestngInit baseTestngInit =  new BaseTestngInit();

    }

    //读操作读取订单号
    public void readFile(){
        String filename = "G:\\testdata\\data.txt";
        String line = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String fileName="G:\\testdata\\response.txt";
            BufferedWriter out=new BufferedWriter(new FileWriter(fileName));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
//                读取参数进行核销接口并获取响应结果
                String text = ty(line);
                System.out.println(line+":核销返回:"+text);
//                响应结果写入文本
                writeFile(line+"核销返回："+text, out);
                writeFile("--------------------------------",out);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //获取响应参数写入文本
    public void writeFile(String text, BufferedWriter out) {
        try
        {
            out.write(text);
            //注意\n不一定在各种计算机上都能产生换行的效果
            out.newLine();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //核销接口
    public String ty(String pass){
        Map<String, Object> map = new HashMap<>();
        String time = DateUtils.getSystime();
        System.out.println(time);
        // 西游洞资源id——tengyunGardenId
//        map.put("resource_id","2c180c36-42c3-48fb-bbbc-12d162996535");
        map.put("resource_id", "bf299f31-8ce9-474f-9f6d-367cc1952807");
        // 大观园资源id——tengyunGardenId
//        map.put("resource_id", "47d8327c-c1f1-4442-a939-29d5f1613349");
//        map.put("resource_id", "47d8327c-c1f1-4442-a939-29d5f1613348");
        // 设备id——tengyunChannelId
//        map.put("device_id", "603879b7-33d6-4dec-77a3-245b1ca5e616");
        //1闸机, 2手持机 3小程序  为3时device_id应为null
        //黑龙潭2
//        map.put("resource_id","35e6fe2f-1867-4b71-9a5b-e13e945d75db");
        //黑龙潭1
//        map.put("resource_id","4d2569ba-2f19-43a1-bc2e-eb38d67f2fc2");
        map.put("source_type", 3);
        map.put("source_id", "18687201681");
        map.put("timestamp", time);
        map.put("nonce", "asdfasdfa");
        // 暂时不做签名校验
        map.put("sign", "13795e0dc80f0149f");
        Map<String, Object> mapc = new HashMap<>();
        //主单号
        mapc.put("pass_number", pass);
        // 当 人像扫描的时候 传 子订单号
        mapc.put("sub_order_id", "");
        //输入类型3--扫码枪 4--人像扫描
        mapc.put("input_type",3);
        //1--进 2--出
        mapc.put("direction", 1);
        mapc.put("check_quantity",1);
        map.put("content", mapc);
        String url = "http://127.0.0.1:8090/api/verification/check";

        ResponseEntity responseEntity = RestTemplateUtils.post(url,map,Object.class);
        String str = JsonUtils.obj2json(responseEntity.getBody());
        return JsonUtils.jsonFormatter(str);
    }


}
