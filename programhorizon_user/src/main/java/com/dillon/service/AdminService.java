package com.dillon.service;

import com.dillon.pojo.Admin;
import com.dillon.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 5:44 PM
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IdWorker idWorker;

    /*
    注册管理用户
     */
    public void add(Admin admin) {
        admin.setId(idWorker.nextId() + "");
        String newPassword = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(newPassword);
        admin.setState("1");
        adminRepository.save(admin);
    }

    /*
    登录
     */
    public Admin findByLoginnameAndPassword(String loginname, String password) {
        Admin admin = adminRepository.findByLoginname(loginname);
        if (admin == null || !bCryptPasswordEncoder.matches(password, admin.getPassword())) {
            return null;
        } else {
            return admin;
        }
    }
}
