package com.dillon.repository;

import com.dillon.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 5:44 PM
 */
public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findByLoginname(String name);
}
