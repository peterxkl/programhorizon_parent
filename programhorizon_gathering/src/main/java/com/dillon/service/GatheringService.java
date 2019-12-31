package com.dillon.service;

import com.dillon.pojo.Gathering;
import com.dillon.repository.GatheringRepository;
import enums.ExceptionEnums;
import exception.PhException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 1:42 PM
 */
@Service
public class GatheringService {
    @Autowired
    private GatheringRepository gatheringRepository;

    /*
    根据id查询文章
     */
    @Cacheable(value = "c1")
    public Gathering findById(String id) {
        return gatheringRepository.findById(id).orElseThrow(() -> new PhException(ExceptionEnums.GATHERING_NOT_FOUND));
    }

    /*
    更新文章信息
     */
    @CachePut(value = "c1")
    public Gathering updateGathering(Gathering article) {
        return gatheringRepository.save(article);
    }

    /*
    删除文章
     */
    @CacheEvict(value = "c1")
    public void deleteById(String id) {
        gatheringRepository.deleteById(id);
    }
}
