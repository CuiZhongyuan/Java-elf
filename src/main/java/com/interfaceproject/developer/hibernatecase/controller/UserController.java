package com.interfaceproject.developer.hibernatecase.controller;

import com.interfaceproject.developer.hibernatecase.entity.User;
import com.interfaceproject.developer.hibernatecase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> list(){
        return userService.all();
    }

    @GetMapping("/page")
    public Page<User> page(int page,int rows){
        return userService.page(page,rows);
    }

    @PostMapping
    public User add(@RequestBody  User user){
        return userService.add(user);
    }

    @PutMapping
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id){
            userService.delete(id);
            return "ok";
    }
}
