package com.dillon.service;

import com.dillon.pojo.Gathering;
import com.dillon.repository.GatheringRepository;
import enums.ExceptionEnums;
import exception.PhException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
    查询所有活动
     */
    public List<Gathering> getAll() {
        return gatheringRepository.findAll();
    }


    /*
    根据id查询活动
     */
    @Cacheable(value = "c1")
    public Gathering findById(String id) {
        return gatheringRepository.findById(id).orElseThrow(() -> new PhException(ExceptionEnums.GATHERING_NOT_FOUND));
    }

    /*
    更新活动信息
     */
    @CachePut(value = "c1")
    public Gathering updateGathering(Gathering article) {
        return gatheringRepository.save(article);
    }

    /*
    删除活动
     */
    @CacheEvict(value = "c1")
    public void deleteById(String id) {
        gatheringRepository.deleteById(id);
    }

    /*
    根据条件分页查询
     */
    public Page<Gathering> search(int page, int size, Gathering gathering) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<Gathering> allGatheringByState = gatheringRepository.findByState(gathering.getState(), pageRequest);
        return allGatheringByState;
    }
}
