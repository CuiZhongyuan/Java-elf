package com.javaelf.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 创建身份证号
 * 18位身份证号码各位的含义:
 * // 1-2位省、自治区、直辖市代码；
 * // 3-4位地级市、盟、自治州代码；
 * // 5-6位县、县级市、区代码；
 * // 7-14位出生年月日，比如19670401代表1967年4月1日；
 * // 15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；
 * // 18位为校验码，0-9和X。
 * // 作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，
 * // 计算的结果是0-10，如果某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替，
 * // 因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10
 *
 * @author kdc
 */
public class CreateIDCardNo {
    /**
     * 每位加权因子
     */
    private static int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
            8, 4, 2};

    /**
     * 随机生成身份证号码
     *
     * @return
     */
    public static String getRandomID() {
        String id = "420222199204179999";
        // 随机生成省、自治区、直辖市代码 1-2
        String[] provinces = {"11", "12", "13", "14", "15", "21", "22", "23",
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82"};
        String province = randomOne(provinces);
        // 随机生成地级市、盟、自治州代码 3-4
        String city = randomCityCode(18);
        // 随机生成县、县级市、区代码 5-6
        String county = randomCityCode(28);
        // 随机生成出生年月 7-14
        String birth = randomBirth(20, 50);
        // 随机生成顺序号 15-17(随机性别)
        String no = new Random().nextInt(899) + 100 + "";
//        // 随机生成校验码 18
//        String checks[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
//                "X" };
//        String check = randomOne(checks);
        // 拼接身份证号码17位
        String id17 = province + city + county + birth + no;
        char[] c = id17.toCharArray();
        int[] bit = converCharToInt(c);
        int sum17 = 0;
        sum17 = getPowerSum(bit);
        // 将和值与11取模得到余数进行校验码判断
        String checkCode = getCheckCodeBySum(sum17);
        id = id17 + checkCode;
        return id;
    }

    /**
     * 从String[] 数组中随机取出其中一个String字符串
     *
     * @param s
     * @return
     * @author mingzijian
     */
    public static String randomOne(String[] s) {
        return s[new Random().nextInt(s.length - 1)];
    }

    /**
     * 随机生成两位数的字符串（01-max）,不足两位的前面补0
     *
     * @param max
     * @return
     * @author mingzijian
     */
    public static String randomCityCode(int max) {
        int i = new Random().nextInt(max) + 1;
        return i > 9 ? i + "" : "0" + i;
    }

    /**
     * 随机生成minAge到maxAge年龄段的人的生日日期
     *
     * @param minAge
     * @param maxAge
     * @return
     * @author mingzijian
     */
    public static String randomBirth(int minAge, int maxAge) {
        // 设置日期格式
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        Calendar date = Calendar.getInstance();
        // 设置当前日期
        date.setTime(new Date());
        // 随机设置日期为前maxAge年到前minAge年的任意一天
        int randomDay = 365 * minAge
                + new Random().nextInt(365 * (maxAge - minAge));
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);
        return dft.format(date.getTime());
    }

    private static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    private static int getPowerSum(int[] bit) {
        int sum = 0;
        if (power.length != bit.length) {
            return sum;
        }
        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
            default:
                checkCode = "1";
                break;
        }
        return checkCode;
    }
}
