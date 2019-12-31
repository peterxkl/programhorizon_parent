package com.dillon.controller;

import com.dillon.pojo.Spit;
import com.dillon.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/22/2019 2:58 PM
 */
@RestController
@RequestMapping("spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll() {
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(spitService.findAll()).build();
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable String id) {
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(spitService.findById(id)).build();
    }

    @PostMapping
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return Result.builder().flag(true).code(StatusCode.OK).message("新增成功").data(null).build();
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Spit spit, @PathVariable String id) {
        spit.setId(id);
        spitService.update(spit);
        return Result.builder().flag(true).code(StatusCode.OK).message("修改成功").data(null).build();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        spitService.deleteById(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("删除成功").data(null).build();
    }

    @RequestMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> result = spitService.findByParentId(parentId, page, size);
        PageResult<Spit> pageResult = new PageResult<>(result.getTotalElements(), result.getContent());
        return Result.builder().flag(true).code(StatusCode.OK).message("查询成功").data(pageResult).build();
    }

    @PutMapping("/thumbup/{id}")
    public Result updateThumbup(@PathVariable String id) {   //这里需要控制不能重复点赞
        String userId = "2023";
        if (redisTemplate.opsForValue().get("thumbup_"+userId+"_"+id) != null) {
            return Result.builder().flag(false).code(StatusCode.REPERROR).message("你已经点过赞了").data(null).build();
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_"+userId+"_"+id, "1");
        return Result.builder().flag(true).code(StatusCode.OK).message("点赞成功").data(null).build();
    }

    @PutMapping("/share/{id}")
    public Result updateShare(@PathVariable String id) {
        spitService.updateShare(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("分享成功").data(null).build();
    }
    @PutMapping("/visit/{id}")
    public Result updateVisit(@PathVariable String id) {
        spitService.updateVisit(id);
        return Result.builder().flag(true).code(StatusCode.OK).message("浏览成功").data(null).build();
    }

}
