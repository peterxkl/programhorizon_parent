package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 2:24 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_recruit")
@EntityListeners(AuditingEntityListener.class)
public class Recruit {
    @Id
    private String id;
    private String jobName;         //职位名称
    private String salary;          //薪资范围
    private String condition1;       //经验要求    ???为什么这里不能命名为“condition”
    private String education;       //学历要求
    private String type;            //任职方式
    private String address;         //办公地址
    private String eid;             //企业id
    @CreatedDate
    private LocalDate createDate;  //创建时间
    private String state;       //状态   0-关闭 1-开启 2-推荐
    private String url;         //网址
    private String label;       //标签
    private String content1;   //职位描述
    private String content2;   //职位要求
}
