package com.interfaceproject.hibernatecase.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
*
*测试 --PO（Persistent Object）：持久化对象
*
*@author wz
*@date 2020-7-2 10:05:07
**/
@Setter
@Getter
@Entity
@Table(name = "d_user")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class User{
        private static final long serialVersionUID = 1L;
        /**
         * 主键
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        private Long id;
        /**
        * 
        */
        private String username;
        /**
        * 
        */
        private String password;
        /**
        * 
        */
        private String realname;
}