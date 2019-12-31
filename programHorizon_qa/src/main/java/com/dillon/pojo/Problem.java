package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/9/2019 8:51 AM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_problem")
@EntityListeners(AuditingEntityListener.class)
public class Problem {
    @Id
    private String id;
    private String title;  //标题
    private String content; //内容
    @CreatedDate
    private LocalDateTime createTime;  //创建日期
    @LastModifiedDate
    private LocalDateTime updateTime;  //更新日期
    private String userId;   //用户id
    private String nickname;  //昵称
    private Long visits;     //浏览数
    private Long thumbUp;    //点赞数
    private Long reply;     //回复数
    private String solve;   //是否解决
    private String replyName;  //回复人昵称
    private LocalDateTime replyTime;  //回复时间
}
