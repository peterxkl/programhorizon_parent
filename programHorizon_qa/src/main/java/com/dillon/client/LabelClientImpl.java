package com.dillon.client;

import entity.Result;
import entity.StatusCode;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/8/2020 7:26 PM
 */
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String id) {
        return Result.builder().flag(false).code(StatusCode.ERROR).message("熔断器启动").build();
    }
}
