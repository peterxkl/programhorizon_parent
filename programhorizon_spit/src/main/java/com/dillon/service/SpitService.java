package com.dillon.service;

import com.dillon.pojo.Spit;
import com.dillon.repository.SpitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 2:58 PM
 */
@Service
public class SpitService {

    @Autowired
    private SpitRepository spitRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    /*
    查询全部记录
     */
    public List<Spit> findAll() {
        return spitRepository.findAll();
    }

    /*
    根据id查询实体
     */
    public Spit findById(String id) {
        Spit spit = spitRepository.findById(id).get();
        return spit;
    }

    /*
    增加
     */
    public void add(Spit spit) {
        spit.setId(idWorker.nextId()+"");
        spit.setPublishTime(LocalDateTime.now());
        spit.setShare(0);
        spit.setState("1");
        spit.setThumbup(0);
        spit.setVisit(0);
        spit.setComment(0);
        if (spit.getParentId() == null || "".equals(spit.getParentId())) {   //判断这条吐槽是评论他人还是发布自己吐槽
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(spit.getParentId()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query, update, Spit.class);
        }
        spitRepository.save(spit);
    }

    /*
    修改
     */
    public void update(Spit spit) {
        spitRepository.save(spit);
    }

    /*
    删除
     */
    public void deleteById(String id) {
        spitRepository.deleteById(id);
    }

    /*
    根据父级id查询吐槽列表并分页
     */
    public Page<Spit> findByParentId(String parentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Spit> result = spitRepository.findByParentId(parentId, pageRequest);
        return result;
    }

    /*
    点赞
     */
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query, update, Spit.class);
    }

    /*
    分享
     */
    public void updateShare(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("share",1);
        mongoTemplate.updateFirst(query, update, Spit.class);
    }

    /*
    浏览
     */
    public void updateVisit(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("visit",1);
        mongoTemplate.updateFirst(query, update, Spit.class);
    }


}

