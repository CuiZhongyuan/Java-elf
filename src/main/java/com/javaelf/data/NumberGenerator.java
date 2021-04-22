package com.javaelf.data;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 随机数生成器
 *
 * @author niaoshuai
 * @author shixing
 */
public class NumberGenerator {

    private static final String NUMBER_CHAR = "0123456789";

    /**
     * 生成随机数
     * @param length
     * @return
     */
    public static int randomInt(Integer length) {
        Random rm = new Random();
        if (length == 0 ){
            return 0;
        }else {
            int i = rm.nextInt(length);
            return i;
        }
    }

    /**
     * 获取指定范围随机数
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int  randomInt(int startIndex,int endIndex ) {
        Random rm = new Random();
        int i = rm.nextInt(endIndex+1-startIndex)+startIndex;
        return i;
    }


    /**
    * @Author zgq
    * @Description  生成有序的编号  0000 前缀长度
    * @Date 20-9-24 下午4:49
     * @param endIndex
    * @return java.lang.String
    **/
    public static String orderInt(int endIndex) {
        String result="";
        for (int i = 1; i <= endIndex; i++) {
            if (i < 10) {
                result = "000" + i;
                System.out.println(result);
            } else if (i >= 100) {
                result = "000" + i;
                System.out.println(result);
            } else if (i >= 1000) {
                result = "000" + i;
                System.out.println(result);
            } else if (i >= 10000) {
//                result = String(int);
                System.out.println(i);
            } else
                { System.out.println("0000" + i);
            }
        }
        return result ;
    }

    /**
    * @Author zgq 
    * @Description 生成规则设备编号:设备类型+五位编号（从1开始，不够前补0)
    * @Date 20-9-24 下午5:24
     * @param equipmentType
     * @param equipmentNo
    * @return java.lang.String
    **/
    public static String getNewEquipmentNo(String equipmentType, String equipmentNo){
        String newEquipmentNo = "";

        if(equipmentNo != null && !equipmentNo.isEmpty()){
            int newEquipment = Integer.parseInt(equipmentNo) + 1;
            newEquipmentNo = String.format(equipmentType + "%05d", newEquipment);
        }
        return newEquipmentNo;
    }



    public static String generateStringByLength(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));
        }
        return sb.toString();
    }

    public static final char[] CHARR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?-+=_".toCharArray();
    public static final String PASSWORD_REGEX = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_!@#$%^&*`~()-+=]+$)(?![0-9\\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_!@#$%^&*`~()-+=]{8,16}$";
    public static final String NO_CHINESE_REGEX = "^[^\\u4e00-\\u9fa5]+$";
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    public static final Pattern NO_CHINESE_PATTERN = Pattern.compile(NO_CHINESE_REGEX);
    /**
     * 生成密码
     * @param length
     * @return
     */
    public static String generatePassword(int length) {
        length = length < 6 ? 6 : length;
        length = length > 16 ? 16 : length;
        String result = getRandomPassword(length);
        Matcher m = PASSWORD_PATTERN.matcher(result);
        Matcher m1 = NO_CHINESE_PATTERN.matcher(result);
        if (m.matches() && m1.matches()) {
            return result;
        }
        return generatePassword(length);
    }

    public static String getRandomPassword(int length) {
        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(CHARR[r.nextInt(CHARR.length)]);
        }
        return sb.toString();
    }

}
