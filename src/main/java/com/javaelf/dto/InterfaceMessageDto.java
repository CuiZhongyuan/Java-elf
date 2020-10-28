package com.javaelf.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InterfaceMessageDto {
    /***
     * 数据传输对象，excel封装成数据对象
     * 被测接口信息表头对应-- @Excel注解的name名称
     */
    @Excel(name = "ApiId(被测接口编号)",orderNum = "1",width = 20)
    private String apiId;
    @Excel(name = "ApiName(被测接口名称)",orderNum = "2",width = 20)
    private String apiName;
    @Excel(name = "Type(接口提交方式)",orderNum = "3",width = 20)
    private String type;
    @Excel(name = "Url(被测接口地址)",orderNum = "4",width = 20)
    private String url;
}
