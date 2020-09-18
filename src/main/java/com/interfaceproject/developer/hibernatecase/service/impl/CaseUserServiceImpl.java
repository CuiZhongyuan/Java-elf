package com.interfaceproject.developer.hibernatecase.service.impl;

import com.interfaceproject.developer.hibernatecase.dao.CaseUserDao;
import com.interfaceproject.developer.hibernatecase.entity.CaseID;
import com.interfaceproject.developer.hibernatecase.service.CaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CaseUserServiceImpl implements CaseUserService {
    @Autowired
    CaseUserDao caseUserDao;

    public List<CaseID> all() {
        return caseUserDao.findAll();
    }

    @Override
    public Page<CaseID> page(int page, int rows) {
        PageRequest pageRequest = PageRequest.of((page-1)*rows,rows);
        Page<CaseID> all = caseUserDao.findAll(pageRequest);
        return all;
    }


    public CaseID add(CaseID caseID) {
        CaseID save = caseUserDao.save(caseID);
        return save;
    }

    public CaseID update(CaseID caseID) {
        if (null !=caseID.getId()){
            return caseUserDao.save(caseID);
        }
        return null;
    }

    public String delete(Long id) {
        caseUserDao.deleteById(id);
       return null;
    }

}
