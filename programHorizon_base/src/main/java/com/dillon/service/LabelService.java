package com.dillon.service;

import com.dillon.pojo.Label;
import com.dillon.repository.LabelRepository;
import entity.PageResult;
import enums.ExceptionEnums;
import exception.PhException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/6/2019 3:16 PM
 */
@Service
public class LabelService {

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    IdWorker idWorker;

    public Label findById(String id) {
        return labelRepository.findById(id).orElseThrow(() -> new PhException(ExceptionEnums.LABEL_NOT_FOUND));
    }

    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    public void update(Label label) {
        labelRepository.save(label);
    }

    public void add(Label label) {
        label.setId(idWorker.nextId()+"");
        labelRepository.save(label);
    }

    public void deleteById(String id) {
        labelRepository.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelRepository.findAll(new Specification<Label>() {
            /*
              1.root根对象，也就是把条件封装到哪个对象中。where 类名 = label.getId()
              2.criteriaQuery 封装的都是查询关键字，比如group by，order by 等
              3.criteriaBuilder 用来封装条件对象的，如果直接返回null，表示不需要任何条件
              4.Predicate 使用criteriaBuilder封装条件对象后的返回结果，一个条件会有一个该对象
              5.得到多个Predicate对象后，在使用criteriaBuilder.add()去最后执行
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                Optional.ofNullable(label.getLabelName()).ifPresent(item -> {
                    Predicate predicate = criteriaBuilder.like(root.get("labelName").as(String.class), "%"+item+"%");
                    list.add(predicate);
                });
                Optional.ofNullable(label.getState()).ifPresent(item -> {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), item);
                    list.add(predicate);
                });
                Predicate[] predicates = new Predicate[list.size()];
                list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return labelRepository.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        },pageable);
    }
}
