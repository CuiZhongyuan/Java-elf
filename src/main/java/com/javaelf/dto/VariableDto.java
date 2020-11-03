package com.javaelf.dto;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class VariableDto {
    /**
     * 变量表头名称对应--@Excel注解的name名称
     */
    @Excel(name = "id(变量id)",orderNum = "1",width = 10)
    private String id;
    @Excel(name = "Name(变量名)",orderNum = "2",width = 40)
    private String name;
    @Excel(name = "Value(变量值)",orderNum = "3",width = 40)
    private String value;
    @Excel(name = "ReflectCalss(反射类)",orderNum = "4",width = 40)
    private String reflectCalss;
    @Excel(name = "ReflectMethod(反射方法)",orderNum = "5",width = 40)
    private String reflectMethod;
    @Excel(name = "reflectValue(反射实际得到的值)",orderNum = "6",width = 40)
    private String reflectValue;
    @Excel(name = "Remark(备注信息)",orderNum = "7",width = 40)
    private String remark;
}
