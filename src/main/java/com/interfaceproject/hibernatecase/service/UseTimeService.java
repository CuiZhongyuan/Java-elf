package com.interfaceproject.hibernatecase.service;

import com.interfaceproject.hibernatecase.entity.Time;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UseTimeService {

    List<Time> all();

    Page<Time> page(int page, int rows);

    Time add(Time time);

    Time update(Time time);

    String delete(Long id);
}
