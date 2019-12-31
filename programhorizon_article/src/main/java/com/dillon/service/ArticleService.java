package com.dillon.service;

import com.dillon.pojo.Article;
import com.dillon.repository.ArticleRepository;
import enums.ExceptionEnums;
import exception.PhException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 10:41 AM
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private IdWorker idWorker;

    /*
    根据id审核文章
     */
    @Transactional
    public void examineArticleById(String id) {
        articleRepository.examine(id);
    }

    /*
    根据id点赞文章
     */
    @Transactional
    public int updateThumbup(String id) {
        int thumbup = articleRepository.updateThumbup(id);
        return thumbup;
    }


    /*
    添加文章
     */
    public void addArticle(Article article) {
        article.setId(idWorker.nextId()+"");
        //todo  有些值前端不会传，后台需要初始化
        articleRepository.save(article);
    }

    /*
    根据id查询文章
     */
    @Cacheable(value = "c1")
    public Article findById(String id) {
        return articleRepository.findById(id).orElseThrow(() -> new PhException(ExceptionEnums.ARTICLE_NOT_FOUND));
    }

    /*
    更新文章信息
     */
    @CachePut(value = "c1")
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    /*
    删除文章
     */
    @CacheEvict(value = "c1")
    public void deleteById(String id) {
        articleRepository.deleteById(id);
    }
}
