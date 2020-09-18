package com.interfaceproject.developer.hibernatecase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.interfaceproject.developer.hibernatecase.entity.User;
import org.springframework.stereotype.Repository;

/**
*
*测试 --DAO层
*
*@author wz
*@date 2020-7-2 10:05:07
**/
// JpaRepository<T, ID> //第一个参数为实体类类型，第二个为表主键，复合主键必须实现序列化
@Repository
public interface UserDao extends JpaRepository<User, Long> {

}
