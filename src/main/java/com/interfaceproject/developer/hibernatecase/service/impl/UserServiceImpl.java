package com.interfaceproject.developer.hibernatecase.service.impl;

import com.interfaceproject.developer.hibernatecase.dao.UserDao;
import com.interfaceproject.developer.hibernatecase.entity.User;
import com.interfaceproject.developer.hibernatecase.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 查询所有
     * @return
     */
    public List<User> all(){
        return userDao.findAll();
    }

    public Page<User> page(int page,int rows){
        PageRequest pageRequest = PageRequest.of((page-1)*rows, rows);
        Page<User> all = userDao.findAll(pageRequest);
        return all;
    }

    public User add(User user){
        User save = userDao.save(user);
        return save;
    }

    public User update(User user){
        if (null != user.getId()){
            return userDao.save(user);
        }
        return null;
    }

    public String delete(Long id){

        userDao.deleteById(id);
        return null;
    }
}
