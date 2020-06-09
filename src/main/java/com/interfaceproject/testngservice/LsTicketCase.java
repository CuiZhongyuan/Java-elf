package com.interfaceproject.testngservice;

import com.interfaceproject.utils.JsonUtils;
import com.interfaceproject.utils.hxutils.Base64Util;
import com.interfaceproject.utils.hxutils.CreateIDCardNo;
import com.interfaceproject.utils.hxutils.DateUtils;
import com.interfaceproject.utils.hxutils.HxHttpClientOld;
import org.apache.commons.codec.digest.DigestUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LsTicketCase {

    /**
     * 开发者ID
     */
    private static String appid="111";
    /**
     * 开发密钥
     */
    private static String secret="e715968f810947149db0db193cf21b93";

    private static String ip="http://219.146.72.74:19993/fsale";

    @Test
    public void testCase() throws Exception{
        Map<String, Object> order = test4();
        Thread.sleep(10000);
        test6((String)order.get("orderNo"), (List<String>)order.get("subOrderNoList"));
    }


    /**
     * 1.下单
     */
    public Map<String, Object> test4() throws Exception {
        //内层参数
        Map<String, Object> content = new HashMap<>();
        content.put("ticketCode", "T407960");
        content.put("resourceId", "");
        content.put("payType",2);
        content.put("bookingTime", "2020-06-09");
        content.put("playerNum", 3);
        content.put("contactName", "测试票");
        content.put("contactMobile", "15910451510");
        content.put("contactIdCard", "412702199102150019");
        content.put("threeOrderNo", new Long(System.currentTimeMillis()).toString());
        content.put("saleMoney", "0.01");
        content.put("appointmentId", "");
        List<String> playerIdCardList = new ArrayList<>();
        List<String> playerMobileList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            playerIdCardList.add(CreateIDCardNo.getRandomID());
            playerMobileList.add("15910451510");
        }
        content.put("playerIdCard", playerIdCardList);
        content.put("playerMobile", playerMobileList);
        //外层参数
        Map<String, Object> map = new HashMap<>();
        String timestamp = DateUtils.getSystime();
        map.put("timestamp", timestamp);
        String content1 = Base64Util.encodeBase64(JsonUtils.obj2json(content));
        map.put("content", content1);
        map.put("sign", DigestUtils.md5Hex(content1 + secret + timestamp));
        map.put("code", appid);
        map.put("extendData", "");
        //请求
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("reqdata", JsonUtils.obj2json(map));
        String str = HxHttpClientOld.post(ip+"/api/ota/order/create", map1);
        System.out.println(str);
        Map<String, Object> resultMap = JsonUtils.fromJsonStr(str);
        System.out.println(Base64Util.decodeBase64(resultMap.get("content").toString()));
        Map<String, Object> contentMap = JsonUtils.json2map(Base64Util.decodeBase64(resultMap.get("content").toString()));
        Map<String, Object> mainMap = JsonUtils.json2map(JsonUtils.obj2json(contentMap.get("mainInfo")));
        String baseOrderNo= (String) mainMap.get("baseOrderNo");

        List<Map<String, Object>> mapList = JsonUtils.json2list(JsonUtils.obj2json(mainMap.get("subOrderInfoVoList")), Map.class);
        List<String> subOrderNoList= new ArrayList<>();
        for (Map<String,Object> map2 : mapList){
            String subOrderNo = (String) map2.get("subOrderNo");
            subOrderNoList.add(subOrderNo);
        }
         //java8新特性for循环,对于遍历效率而言建议选择增强for
//        mapList.forEach(jsonMap -> {
//            String subOrderNo = (String) jsonMap.get("subOrderNo");
//            subOrderNoList.add(subOrderNo);
//        });

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("orderNo", baseOrderNo);
        resMap.put("subOrderNoList", subOrderNoList);
        return resMap;

    }

    /**
     * 2.退订
     */
    public void test6(String order,List<String> list) {
        //内层参数
        Map<String, Object> content = new HashMap<>();
        content.put("orderNo", order);
        content.put("suborderNos", list);

        //外层参数
        Map<String, Object> map = new HashMap<>();
        String timestamp = DateUtils.getSystime();
        map.put("timestamp", timestamp);
        String content1 = Base64Util.encodeBase64(JsonUtils.obj2json(content));
        map.put("content", content1);
        map.put("sign", DigestUtils.md5Hex(content1 + secret + timestamp));
        map.put("code", appid);
        map.put("extendData", "");
        //请求
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("reqdata", JsonUtils.obj2json(map));
        String str = HxHttpClientOld.post(ip+"/api/ota/order/unsubscribe", map1);
        System.out.println(str);
        Map<String, Object> resultMap = JsonUtils.fromJsonStr(str);
        System.out.println(Base64Util.decodeBase64(resultMap.get("content").toString()));
    }


}
