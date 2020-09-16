package com.interfaceproject.hibernatecase.controller;

import com.interfaceproject.hibernatecase.entity.CaseID;
import com.interfaceproject.hibernatecase.service.CaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/case")
public class CaseUserResultController {

    @Autowired
    CaseUserService caseUserService;

    @GetMapping("/all")
    public List<CaseID> all(){
        return caseUserService.all();
    }

    @GetMapping("/page")
    public Page<CaseID> page(int page,int rows){
        return caseUserService.page(page,rows);
    }

    @PostMapping
    public CaseID add(@RequestBody CaseID caseID){
        return caseUserService.add(caseID);
    }

    @PutMapping
    public CaseID update(@RequestBody @Valid CaseID caseID, BindingResult result){
        if (result.hasErrors()){
            return null;
        }
        return caseUserService.update(caseID);
    }
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable  Long id){
        caseUserService.delete(id);
        return "ok";
    }


}
