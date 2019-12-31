package com.dillon.controller;

import com.dillon.pojo.Admin;
import com.dillon.service.AdminService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 5:43 PM
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    /*
    登录
     */
    @PostMapping
    public Result login(@RequestBody Map<String, String> loginMap) {
        Admin admin = adminService.findByLoginnameAndPassword(loginMap.get("loginname"), loginMap.get("password"));
        if (admin == null) {
            return Result.builder().flag(false).code(StatusCode.LOGINERROR).message("密码错误").data(null).build();
        } else {
            String token = jwtUtil.createJWT(admin.getId(), admin.getLoginname(), "admin");  //生成token
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("loginname", admin.getLoginname());
            return Result.builder().flag(true).code(StatusCode.OK).message("登录成功").data(map).build();
        }
    }
}
