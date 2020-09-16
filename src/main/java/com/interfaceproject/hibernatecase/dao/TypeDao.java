package com.interfaceproject.hibernatecase.dao;

import com.interfaceproject.hibernatecase.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDao extends JpaRepository<TypeEntity,Long> {

}
