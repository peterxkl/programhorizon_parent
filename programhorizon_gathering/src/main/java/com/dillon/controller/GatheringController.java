package com.dillon.controller;

import com.dillon.pojo.Gathering;
import com.dillon.service.GatheringService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 1:42 PM
 */
@RestController
@RequestMapping("gathering")
@CrossOrigin
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    /*
    查询所有活动
     */
    @GetMapping
    public Result getAllGathering() {
        List<Gathering> result = gatheringService.getAll();
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    /*
    根据id查询活动
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Gathering result = gatheringService.findById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    /*
    更新活动信息
     */
    @PutMapping
    public Result updateArticle(@RequestBody Gathering gathering) {
        Gathering updateGathering = gatheringService.updateGathering(gathering);
        return Result.builder().flag(true).code(StatusCode.OK).message("更新成功").data(updateGathering).build();
    }

    /*
    删除活动
     */
    @DeleteMapping
    public Result deleteById(String id) {
        gatheringService.deleteById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("删除成功").data(null).build();
    }

    /*
    根据条件分页查询
     */
    @PostMapping("/search/{page}/{size}")
    public Result search(@PathVariable int page, @PathVariable int size, @RequestBody Gathering gathering) {
        Page<Gathering> searchResult = gatheringService.search(page, size, gathering);
        PageResult<Gathering> pageResult = new PageResult<>(searchResult.getTotalElements(), searchResult.getContent());
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(pageResult).build();
    }

}
