package com.javaelf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * stream流的测试类-学习stream流使用，不做其他用途
 * */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class TestStreamEntity {
    /**
     * 姓名
     * */
    public String name;
    /**
     * 性别
     * */
    public String sex;
    /**
     * 年龄
     * */
    public int age;
    /**
     * 年级
     * */
    public int grade;
}
