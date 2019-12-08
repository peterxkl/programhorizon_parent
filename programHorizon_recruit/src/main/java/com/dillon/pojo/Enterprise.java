package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 1:59 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_enterprise")
public class Enterprise {
    @Id
    private String id;
    private String name; //企业名称
    private String summary; //企业简介
    private String address; //企业地址
    private String labels; //标签列表
    private String coordinate; //坐标
    private String isHot;  //是否热门  0-不热门 1-热门
    private String logo; //Logo
    private int jobCount; //职位数
    private String url; //URL
}
