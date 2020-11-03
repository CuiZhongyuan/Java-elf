package com.javaelf.dao;

import com.javaelf.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginCaseDatasDao extends JpaRepository<TestCase,Long> {
}
