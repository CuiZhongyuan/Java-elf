package com.javaelf.utils;


import com.javaelf.dao.VariableSubstitutionDao;
import com.javaelf.entity.VariableSubstitution;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 变量名替换变量值工具类
 * 读取测试数据中的变量数据把变量名替换为变量值
 */
public class VariableUtil {
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
