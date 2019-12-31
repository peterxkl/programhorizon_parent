package com.dillon.repository;

import com.dillon.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/24/2019 10:10 PM
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, String> {
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
