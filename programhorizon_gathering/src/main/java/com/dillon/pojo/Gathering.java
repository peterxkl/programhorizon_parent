package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 1:35 PM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_gathering")
public class Gathering implements Serializable {
    @Id
    private String id;
    private String name;
    private String summary;
    private String detail;
    private String sponsor;
    private String image;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String address;
    private LocalDateTime enrollTime;  //报名截止时间
    private String state;
    private String city;
}
