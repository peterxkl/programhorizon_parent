package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 2:51 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Spit implements Serializable {
    @Id
    private String id;
    private String content;
    private LocalDateTime publishTime;
    private String userId;
    private String nickname;
    private int visit;
    private int thumbup;
    private int share;
    private int comment;
    private String state;
    private String parentId;
}
