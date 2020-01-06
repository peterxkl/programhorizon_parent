package com.dillon.repository;

import com.dillon.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/2/2020 8:10 PM
 */
public interface ReplyRepository extends JpaRepository<Reply, String> {
}
