package com.dillon.repository;

import com.dillon.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/4/2020 8:31 PM
 */
public interface NoFriendRepository extends JpaRepository<NoFriend, String> {
    int findByUserIdAndFriendId(String userid, String friendid);
    void deleteByUserIdAndAndFriendId(String userid, String friendid);
}
