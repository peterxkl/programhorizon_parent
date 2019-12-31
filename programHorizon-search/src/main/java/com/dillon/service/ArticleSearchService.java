package com.dillon.service;

import com.dillon.pojo.Article;
import com.dillon.repository.ArticleSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/24/2019 10:10 PM
 */
@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;
    @Autowired
    private IdWorker idWorker;


    public void saveArticle(Article article) {
        article.setId(idWorker.nextId()+"");
        article.setState("1");
        articleSearchRepository.save(article);
    }

    public Page<Article> findByTitleOrContentLike(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<Article> result = articleSearchRepository.findByTitleOrContentLike(keyword, keyword, pageRequest);
        return result;
    }
}
