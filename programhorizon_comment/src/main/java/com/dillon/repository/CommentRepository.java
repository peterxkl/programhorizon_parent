package com.dillon.repository;

import com.dillon.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 7:19 PM
 */
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByArticleId(String id);
}
