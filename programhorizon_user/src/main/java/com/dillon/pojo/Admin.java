package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 5:41 PM
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    private String id;
    private String loginname;
    private String password;
    private String state;
}
