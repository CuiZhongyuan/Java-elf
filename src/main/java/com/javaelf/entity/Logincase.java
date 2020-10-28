package com.javaelf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
*
*测试用例 --PO（Persistent Object）：持久化对象
*
*@author wz
*@date 2020-5-31 13:43:37
**/
@Setter
@Getter
@Entity
@Table(name = "logincase")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class Logincase {
        private static final long serialVersionUID = 1L;
        /**
         * url的id
         */
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Id
        private Long id;
        /**
         * 绑定状态 0 正向测试 1 反向测试
         */
        private Integer caseStatus = 1;
        /**
        * 请求地址address
        */
        @NotBlank(message = "urlid不能为空")
        private Long urlId ;
        /**
         * 登录账号
         */
        private String name;
        /**
         * 登录密码
         */
        private String pwd;
        /**
         * 登录密码
         */
        private String expect;
        /**
         * 登录密码
         */
        private String actual;
        /**
         * 登录密码
         */
        private String urlPath;

}