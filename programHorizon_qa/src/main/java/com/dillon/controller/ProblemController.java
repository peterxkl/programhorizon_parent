package com.dillon.controller;

import com.dillon.pojo.Problem;
import com.dillon.service.ProblemService;
import com.sun.org.apache.regexp.internal.RE;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/19/2019 7:44 PM
 */
@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    /*
    根据标签id查询最新问题列表
     */
    @RequestMapping("/newlist/{labelid}/{page}/{size}")
    public Result findNewListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> newListByLabelId = problemService.findNewListByLabelId(labelid, page, size);
        PageResult<Problem> result = new PageResult<>(newListByLabelId.getTotalElements(), newListByLabelId.getContent());
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    /*
      根据标签id查询热门问题列表
     */
    @RequestMapping("/hotlist/{labelid}/{page}/{size}")
    public Result findHotListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> hotlistByLabelId = problemService.findHotListByLabelId(labelId, page, size);
        PageResult<Problem> result = new PageResult<>(hotlistByLabelId.getTotalElements(), hotlistByLabelId.getContent());
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    /*
      根据标签id查询等待回答列表
     */
    @RequestMapping("/waitlist/{labelid}/{page}/{size}")
    public Result findWaitListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> waitListByLabelId = problemService.findWaitListByLabelId(labelId, page, size);
        PageResult<Problem> result = new PageResult<>(waitListByLabelId.getTotalElements(), waitListByLabelId.getContent());
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }
}
