package com.javaelf.dao;

import com.javaelf.entity.VariableSubstitution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableDao extends JpaRepository<VariableSubstitution,Long> {
}
