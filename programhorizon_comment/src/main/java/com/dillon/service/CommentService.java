package com.dillon.service;

import com.dillon.pojo.Comment;
import com.dillon.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 7:18 PM
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IdWorker idWorker;

    public List<Comment> findByArticleId(String id){
        return commentRepository.findByArticleId(id);
    }

    public void add(Comment comment) {
        comment.setId(idWorker.nextId() + "");
        commentRepository.save(comment);
    }

    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}

