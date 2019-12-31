package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 11:12 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private String id;
    private String mobile;
    private String password;
    private String nickname;
    private String sex;
    private LocalDate birthday;
    private String avatar;
    private String email;
    private LocalDate regdate;    //注册日期
    private LocalDate updatedate;  //修改日期
    private LocalDate lastdate;   //最后登录日期
    private Integer online;    //在线时长
    private String interest;  //兴趣
    private String personality;    //个性
    private Integer fanscount;   //粉丝数
    private Integer followcount;  //关注数
}
