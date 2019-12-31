package com.dillon.repository;

import com.dillon.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 10:36 AM
 */
public interface ArticleRepository extends JpaRepository<Article, String> {

    /*
    根据文章id来审核文章
     */
    @Modifying
    @Query("update Article set state = '1' where id = ?1")
    public void examine(String id);

    /*
    根据id来点赞文章
     */
    @Modifying
    @Query("update Article set thumbup = thumbup+1 where id = ?1")
    public int updateThumbup(String id);

}
