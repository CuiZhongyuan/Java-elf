package testcase;

import com.javaelf.utils.JsonUtils;
import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class RandomParameter {

    public static HashMap<String, Object> getStrParam(Integer leftBorder, Integer
            rightBorder, String paraName, String regular) {
        HashMap<String, Object> map = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        Random random = new Random();
        Generex generex = new Generex(regular);

        String empty = "";
        String spaceStr = " ";
        String leftStr = RandomStringUtils.randomAlphanumeric(leftBorder);
        String middleStr = RandomStringUtils.randomAlphanumeric(random.nextInt(rightBorder - leftBorder + 1) + leftBorder);
        String rightStr = RandomStringUtils.randomAlphanumeric(rightBorder);
        String moreStr = RandomStringUtils.randomAlphanumeric(rightBorder + 1);
        String regStr = generex.random();
        arrayList.add(empty);
        arrayList.add(spaceStr);
        arrayList.add(leftStr);
        arrayList.add(middleStr);
        arrayList.add(rightStr);
        arrayList.add(moreStr);
        arrayList.add(regStr);

        map.put(paraName, arrayList);
        map.put("middleStr", middleStr);
        return map;
    }

    public static HashMap<String, Object> getStrParam(Integer leftBorder, Integer
            rightBorder, String paraName) {
        HashMap<String, Object> map = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        Random random = new Random();

        String empty = "";
        String spaceStr = " ";
        String lessStr = null;
        String leftStr = null;
        if (leftBorder > 0) {
            lessStr = RandomStringUtils.randomAlphanumeric(leftBorder - 1);
        }
        if (leftBorder != 0) {
            leftStr = RandomStringUtils.randomAlphanumeric(leftBorder);
        }
        String middleStr = RandomStringUtils.randomAlphanumeric(random.nextInt(rightBorder - leftBorder + 1) + leftBorder);
        String rightStr = RandomStringUtils.randomAlphanumeric(rightBorder);
        String moreStr = RandomStringUtils.randomAlphanumeric(rightBorder + 1);

        arrayList.add(empty);
        arrayList.add(spaceStr);
        if (lessStr != null) {
            arrayList.add(lessStr);
        }
        if (leftStr != null) {
            arrayList.add(leftStr);
        }
        arrayList.add(middleStr);
        arrayList.add(rightStr);
        arrayList.add(moreStr);

        map.put(paraName, arrayList);
        map.put("middleStr", middleStr);
        return map;
    }

    /**
     * 组合测试参数
     *
     * @param hashMaps
     * @return
     */
    public static ArrayList<HashMap<String, Object>> getOrgData(HashMap<String, Object>... hashMaps) {
        //变参参数名
        String paraName = null;
        //finalArrayList存放排列组合结果
        ArrayList<HashMap<String, Object>> finalArrayList = new ArrayList();
        //保存传参hashMaps，目的获取参数个数，hashMaps存放paraName和middleStr
        ArrayList<HashMap<String, Object>> lengthArrayList = new ArrayList();
        for (HashMap<String, Object> hashMap : hashMaps) {
            lengthArrayList.add(hashMap);
        }
        if(lengthArrayList.size()>1){
            for (int i = 0; i < lengthArrayList.size(); i++) {
                //  ArrayList<HashMap<String, Object>> arrayList = new ArrayList();
                //获取所有传参中的第i个值，定义为变参changehashMap
                HashMap<String, Object> changehashMap = lengthArrayList.get(i);
                //获取changehashMap需要测试参数名称
                Set<String> keys = changehashMap.keySet();
                for (String key : keys) {
                    if (key != "middleStr") {
                        paraName = key;
                    }
                }
                //获取变参的changeArrayList,里面存放着需要测试的变化参数
                ArrayList<String> changeArrayList = (ArrayList<String>) changehashMap.get(paraName);
                //遍历变化参数，与middle（定值）组合
                for (int j = 0; j < changeArrayList.size(); j++) {
                    HashMap<String, Object> orgHashMap = new HashMap();
                    //定值取lengthArrayList中hashMaps的middle值
                    for (int k = 0; k < lengthArrayList.size(); k++) {
                        //变参与自己不能组合
                        if (i != k) {
                            //获取定值的map
                            HashMap<String, Object> otherHashMap = lengthArrayList.get(k);
                            String otherParaName = null;
                            //获取定值参数名
                            Set<String> otherkeys = otherHashMap.keySet();
                            for (String otherkey : otherkeys) {
                                if (otherkey != "middleStr") {
                                    otherParaName = otherkey;
                                }
                            }
                            //获取定值
                            Object middleStr = otherHashMap.get("middleStr");
                            //变值与定值组合map
                            orgHashMap.put(otherParaName, middleStr);
                            String value = changeArrayList.get(j);
                            orgHashMap.put(paraName, value);
                        }
                    }
                    finalArrayList.add(orgHashMap);
                }
            }
            return finalArrayList;
        }else {
            HashMap<String, Object> changehashMap = lengthArrayList.get(0);
            Set<String> keys = changehashMap.keySet();
            for (String key : keys) {
                if (key != "middleStr") {
                    paraName = key;
                }
            }
            //获取变参的changeArrayList,里面存放着需要测试的变化参数
            ArrayList<String> changeArrayList = (ArrayList<String>) changehashMap.get(paraName);
            for (int i = 0; i <changeArrayList.size() ; i++) {
                HashMap<String, Object> orgHashMap = new HashMap();
                orgHashMap.put(paraName,changeArrayList.get(i));
                finalArrayList.add(orgHashMap);
            }
            return finalArrayList;
        }
    }

    /**
     * 测试动态参数与静态参数结合
     *
     * @param hashMap
     * @return
     */
    public static ArrayList<String> requestBodyArrayList(ArrayList<HashMap<String, Object>> finalArrayList, HashMap<String, Object> hashMap) {
        ArrayList<String> arrayList = new ArrayList();

        for (int i = 0; i <finalArrayList.size() ; i++) {
            HashMap<String, Object>  objectHashMap = finalArrayList.get(i);
            objectHashMap.putAll(hashMap);
            String requestBody = JsonUtils.mapToJson(objectHashMap);
            arrayList.add(requestBody);
        }
        return arrayList;
    }


}
