package com.interfaceproject.developer.hibernatecase.service;

import com.interfaceproject.developer.hibernatecase.entity.TypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeServioce {

    List<TypeEntity> all();

    Page<TypeEntity> page(int pages, int rows);

    TypeEntity add(TypeEntity typeEntity);

    TypeEntity update(TypeEntity typeEntity);

    String delete(Long id);
}
