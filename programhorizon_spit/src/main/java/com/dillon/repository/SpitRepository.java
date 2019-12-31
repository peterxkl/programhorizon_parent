package com.dillon.repository;

import com.dillon.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 2:59 PM
 */
public interface SpitRepository extends MongoRepository<Spit, String> {
    Page<Spit> findByParentId(String parentId, Pageable pageable);
}
