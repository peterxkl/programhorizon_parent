package com.dillon.repository;

import com.dillon.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/6/2019 11:42 AM
 */
public interface LabelRepository extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
