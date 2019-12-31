package com.dillon.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.dillon.pojo.Article;
import com.dillon.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 10:42 AM
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /*
    添加文章
     */
    @PostMapping
    public Result saveArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return Result.builder().flag(true).code(StatusCode.OK).message("新增成功").data(null).build();
    }

    /*
    审核文章
     */
    @PutMapping("/examine/{id}")
    public Result examineArticleById(@PathVariable String id) {
        articleService.examineArticleById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("审核通过").data(null).build();
    }

    /*
    点赞文章
     */
    @PutMapping("/thumbup/{id}")
    public Result updateThumbup(@PathVariable String id) {
        int thumbup = articleService.updateThumbup(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("点赞成功").data(thumbup).build();

    }

    /*
    根据id查询文章
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Article result = articleService.findById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    /*
    更新文章信息
     */
    @PutMapping
    public Result updateArticle(@RequestBody Article article) {
        Article updateArticle = articleService.updateArticle(article);
        return Result.builder().flag(true).code(StatusCode.OK).message("更新成功").data(updateArticle).build();
    }

    /*
    删除文章
     */
    @DeleteMapping
    public Result deleteById(String id) {
        articleService.deleteById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("删除成功").data(null).build();
    }
}
