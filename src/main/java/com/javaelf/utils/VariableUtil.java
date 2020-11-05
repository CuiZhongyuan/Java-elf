package com.javaelf.utils;


import com.javaelf.dao.VariableSubstitutionDao;
import com.javaelf.dto.InterfaceMessageDto;
import com.javaelf.dto.TestCaseDto;
import com.javaelf.dto.VariableDto;
import com.javaelf.entity.VariableSubstitution;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 变量名替换变量值工具类
 * 读取测试数据中的变量数据把变量名替换为变量值
 */
public class VariableUtil {
    /*=========================================excel变量替换工具类====================================*/
    //创建各个对应sheet表的集合用来接收返回的数据，供外部调用
    public static List<InterfaceMessageDto> caseMessageList = new ArrayList<>();
    public static List<TestCaseDto> testCaseDtoList = new ArrayList<>();
    public static Map<String,String> variableNameAndValueMap = new HashMap<>();
    public static List<VariableDto> variablesList = new ArrayList<>();
    //使用静态代码块把excel数据读取存放到list中供后续实体
    static{
        String excelPath = (String) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.path");
        int sheet1Index = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.sheet1Index");
        int sheet2Index = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.sheet2Index");
        int sheet3Index = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.sheet3Index");
        int titleRows = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.titleRows");
        int headerRows = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.headerRows");
        //创建对应sheet1的接口信息集合
        List<InterfaceMessageDto> caseMessage = EasyPoiUtil.importExcel(excelPath, sheet1Index, titleRows, headerRows, InterfaceMessageDto.class);
        caseMessageList.addAll(caseMessage);
        //创建对应sheet2的测试用例覆盖
        List<TestCaseDto> testCaseDto = EasyPoiUtil.importExcel(excelPath, sheet2Index, titleRows, headerRows, TestCaseDto.class);
        //过滤excel读取最后多出一个为空的数据
        List<TestCaseDto> writeBackTestCaseList = testCaseDto.stream().filter(loginData -> loginData.getId() != null).collect(Collectors.toList());
        testCaseDtoList.addAll(writeBackTestCaseList);
        //创建对应sheet3的变量替换集合
        List<VariableDto> variableDtoList = EasyPoiUtil.importExcel(excelPath,sheet3Index , titleRows, headerRows, VariableDto.class);
        //过滤excel变量名为空的数据
        List<VariableDto> list = variableDtoList.stream().filter(var -> var.getName()!= null ).collect(Collectors.toList());
        variablesList.addAll(list);
        loadVariableToMap();
    }
    /**
     * 把读取的变量及变量值加载到map中
     */
    public static void loadVariableToMap(){
        for (VariableDto variable : variablesList){
            String variableName = variable.getName();
            String variableValue = variable.getValue();
            //如果variableValue为null或""判断
            if (variableValue ==null||variableValue.trim().length()==0){
                //获得要反射的类(得到反射类的全路径)
                String reflectCalss = variable.getReflectCalss();
                //要反射调用的方法
                String reflectMethod = variable.getReflectMethod();
                try {
                    //获取字节码
                    Class clzss = Class.forName(reflectCalss);
                    //字节码对象
                    Object object = clzss.newInstance();
                    //获取反射调用方法对象
                    Method method = clzss.getMethod(reflectMethod);
                    //反射调用方式，获取到方法的返回值
                    variableValue = (String) method.invoke(object);
                    variable.setReflectValue(variableValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            variableNameAndValueMap.put(variableName,variableValue);
        }
    }
    /**
     *  变量替换方法(把读取的变量名替换成变量值)
     * @param parameter ：需要替换的变量名
     */
    public static String replaceVariables(String parameter){
        //取出所有的变量名
        Set<String> variableNames =variableNameAndValueMap.keySet();
        if (parameter != null) {
            for (String variableName : variableNames) {
                //判断测试数据中出现了变量名就执行替换变量值的操作
                if (parameter.contains(variableName)) {
                    parameter = parameter.replace(variableName, variableNameAndValueMap.get(variableName));
                }
            }
        }
        return parameter;
    }

    /*=========================================变量替换后进行数据库操作回写====================================*/
    /**
     * 接收数待转换变量集合
     *
     * @return
     */
    public static Map<String, Object> loadVariableToMap(List<VariableSubstitution> list,VariableSubstitutionDao variableSubstitutionDao) {
        Map<String, Object> variablelistMap = new HashMap<>();
        for (VariableSubstitution variable : list) {
            String variableName = variable.getName();
            String variableValue = variable.getValue();
            //如果variableValue为null或""判断
            if (variableValue == null || variableValue.trim().length() == 0) {
                //获得要反射的类(得到反射类的全路径)
                String reflectCalss = variable.getReflectCalss();
                //要反射调用的方法
                String reflectMethod = variable.getReflectMethod();
                try {
                    //获取字节码
                    Class clzss = Class.forName(reflectCalss);
                    //字节码对象
                    Object object = clzss.newInstance();
                    //获取反射调用方法对象
                    Method method = clzss.getMethod(reflectMethod);
                    //反射调用方式，获取到方法的返回值
                    variableValue = (String) method.invoke(object);
                    variableSubstitutionDao.updateVariableValue(variableValue, variableName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            variablelistMap.put(variableName, variableValue);
        }
        return variablelistMap;
    }

    /**
     * 变量转换成变量值
     *
     * @param parameter ：需要替换的变量名
     */
    public static String replaceVariables(String parameter, List<VariableSubstitution> variableSubstitutionList,VariableSubstitutionDao variableSubstitutionDao) {
        Map<String, Object> variablelistMap = loadVariableToMap(variableSubstitutionList,variableSubstitutionDao);
        //取出所有的变量名
        Set<String> variableNames = variablelistMap.keySet();
        if (parameter != null) {
            for (String variableName : variableNames) {
                //判断测试数据中出现了变量名就执行替换变量值的操作
                if (parameter.contains(variableName)) {
                    parameter = parameter.replace(variableName, (CharSequence) variablelistMap.get(variableName));
                }
            }
        }
        return parameter;
    }
}
