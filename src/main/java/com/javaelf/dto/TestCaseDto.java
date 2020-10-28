package com.javaelf.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TestCaseDto {
    /**
     * 测试用例表头名称对应--@Excel注解的name名称
     */
    @Excel(name = "CaseId(用例编号)",orderNum = "1",width = 20)
    private String id;
    @Excel(name = "Desc(用例描述)",orderNum = "2",width = 20)
    private String desc;
    @Excel(name = "ApiId(接口编号ID)",orderNum = "3",width = 40)
    private String apiId;
    @Excel(name = "RelevanceFlag(是否有接口关联，0未关联|1关联)",orderNum = "4",width = 20)
    private String relevanceFlag;
    @Excel(name = "Headers(请求头参数)",orderNum = "5",width = 20)
    private String headers;
    @Excel(name = "Params(请求body参数)",orderNum = "6",width = 40)
    private String params;
    @Excel(name = "Expected(期望结果)",orderNum = "7",width = 20)
    private String expected;
    @Excel(name = "Actual(实际响应结果)",orderNum = "8",width = 40)
    private String actual;
    @Excel(name = "Note(备注信息)",orderNum = "9",width = 40)
    private String note;
}
