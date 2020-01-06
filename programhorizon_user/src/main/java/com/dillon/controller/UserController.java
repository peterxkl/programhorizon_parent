package com.dillon.controller;

import com.dillon.pojo.User;
import com.dillon.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 11:19 AM
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("sendsms/{mobile}")
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return Result.builder().flag(true).code(StatusCode.OK).message("验证码发送成功").data(null).build();
    }

    /*
    登录
     */
    @PostMapping
    public Result login(@RequestBody Map<String, String> loginMap) {
        User user = userService.findByMobileAndPassword(loginMap.get("mobile"), loginMap.get("password"));
        if (user == null) {
            return Result.builder().flag(false).code(StatusCode.LOGINERROR).message("密码错误").data(null).build();
        } else {
            return Result.builder().flag(true).code(StatusCode.OK).message("登录成功").data(null).build();
        }
    }

    /*
    根据id删除指定用户，不过需要具备admin身份
    前后端约定：前端请求微服务时需要添加头信息Authorization ,内容为Bearer+空格+token
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        userService.deleteById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("删除成功").data(null).build();
    }

    @PutMapping("/insfans/{id}/{number}")
    public void updateFanscount(@PathVariable String id, @PathVariable int number) {
        userService.updateFanscount(id, number);
    }

    @PutMapping("/insfollow/{id}/{number}")
    public void updateFollowcount(@PathVariable String id, @PathVariable int number) {
        userService.updateFollowcount(id, number);
    }

}
