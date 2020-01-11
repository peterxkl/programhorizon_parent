package com.dillon.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/4/2020 5:03 PM
 */
@FeignClient(value = "programHorizon-base", fallback = LabelClientImpl.class)
public interface LabelClient {

    @GetMapping("label/{id}")
    Result findById(@PathVariable("id") String id);

}
