package com.dillon.service;

import com.dillon.pojo.Enterprise;
import com.dillon.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 2:08 PM
 */
@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public List<Enterprise> getHotList() {
        return enterpriseRepository.findAllByIsHot("1");
    }
}
