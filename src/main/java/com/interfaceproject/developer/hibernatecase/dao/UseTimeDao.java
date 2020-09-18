package com.interfaceproject.developer.hibernatecase.dao;

import com.interfaceproject.developer.hibernatecase.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UseTimeDao extends JpaRepository<Time , Long> {


}
