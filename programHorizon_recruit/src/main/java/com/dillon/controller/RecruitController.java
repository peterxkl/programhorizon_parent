package com.dillon.controller;

import com.dillon.pojo.Enterprise;
import com.dillon.pojo.Recruit;
import com.dillon.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 2:32 PM
 */
@RestController
@RequestMapping("recruit/recruit")
public class RecruitController {

    @Resource
    private RecruitService recruitService;

    @GetMapping("search/recommend")
    public Result recommend() {
        List<Recruit> result = recruitService.findTop4ByStateOrdOrderByCreateTimeDesc("2");
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    @GetMapping("search/newList")
    public Result newList() {
        List<Recruit> result = recruitService.findTop12ByStateIsNotOrOrderByCreateTimeDesc("0");
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    @PostMapping
    public Result addRecruit(@RequestBody Recruit recruit) {
        recruitService.addRecruit(recruit);
        return Result.builder().flag(true).code(StatusCode.OK).message("新增成功").data(null).build();
    }

    /*
    根据id查询企业
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Recruit result = recruitService.findById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }
}
