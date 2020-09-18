package com.interfaceproject.developer.hibernatecase.service;

import com.interfaceproject.developer.hibernatecase.entity.CaseID;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseUserService {

    List<CaseID> all();

    Page<CaseID> page(int page, int rows);

    CaseID add(CaseID caseID);

    CaseID update(CaseID caseID);

    String delete(Long id);
}
