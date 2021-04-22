package com.javaelf.utils;

import com.javaelf.data.CardIDGenerator;
import com.javaelf.data.CharNumberGenerator;
import com.javaelf.data.RandomGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RandomDataKit {

    public static String randomDataStringInspect(String dataString){
        String pattern = "Random\\(.+?\\)";
//        String str = "{name : 'test' , age: 'Random(Long[2])' , detail: 'Random(String[50,60])' , isStudent: 'Random(Boolean)' }";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(dataString);
        while (m.find()){
            String randomStr =  m.group();
            Object randomVal =  RandomDataKit.ranDomData(randomStr);
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

