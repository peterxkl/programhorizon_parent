package com.dillon.service;

import com.dillon.config.RabbitConfig;
import com.dillon.pojo.User;
import com.dillon.repository.UserRepository;
import enums.ExceptionEnums;
import exception.PhException;
import io.jsonwebtoken.Claims;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 11:20 AM
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest httpRequest;

    @Autowired
    private JwtUtil jwtUtil;

    public void sendSms(String mobile) {
        int code = (int)((Math.random()*9+1)*100000); //随机生成的六位数
        //第一步：将生成的随机短信存入redis中
        redisTemplate.opsForValue().set("smscode_"+mobile, code, 5, TimeUnit.MINUTES);   //5分钟后过期

        //将六位随机数利用rabbitmq发送出去
        HashMap<String,String> information = new HashMap<>();
        information.put("mobile", mobile);
        information.put("code", code+"");
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_B, null, information);
    }

    /*
    注册用户
     */
    public void add(User user) {
        user.setId(idWorker.nextId()+"");
        String newPassword = encoder.encode(user.getPassword());
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    /*
    登录
     */
    public User findByMobileAndPassword(String mobile, String password) {
        User user = userRepository.findByMobile(mobile);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            return null;
        } else {
            return user;
        }
    }

    /*
    根据id删除用户
     */
    public void deleteById(String id) {
        String authorization = httpRequest.getHeader("Authorization");
        if (authorization == null) { //没有传Authorization头信息
            throw  new PhException(ExceptionEnums.INSUFFICIENT_PERMISSION);
        }
        if (!authorization.startsWith("Bearer ")) { //Authorization头信息格式错误
            throw  new PhException(ExceptionEnums.INSUFFICIENT_PERMISSION);
        }
        Claims claims = jwtUtil.parseJWT(authorization.substring(7));
        if (claims == null) {  //所传token错误
            throw  new PhException(ExceptionEnums.INSUFFICIENT_PERMISSION);
        }
        if (!"admin".equals(claims.get("roles"))) { //角色错误
            throw  new PhException(ExceptionEnums.INSUFFICIENT_PERMISSION);
        }
        userRepository.deleteById(id);
    }

    /*
    变更粉丝数(可能+1、也可能-1)
     */
    public void updateFanscount(String userId, int number) {
        userRepository.updateFanscount(userId, number);
    }

    /*
    更新关注数
     */
    public void updateFollowcount(String userId, int number) {
        userRepository.updateFollowcount(userId, number);
    }
}
