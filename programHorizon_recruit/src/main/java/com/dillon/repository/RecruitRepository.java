package com.dillon.repository;

import com.dillon.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 2:33 PM
 */
@Repository
public interface RecruitRepository extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {
    List<Recruit> findTop4ByStateOrderByCreateDateDesc(String state);     //这里如果有黄色标记部分，就代表语句写得有错误。需要修改
    List<Recruit> findTop12ByStateIsNotOrderByCreateDateDesc(String state);
}
