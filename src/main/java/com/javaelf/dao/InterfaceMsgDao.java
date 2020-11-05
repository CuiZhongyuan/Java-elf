package com.javaelf.dao;

import com.javaelf.entity.InterfaceMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceMsgDao extends JpaRepository<InterfaceMsg, Long> {
}
