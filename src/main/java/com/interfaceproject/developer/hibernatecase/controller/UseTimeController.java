package com.interfaceproject.developer.hibernatecase.controller;

import com.interfaceproject.developer.hibernatecase.entity.Time;
import com.interfaceproject.developer.hibernatecase.service.UseTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/time")
public class UseTimeController {

    /**
     *
     */
    @Autowired
    private UseTimeService useTimeService;

    @GetMapping("/all")
    public List<Time> list(){
        return useTimeService.all();
    }

    @GetMapping("/page")
    public Page<Time> page(int page, int rows){
        return useTimeService.page(page,rows);
    }

    @PostMapping
    public Time add(@RequestBody Time time){
        return useTimeService.add(time);

    }

    @PutMapping
    public Time update(@RequestBody Time time){
        return useTimeService.update(time);

    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id ){
        useTimeService.delete(id);
        return "ok";

    }



}
