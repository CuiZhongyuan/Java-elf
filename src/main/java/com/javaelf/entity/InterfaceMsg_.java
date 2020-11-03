package com.javaelf.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
*被测url信息 --实体类校验
*
*@author czy
*@date 2020-5-31 13:43:37
**/
@StaticMetamodel(InterfaceMsg.class)
public class InterfaceMsg_ {
    public static volatile SingularAttribute<InterfaceMsg,Long> id;
    public static volatile SingularAttribute<InterfaceMsg,String> requestType;
    public static volatile SingularAttribute<InterfaceMsg,String> urlAddress;
}
