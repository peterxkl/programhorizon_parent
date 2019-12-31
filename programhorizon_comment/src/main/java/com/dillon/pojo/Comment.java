package com.dillon.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 7:13 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comment{
    @Id
    private String id;
    private String articleId;
    private String content;
    private String userId;
    private String parentId;
    @CreatedDate
    private LocalDateTime publishDate;
}
