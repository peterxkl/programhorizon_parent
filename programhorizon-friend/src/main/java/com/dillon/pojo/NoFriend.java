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
 * @date 1/4/2020 8:29 PM
 */
@Entity
@Table(name = "tb_nofriend")
@Data
@Builder
public class NoFriend implements Serializable {
    @Id
    private String userId;
    @Id
    private String friendId;
}
