package com.dillon.controller;

import com.dillon.pojo.Admin;
import com.dillon.service.AdminService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Slf4j
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    /*
    登录
     */
    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> loginMap) {
        log.info("name is {}, password is {}", loginMap.get("username"), loginMap.get("password"));
        Admin admin = adminService.findByLoginnameAndPassword(loginMap.get("username"), loginMap.get("password"));
        if (admin == null) {
            return Result.builder().flag(false).code(StatusCode.LOGINERROR).message("密码错误").data(null).build();
        } else {
            String token = jwtUtil.createJWT(admin.getId(), admin.getLoginname(), "admin");  //生成token
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("name", admin.getLoginname());
            map.put("roles", "admin");
            return Result.builder().flag(true).code(StatusCode.OK).message("登录成功").data(map).build();
        }
    }

    /*
    添加管理员用户
     */
    @PostMapping("add")
    public Result add(@RequestBody Admin admin) {
        log.info("admin is {}", admin);
        adminService.add(admin);
        return Result.builder().flag(true).code(StatusCode.OK).message("添加成功").data(null).build();
    }


    /*
    根据token获取用户基本信息
     */
    @GetMapping("info")
    public Result getInfo(@RequestParam String token) {
        Claims claims = jwtUtil.parseJWT(token);
        Map<String, String> map = new HashMap<>();
        map.put("name", claims.getSubject());
        map.put("roles", (String) claims.get("roles"));
        return Result.builder().flag(true).code(StatusCode.OK).message("成功").data(map).build();
    }
}
