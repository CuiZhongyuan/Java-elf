package com.interfaceproject.hibernatecase.service.impl;

import com.interfaceproject.hibernatecase.dao.UseTimeDao;
import com.interfaceproject.hibernatecase.entity.Time;
import com.interfaceproject.hibernatecase.service.UseTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UseTimeServiceImpl  implements UseTimeService {

    @Autowired
    private UseTimeDao useTimeDao;

    public List<Time> all() {
        return useTimeDao.findAll();
    }


    public Page<Time> page(int page, int rows) {
        PageRequest pageRequest = PageRequest.of((page - 1) * rows,rows);
        Page<Time> all = useTimeDao.findAll(pageRequest);
        return all;
    }


    public Time add(Time time) {
        return useTimeDao.save(time);
    }


    public Time update(Time time) {
        return useTimeDao.save(time);
    }

    public String delete(Long id) {
        useTimeDao.deleteById(id);
        return null;
    }

}
