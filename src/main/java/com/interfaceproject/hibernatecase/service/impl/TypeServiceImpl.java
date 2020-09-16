package com.interfaceproject.hibernatecase.service.impl;

import com.interfaceproject.hibernatecase.dao.TypeDao;
import com.interfaceproject.hibernatecase.entity.TypeEntity;
import com.interfaceproject.hibernatecase.service.TypeServioce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeServioce {

    @Autowired
    TypeDao typeDao;


    public List<TypeEntity> all() {
        return typeDao.findAll();
    }


    public Page<TypeEntity> page(int pages, int rows) {
        PageRequest pageRequest = PageRequest.of((pages-1)*rows,rows);
        Page<TypeEntity> all = typeDao.findAll(pageRequest);
        return all;
    }


    public TypeEntity add(TypeEntity typeEntity) {
        TypeEntity save = typeDao.save(typeEntity);
        return save;
    }


    public TypeEntity update(TypeEntity typeEntity) {
        if (null != typeEntity.getId()){
            return typeDao.save(typeEntity);
        }
        return null;
    }

    public String delete(Long id) {
        typeDao.deleteById(id);
        return null;
    }

}
