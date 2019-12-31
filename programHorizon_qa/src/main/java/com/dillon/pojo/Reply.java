package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/9/2019 9:31 AM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_reply")
@EntityListeners(AuditingEntityListener.class)
public class Reply {
    @Id
    private String id;
    private String problemId;
    private String content;
    @CreatedDate
    private LocalDateTime createTime;
}
