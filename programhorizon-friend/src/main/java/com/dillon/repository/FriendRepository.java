package com.dillon.repository;

import com.dillon.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/4/2020 7:35 PM
 */
public interface FriendRepository extends JpaRepository<Friend, String> {
    int findByUserIdAndFriendId(String userid, String friendid);
    void deleteByUserIdAndAndFriendId(String userid, String friendid);
    @Modifying
    @Query("update Friend f set f.islike = ?3  where f.userId = ?1 and f.friendId = ?2")
    void updateLike(String userid, String friendid, String islike);
}
