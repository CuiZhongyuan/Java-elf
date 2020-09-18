package com.interfaceproject.developer.hibernatecase.service;


import com.interfaceproject.developer.hibernatecase.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * 获取全部记录
     *
     * @return
     */
    List<User> all();

    /**
     * 获取不分页数据，有查询条件，有排序
     *
     * @return
     */
    Page<User> page(int page, int rows);

    /**
     * 添加数据
     *
     * @param user
     * @return
     */
    User add(User user);

    /**
     * 更新数据
     *
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 删除数据，根据id做删除
     *
     * @param id
     */
    String delete(Long id);
}
