package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 10:27 AM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_article")
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {  //注意：如果要实现缓存，必须将实体类序列化
    @Id
    private String id;
    private String columnId;   //专栏id
    private String userId;
    private String title;
    private String content;
    private String image;    //文章封面
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String isPublic;   //是否公开
    private String isTop;     //是否置顶
    private int visits;      //浏览量
    private int thumbup;
    private int comment;    //评论数
    private String state;   //审核状态  0：未审核   1：已审核
    private int channelId;  //所属频道
    private String url;
    private String type;    //文章类型   0：分享  1：专栏
}
