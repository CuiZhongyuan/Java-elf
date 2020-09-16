package com.interfaceproject.hibernatecase.dao;

import com.interfaceproject.hibernatecase.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UseTimeDao extends JpaRepository<Time , Long> {


}
