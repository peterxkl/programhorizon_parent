package com.dillon.service;

import com.dillon.pojo.Problem;
import com.dillon.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * @author DillonXie
 * @version 1.0
 * @date 12/19/2019 8:04 PM
 */
@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    public Page<Problem> findNewListByLabelId(String labelId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);   //需要注意：这里封装分页功能的类的of方法的调用方式
        return problemRepository.findNewListByLabelId(labelId, pageRequest);
    }

    public Page<Problem> findHotListByLabelId(String labelId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);   //需要注意：这里封装分页功能的类的of方法的调用方式
        return problemRepository.findHotListByLabelId(labelId, pageRequest);
    }

    public Page<Problem> findWaitListByLabelId(String labelId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);   //需要注意：这里封装分页功能的类的of方法的调用方式
        return problemRepository.findWaitListByLabelId(labelId, pageRequest);
    }
}
