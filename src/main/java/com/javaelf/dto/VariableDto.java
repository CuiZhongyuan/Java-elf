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
    @Excel(name = "Name(变量名)",orderNum = "1",width = 40)
    private String name;
    @Excel(name = "Value(变量值)",orderNum = "2",width = 40)
    private String value;
    @Excel(name = "Remark(备注信息)",orderNum = "3",width = 40)
    private String remark;
}
