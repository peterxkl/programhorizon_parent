package com.dillon.repository;

import com.dillon.pojo.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 1:41 PM
 */
public interface GatheringRepository extends JpaRepository<Gathering, String> {
}
