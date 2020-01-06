package com.dillon.service;

import com.dillon.pojo.Problem;
import com.dillon.pojo.Reply;
import com.dillon.repository.ProblemRepository;
import com.dillon.repository.ReplyRepository;
import enums.ExceptionEnums;
import exception.PhException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.servlet.http.HttpServletRequest;


/**
 * @author DillonXie
 * @version 1.0
 * @date 12/19/2019 8:04 PM
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private HttpServletRequest http;
    @Autowired
    private IdWorker idWorker;

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

    public void addProblem(Problem problem) {
        Claims user_claims = (Claims)http.getAttribute("user_claims");
        if (user_claims == null) {
            throw new PhException(ExceptionEnums.INSUFFICIENT_PERMISSION);
        }
        problem.setId(idWorker.nextId() + "");
        problem.setUserId(user_claims.getId());
        problem.setNickname(user_claims.getSubject());
        problemRepository.save(problem);
    }
    /*
    回答问题   todo：多标关联没有处理
     */
    public void replyProblem(Reply reply) {
        Claims user_claims = (Claims)http.getAttribute("user_claims");
        if (user_claims == null) {
            throw new PhException(ExceptionEnums.INSUFFICIENT_PERMISSION);
        }
        reply.setId(idWorker.nextId() + "");
        reply.setUserId(user_claims.getId());
        reply.setNickname(user_claims.getSubject());
        replyRepository.save(reply);
    }
}
