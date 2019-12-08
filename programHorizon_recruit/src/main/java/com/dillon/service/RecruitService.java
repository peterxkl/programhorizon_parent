package com.dillon.service;

import com.dillon.pojo.Recruit;
import com.dillon.repository.RecruitRepository;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 2:35 PM
 */
@Service
public class RecruitService {

    @Resource
    private RecruitRepository recruitRepository;
    @Resource
    private IdWorker idWorker;

    public List<Recruit> findTop4ByStateOrdOrderByCreateTimeDesc(String state) {
        return recruitRepository.findTop4ByStateOrderByCreateDateDesc(state);
    }

    public List<Recruit> findTop12ByStateIsNotOrOrderByCreateTimeDesc(String state){
        return recruitRepository.findTop12ByStateIsNotOrderByCreateDateDesc(state);
    }

    public void addRecruit(Recruit recruit) {
        recruit.setId(idWorker.nextId()+"");
        recruitRepository.save(recruit);
    }

    public void addManyRecruit(List<Recruit> recruitList) {
        recruitRepository.saveAll(recruitList);
    }
}
