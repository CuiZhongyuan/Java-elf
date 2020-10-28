package com.javaelf.entity;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
*被测url信息 --实体类校验
*
*@author czy
*@date 2020-5-31 13:43:37
**/
@StaticMetamodel( Logincase.class)
public class Logincase_ {
    public static volatile SingularAttribute<Logincase,Long> id;
    public static volatile SingularAttribute<Logincase,Integer> caseStatus;
    public static volatile SingularAttribute<Logincase,Long> urlid;
    public static volatile SingularAttribute<Logincase,String> name;
    public static volatile SingularAttribute<Logincase,String> pwd;
    public static volatile SingularAttribute<Logincase,String> expect;
    public static volatile SingularAttribute<Logincase,String> actual;
    public static volatile SingularAttribute<Logincase,String> urlpath;

}
