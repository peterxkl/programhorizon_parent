package com.dillon.controller;

import com.dillon.pojo.Gathering;
import com.dillon.service.GatheringService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 1:42 PM
 */
@RestController
@RequestMapping("gathering")
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    /*
    根据id查询文章
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        Gathering result = gatheringService.findById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(result).build();
    }

    /*
    更新文章信息
     */
    @PutMapping
    public Result updateArticle(@RequestBody Gathering gathering) {
        Gathering updateGathering = gatheringService.updateGathering(gathering);
        return Result.builder().flag(true).code(StatusCode.OK).message("更新成功").data(updateGathering).build();
    }

    /*
    删除文章
     */
    @DeleteMapping
    public Result deleteById(String id) {
        gatheringService.deleteById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("删除成功").data(null).build();
    }

}
