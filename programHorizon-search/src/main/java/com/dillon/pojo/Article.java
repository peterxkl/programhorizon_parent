package com.dillon.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/24/2019 10:04 PM
 */
@Data
@Document(indexName = "programhorizon", type = "article")   //注意indexName必须小写
public class Article implements Serializable {
    @Id
    private String id;
    @Field(analyzer = "ik_max_word")
    private String title;
    @Field(analyzer = "ik_max_word")
    private String content;
    private String state;
}
