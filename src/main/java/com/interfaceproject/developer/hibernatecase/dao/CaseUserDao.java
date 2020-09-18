package com.interfaceproject.developer.hibernatecase.dao;

import com.interfaceproject.developer.hibernatecase.entity.CaseID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseUserDao  extends JpaRepository<CaseID ,Long> {
}
