package com.javaelf.entity;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
*被测url信息 --实体类校验
*
*@author czy
*@date 2020-5-31 13:43:37
**/
@StaticMetamodel(TestCase.class)
public class TestCase_ {
    public static volatile SingularAttribute<TestCase,Long> id;
    public static volatile SingularAttribute<TestCase,Integer> caseStatus;
    public static volatile SingularAttribute<TestCase,Long> interfacemsgId;
    public static volatile SingularAttribute<TestCase,String> headersParames;
    public static volatile SingularAttribute<TestCase,String> bodyParames;
    public static volatile SingularAttribute<TestCase,String> expect;
    public static volatile SingularAttribute<TestCase,String> actual;
    public static volatile SingularAttribute<TestCase,String> remark;

}
