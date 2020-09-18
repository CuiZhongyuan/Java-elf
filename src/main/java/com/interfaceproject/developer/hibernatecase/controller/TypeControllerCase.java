package com.interfaceproject.developer.hibernatecase.controller;


import com.interfaceproject.developer.hibernatecase.entity.TypeEntity;
import com.interfaceproject.developer.hibernatecase.service.TypeServioce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/type",headers = {"token=123"})
public class TypeControllerCase {

    @Autowired
    TypeServioce typeServioce;

    @GetMapping(value = "/all")
    public List<TypeEntity> all(){
        return typeServioce.all();
    }

    @GetMapping(value = "/page")
    public Page<TypeEntity> page(int pages, int rows){
        return typeServioce.page(pages,rows);
    }

    @PostMapping
    public TypeEntity add(@RequestBody TypeEntity typeEntity){
        return typeServioce.add(typeEntity);
    }

    @PutMapping
    public TypeEntity update(@RequestBody TypeEntity typeEntity){
        return typeServioce.update(typeEntity);
    }
    @DeleteMapping(value = "/{id}")
    public String delete(@RequestBody Long id){
        typeServioce.delete(id);
        return "ok";
    }
}
