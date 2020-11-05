package com.javaelf.dao;

import com.javaelf.entity.VariableSubstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VariableSubstitutionDao extends JpaRepository<VariableSubstitution,Long> {
    @Transactional
    @Modifying
    @Query( "UPDATE VariableSubstitution SET reflectValue = :reflectValue WHERE name = :variableName" )
    void updateVariableValue(@Param( "reflectValue" ) String reflectValue, @Param( "variableName" ) String variableName);
}
