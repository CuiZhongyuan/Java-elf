package com.javaelf.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
*被测url信息 --实体类校验
*
*@author czy
*@date 2020-5-31 13:43:37
**/
@StaticMetamodel( UrlMessage.class)
public class UrlMessage_ {
    public static volatile SingularAttribute<UrlMessage,Long> id;
    public static volatile SingularAttribute<UrlMessage,String> type;
    public static volatile SingularAttribute<UrlMessage,String> address;
}
