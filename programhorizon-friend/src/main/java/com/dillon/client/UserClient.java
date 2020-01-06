package com.dillon.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/4/2020 9:15 PM
 */
@FeignClient("programHorizon-user")
public interface UserClient  {
    @PutMapping("/insfans/{id}/{number}")
    void updateFanscount(@PathVariable("id") String id, @PathVariable("number") int number);

    @PutMapping("/insfollow/{id}/{number}")
    void updateFollowcount(@PathVariable("id") String id, @PathVariable("number") int number);
}
