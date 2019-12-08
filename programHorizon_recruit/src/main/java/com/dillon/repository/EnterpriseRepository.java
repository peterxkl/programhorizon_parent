package com.dillon.repository;

import com.dillon.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 2:06 PM
 */
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise,String>, JpaSpecificationExecutor<Enterprise> {
    List<Enterprise> findAllByIsHot(String isHot);
}
