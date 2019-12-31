package com.dillon.controller;

import com.dillon.pojo.Article;
import com.dillon.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/24/2019 10:09 PM
 */
@RestController
@RequestMapping("article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    @PostMapping
    public Result saveArticle(@RequestBody Article article) {
        articleSearchService.saveArticle(article);
        return Result.builder().flag(true).code(StatusCode.OK).message("新增成功").data(null).build();
    }

    @GetMapping("search")
    public Result findByTitleOrContentLike(@Param(value = "keyword") String keyword, @Param(value = "page") int page, @Param(value = "size") int size) {
        Page<Article> result = articleSearchService.findByTitleOrContentLike(keyword, page, size);
        PageResult<Article> pageResult = new PageResult<>(result.getTotalElements(),result.getContent());
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(pageResult).build();
    }
}
