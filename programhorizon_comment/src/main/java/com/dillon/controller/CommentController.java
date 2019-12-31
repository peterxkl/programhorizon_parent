package com.dillon.controller;

import com.dillon.pojo.Comment;
import com.dillon.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 7:17 PM
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{articleId}}")
    public Result findByArticleId(@PathVariable String articleId) {
        List<Comment> result = commentService.findByArticleId(articleId);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    @PostMapping
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.builder().flag(true).code(StatusCode.OK).message("新增成功").data(null).build();
    }

    @DeleteMapping
    public Result deleteById(@PathVariable String id) {
       commentService.deleteById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("删除成功").data(null).build();
    }
}
