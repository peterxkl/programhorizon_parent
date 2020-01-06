package com.dillon.pojo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/4/2020 5:55 PM
 */
@Entity
@Table(name = "tb_friend")
@Data
@Builder
public class Friend implements Serializable {
    @Id
    private String userId;
    @Id
    private String friendId;
    private String islike; //0表示单项喜欢  1表示互相喜欢
}
