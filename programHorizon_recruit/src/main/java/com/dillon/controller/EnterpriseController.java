package com.dillon.controller;

import com.dillon.pojo.Enterprise;
import com.dillon.service.EnterpriseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 2:05 PM
 */
@RestController
@RequestMapping("recruit/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/search/hotList")
    public Result getHotList() {
        List<Enterprise> list = enterpriseService.getHotList();
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(list).build();
    }

    /*
    根据id查询企业
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Enterprise result = enterpriseService.findById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }
}
