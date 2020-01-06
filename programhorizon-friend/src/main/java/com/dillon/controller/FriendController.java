package com.dillon.controller;

import com.dillon.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/4/2020 7:34 PM
 */
@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest http;

    /*
    添加好友或者添加非好友
    type：1 喜欢
    type: 2 不喜欢
     */
    @PutMapping("/like/{friendid}/{type}")
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        Claims user_claims = (Claims)http.getAttribute("user_claims");
        if (user_claims == null) {
            return Result.builder().flag(false).code(StatusCode.ACCESSERROR).message("无权访问").build();
        }
        //喜欢
        if (type.equals("1")) {
            if (friendService.addFriend(user_claims.getId(), friendid) == 0) {
                return Result.builder().flag(false).code(StatusCode.ERROR).message("已经添加此好友").build();
            } else {
                return Result.builder().flag(true).code(StatusCode.OK).message("添加成功").build();
            }
        } else {//不喜欢
            friendService.addNotFriend(user_claims.getId(), friendid);
        }
        return Result.builder().flag(true).code(StatusCode.OK).message("操作成功").build();
    }

    /*
    删除好友
     */
    @DeleteMapping("/{friendid}")
    public Result deleteFriend(@PathVariable String friendid) {
        Claims user_claims = (Claims)http.getAttribute("user_claims");
        if (user_claims == null) {
            return Result.builder().flag(false).code(StatusCode.ACCESSERROR).message("无权访问").build();
        }
        friendService.deleteFriend(user_claims.getId(), friendid);
        return Result.builder().flag(true).code(StatusCode.OK).message("操作成功").build();
    }
}
