package com.cucumber.api.util;

import com.cucumber.api.data.genereate.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataKit {

    /**
     * 根据接口参数约束类型生成边界值
     * @param params
     * @return
     * @throws IOException
     */
    public static Map<String, Object> ParamsData(String params) throws IOException {
//        String s="     Authorization : default =Bearer  , type = string , in =Authorization \n" +
//                "     deptId : default =null , type = string , in =deptId \n" +
//                "     deptName : default =null , type = string , in =deptName \n" +
//                "     eeNo : default =null , type = string , in =eeNo ";
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(params.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        String line;
        StringBuffer strbuf=new StringBuffer();
        Map<String, Object> tmp = new HashMap<>();
        while ( (line = br.readLine()) != null ) {
            if(!line.trim().equals("")){
                strbuf.append(line+"\r\n");
                System.out.println(line);
                //正则匹配　key  type  in
                String rgexSubType = "type = (.*?) ,";
                String rgexKey = "(.*?) :";
//                String rgexSubIn = "in =(.*?)";　//以换行结束
                String paramKey = TplKit.parseStringSubSimple(line,rgexKey);
                String paramType= TplKit.parseStringSubSimple(line,rgexSubType);
                int paramLength=10;
                Object paramKeyvalue= DataKit.GeneratorDataByTyPe(paramType,paramLength);
                tmp.put(paramKey,paramKeyvalue);
            }
        }
        return tmp;
    }

    /**
     * 根据接口参数约束类型生成随机数据 生成边界值数据
     * @param paramType
     * @param paramLength
     * @return
     */
    public static Object GeneratorDataByTyPe(String paramType,int paramLength ) {
        Map<String, Object> tmp = new HashMap<>();

        //case
        switch(paramType){
            case "bool":
                tmp.put(paramType, RandomGenerator.getRandomBoolean());
                break;
            case "int":
                tmp.put(paramType, NumberGenerator.randomInt(paramLength+1));
                break;
            case "date":
                tmp.put(paramType, DateGenerator.randomDataYMD());
                break;
            case "string":
                tmp.put(paramType, UserNameGenerator.userName(paramLength+1));
                break;
            default:
//                tmp.put("keyName","");
                break;
        }


        return tmp.get(paramType);
    }

    /**
     * 用户基本信息数据封装
     * @return
     */
    public static Map getBaseInfo() {
        Map infoMap = new HashMap<>();
        //生成身份证号码
        String ChinaIDCardNumber = ChinaIDCardUtil.generateChineseIDCard();
        //姓名
        String chineseName = UserNameGenerator.getRandomNameByChinese(3);
        infoMap.put("chineseName",chineseName);
        //根据身份证号获取性别
        String sex = ChinaIDCardUtil.getSex(ChinaIDCardNumber);
        infoMap.put("sex",sex);
        String nation = "汉";
        infoMap.put("nation",nation);

        //年龄
        int age = ChinaIDCardUtil.getPersonAgeFromIdCard(ChinaIDCardNumber);
        infoMap.put("age",age);
        //根据身份证获取生日
//        String birthday = ChinaIDCardUtil.extractYearMonthDayOfIdCard(ChinaIDCardNumber) ;
//        String[] birthdaySubStr = birthday.split("-");
//        infoMap.put("birthdaySubStr",birthdaySubStr);
        //身份证号码
        infoMap.put("ChinaIDCardNumber",ChinaIDCardNumber);

        //身份证有效期
        String chineseIDCardValidPeriod = ChinaIDCardUtil.getValidPeriodDate(ChinaIDCardNumber);
        infoMap.put("chineseIDCardValidPeriod",chineseIDCardValidPeriod);

        //身份证籍贯
        String IDCardAreaCodeAddr= IDCardAreaCode.generateIssueOrg(ChinaIDCardNumber,6);
        infoMap.put("IDCardAreaCodeAddr",IDCardAreaCodeAddr);

        //身份证地址
        String homeAddress = ChinaIDCardUtil.getIDAddressByID(ChinaIDCardNumber);
        infoMap.put("homeAddress",homeAddress);

        //账号信息
        infoMap.put("professionalName",UserNameGenerator.getCollageProfessional());

        //毕业时间
        infoMap.put("collageEndDate", ChinaIDCardUtil.getCollageEndDate(ChinaIDCardNumber,3));

        //毕业证编号
        infoMap.put("CollageNo",NumberGenerator.randomInt(100000000,999999999));

        infoMap.put("Phone", PhoneGenerator.getTel());

        infoMap.put("Password",NumberGenerator.generatePassword(8));

        infoMap.put("Email",EmailGenerator.getEmail(6,9));

        //银行卡号
        infoMap.put("BankNumber",BankNumberGenerate.bankNumberGenerate(BankNameEnum.ICBC, BankCardTypeEnum.DEBIT));

        infoMap.put("BankCode", BankNumberGenerate.bankCodeGenerate(BankNameEnum.SHB));

        infoMap.put("DescChinese",CharNumberGenerator.generateInRadomLengthJianHan(2,20));

        infoMap.put("Ip",CharNumberGenerator.generateInRadomLengthJianHan(2,20));

        infoMap.put("RandomNumber",NumberGenerator.randomInt(3));
//        COMPANY20200923010000000001
        infoMap.put("GenereateCompanyId","COMPANY"+"20200923"+NumberGenerator.randomInt(010000000001,010000010001));
        infoMap.put("GenereateAccountName",CharNumberGenerator.generateInRadomLengthJianHan(2,10));



        infoMap.put("Url",CharNumberGenerator.generateInRadomLengthJianHan(2,20));
        infoMap.put("Uuid", CharNumberGenerator.generateInRadomLengthJianHan(2,20));

        //batchNo   batchSourcetype + yyyyMMddHHmmss + random(3)
        infoMap.put("batchSourceType", BatchNoGenerator.generateBatchSourceType());
        infoMap.put("batchSenddate", BatchNoGenerator.generateCurrentDate());
        infoMap.put("batchNo", BatchNoGenerator.generateBatchNo());
        infoMap.put("batchMtCode", BatchNoGenerator.generateMtCode());
        infoMap.put("batchParams", BatchNoGenerator.generateBatchParams());
        infoMap.put("batchRangeUc", BatchNoGenerator.generateBatchRangeUc());
        infoMap.put("batchUcKey", BatchNoGenerator.generateUcKey());
        infoMap.put("corpName", UserNameGenerator.getRandomCompanyName());
        return infoMap;
    }




    public static String randomDataStringInspect(String dataString){
        String pattern = "Random\\(.+?\\)";
//        String str = "{name : 'test' , age: 'Random(Long[2])' , detail: 'Random(String[50,60])' , isStudent: 'Random(Boolean)' }";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(dataString);
        while (m.find()){
            String randomStr =  m.group();
            Object randomVal =  DataKit.ranDomData(randomStr);
            try {
                dataString = dataString.replace(randomStr,randomVal.toString());
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
        return dataString;
    }


    /**
     *
     * Random(String[0,10])
     * Random(Char[10)
     * Random(Long[10])
     * Random(Boolean)
     * Random(IDCard[19990909])
     * Random(Date[yyyy-MM-dd hh:mm:ss])
     * @param data
     * @return
     */
    public static Object ranDomData(String data){
        String pattern = "\\d+";
        Pattern r = Pattern.compile(pattern);
            if(data.contains("String")){
                Matcher m = r.matcher(data);
                List<Object> doorsill = new ArrayList<>();
                while (m.find()){
                   doorsill.add(Integer.valueOf(m.group()));
                }
                return dataGenreate("String",doorsill);
            }else if(data.contains("Char")){
                Matcher m = r.matcher(data);
                List<Object> doorsill = new ArrayList<>();
                while (m.find()){
                    doorsill.add(Integer.valueOf(m.group()));
                }
                return dataGenreate("Char",doorsill);
            }else if(data.contains("Long")){
                Matcher m = r.matcher(data);
                List<Object> doorsill = new ArrayList<>();
                while (m.find()){
                    doorsill.add(Integer.valueOf(m.group()));
                }
                return dataGenreate("Long",doorsill);
            }else if(data.contains("Boolean")){
               return dataGenreate("Boolean",null);
            }else if(data.contains("Date")){
                pattern = "(?<=\\[).*?(?=\\])";
                Pattern rDate = Pattern.compile(pattern);
                Matcher m = rDate.matcher(data);
                List<Object> doorsill = new ArrayList<>();
                while (m.find()){
                    doorsill.add(m.group());
                }
                return dataGenreate("Date",doorsill);
            } else if(data.contains("IDCard")){
                pattern = "(?<=\\[).*?(?=\\])";
                Pattern rDate = Pattern.compile(pattern);
                Matcher m = rDate.matcher(data);
                List<Object> doorsill = new ArrayList<>();
                while (m.find()){
                    doorsill.add(m.group());
                }
                return  doorsill.size()>0 ?  dataGenreate("IDCard",doorsill) : CardIDGenerator.IdNumber(null);
            } else{
                return dataGenreate("not",null);
            }
    }

    /**
     *
     * @param type  数据类型
     * @param doorsill  数据区间
     * @return
     */
    private static Object dataGenreate(String type,List<Object> doorsill){
        Object obj = null;
        if (doorsill !=null && doorsill.size() == 1) {
            doorsill.add(doorsill.get(0));
        }
        switch (type){
            case "String":
                obj = CharNumberGenerator.generateInRadomLengthJianHan(Integer.parseInt(doorsill.get(0).toString()),Integer.parseInt(doorsill.get(1).toString()));
                break;
            case "Char":
                obj = CharNumberGenerator.generateLetter(Integer.parseInt(doorsill.get(0).toString()),Integer.parseInt(doorsill.get(1).toString()));
                break;
            case "Long":
                obj = CharNumberGenerator.generateLong(Integer.parseInt(doorsill.get(0).toString()),Integer.parseInt(doorsill.get(1).toString()));
                break;
            case "Boolean":
                obj = RandomGenerator.getRandomBoolean();
                break;
            case "Date":
                obj = new SimpleDateFormat(doorsill.get(0).toString()).format(new Date());
                break;
            case "IDCard":
                obj = CardIDGenerator.IdNumber(doorsill.get(0).toString());
                break;
            default:
                obj = CharNumberGenerator.generateLetterAndInt(Integer.parseInt(doorsill.get(0).toString()));
                break;
        }
        return obj;
    }
}

