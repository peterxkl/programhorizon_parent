package com.dillon.repository;

import com.dillon.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 11:20 AM
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByMobile(String mobile);
}
