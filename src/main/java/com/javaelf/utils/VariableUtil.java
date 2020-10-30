package com.javaelf.utils;


import com.javaelf.dto.VariableDto;
import java.util.*;

/**
 * 变量名替换变量值工具类
 * 读取测试数据中的变量数据把变量名替换为变量值
 */
public class VariableUtil {

    public static Map<String,String> variableNameAndValueMap = new HashMap<>();
    public static List<VariableDto> variables = new ArrayList<>();
    static{
        String excelPath = (String) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.path");
        int sheetIndex = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.sheetIndex");
        int titleRows = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.titleRows");
        int headerRows = (int) LoadStaticConfigUtil.getCommonYml( "testcaseexcel.headerRows");
        //创建对应sheet表的list集合容器接收读取测试用例数据
        List<VariableDto>  list = EasyPoiUtil.importExcel(excelPath,sheetIndex , titleRows, headerRows, VariableDto.class);
        variables.addAll(list);
        loadVariableToMap();
    }
    /**
     * 把读取的变量及变量值加载到map中
     */
    public static void loadVariableToMap(){
        for (VariableDto variable : variables){
            String variableName = variable.getName();
            String variableValue = variable.getValue();
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
}
