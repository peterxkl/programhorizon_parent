package com.dillon.repository;

import com.dillon.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/29/2019 11:20 AM
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByMobile(String mobile);

    @Modifying
    @Query("update User u set u.fanscount = u.fanscount + ?1 where u.id = ?2")
    void updateFanscount(String userid, int number);

    @Modifying
    @Query("update User u set u.followcount = u.followcount + ?1 where u.id = ?2")
    void updateFollowcount(String userid, int number);
}
