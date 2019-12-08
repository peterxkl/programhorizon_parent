package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/6/2019 11:36 AM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_label")
public class Label {
    @Id
    private String id;
    private String labelName;  //标签名称
    private String state;    //状态
    private Long count; //使用数量
    private Long fans;  //关注数
    private String recommend; //是否推荐
}
