package com.dillon.controller;

import com.dillon.pojo.Label;
import com.dillon.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/6/2019 11:47 AM
 */
@RestController
@RequestMapping("label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Label label = labelService.findById(id);
        return Result.builder().flag(true).message("查询成功").code(StatusCode.OK).data(label).build();
    }
    @GetMapping
    public Result findAll() {
        List<Label> labels = labelService.findAll();
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(labels).build();
    }
    @PostMapping
    public Result addLabel(@RequestBody Label label) {
        labelService.add(label);
        return Result.builder().flag(true).code(StatusCode.OK).message("新增成功").data(null).build();
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("删除成功").data(null).build();
    }
    @PostMapping("search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> search = labelService.findSearch(label);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(search).build();
    }
    @PostMapping("search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> labels = labelService.pageQuery(label, page, size);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(new PageResult<Label>(labels.getTotalElements(),labels.getContent())).build();
    }
}
