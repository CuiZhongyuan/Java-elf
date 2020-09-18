package com.interfaceproject.developer.hibernatecase.dao;

import com.interfaceproject.developer.hibernatecase.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDao extends JpaRepository<TypeEntity,Long> {

}
