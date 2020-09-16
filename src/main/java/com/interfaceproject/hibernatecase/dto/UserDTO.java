package com.interfaceproject.hibernatecase.dto;
import lombok.Getter;
import lombok.Setter;

import java.lang.String;
import java.lang.String;
import java.lang.String;

/**
*测试 -- DTO（Data Transfer Object） 数据传输对象
*
*@author wz
*@date 2020-7-2 10:05:07
**/
@Setter
@Getter
public class UserDTO {
        private Long id;
        private String username;
        private String password;
        private String realname;

}