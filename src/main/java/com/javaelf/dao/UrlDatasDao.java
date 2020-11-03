package com.javaelf.dao;

import com.javaelf.entity.InterfaceMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlDatasDao extends JpaRepository<InterfaceMsg, Long> {
}
