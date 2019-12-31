package com.dillon.repository;

import com.dillon.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author DillonXie
 * @version 1.0
 *
 * @date 12/19/2019 7:45 PM
 */
public interface ProblemRepository extends JpaRepository<Problem,String> {
    /*
    根据标签id查询最新问题列表
     */
    @Query("select p from Problem p where id in(select problemId from Pl where labelId = ?1) order by replyTime desc")
    Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);

    /*
    根据id查询热门问题列表
     */
    @Query("select p from Problem p where id in(select problemId from Pl where labelId = ?1) order by reply desc")
    Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);

    /*
    根据标签id查询等待回答列表
     */
    @Query("select p from Problem p where id in(select problemId from  Pl where labelId = ?1) and reply = 0 order by createTime desc")
    Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
