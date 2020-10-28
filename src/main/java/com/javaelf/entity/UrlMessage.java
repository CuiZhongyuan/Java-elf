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
*URL访问路径 --PO（Persistent Object）：持久化对象
*
*@author wz
*@date 2020-5-31 13:43:37
**/
@Setter
@Getter
@Entity
@Table(name = "urlpath")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class UrlMessage {
        private static final long serialVersionUID = 1L;
        /**
         * url的id
         */
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @NotBlank(message = "id不能为空")
        @Id
        private Long id;
        /**
        * 请求类型：get/post/put/delete/update
        */
        @NotBlank(message = "URL不能为空")
        private String type ;
        /**
        * 请求地址address
        */
        @NotBlank(message = "url地址不能为空")
        private String address;

}