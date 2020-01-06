package com.dillon.service;

import com.dillon.client.UserClient;
import com.dillon.pojo.Friend;
import com.dillon.pojo.NoFriend;
import com.dillon.repository.FriendRepository;
import com.dillon.repository.NoFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DillonXie
 * @version 1.0
 * @date 1/4/2020 7:36 PM
 */
@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private NoFriendRepository noFriendRepository;
    @Autowired
    private UserClient userClient;

    /*
    添加好友
     */
    @Transactional
    public int addFriend(String userid, String friendid) {
        //注意：可能还存在用户已经添加对方为喜欢的情况
        if (friendRepository.findByUserIdAndFriendId(userid, friendid) > 0) {
            return 0;
        }
        int isBothLike = friendRepository.findByUserIdAndFriendId(friendid, userid);
        if (isBothLike > 0) { //表示对方也喜欢你
            //1.添加一条Friend记录
            friendRepository.save(Friend.builder().userId(userid).friendId(friendid).islike("1").build());
            userClient.updateFollowcount(userid,1);  //自己关注数+1
            //2.修改对方的Friend记录
            friendRepository.updateLike(friendid, userid, "1");
            userClient.updateFanscount(friendid, 1);  //好友粉丝数+1
        } else {
            friendRepository.save(Friend.builder().userId(userid).friendId(friendid).islike("0").build());
            userClient.updateFollowcount(userid,1);  //自己关注数+1
            userClient.updateFanscount(friendid, 1);  //好友粉丝数+1
        }
        return 1;
    }

    /*
    添加非好友
     */
    public void addNotFriend(String userid, String friendid) {
        noFriendRepository.save(NoFriend.builder().userId(userid).friendId(friendid).build());
    }

    /*
    删除好友
     */
    @Transactional
    public void deleteFriend(String userId, String friendid) {
        /*
        1.删除自己的Friend表
        2.更新好友的Friend表
        3.添加自己的NoFriend表
         */
        friendRepository.deleteByUserIdAndAndFriendId(userId, friendid);
        friendRepository.updateLike(friendid, userId, "0");
        noFriendRepository.save(NoFriend.builder().userId(userId).friendId(friendid).build());

        userClient.updateFanscount(friendid, -1);  //好友粉丝数-1
        userClient.updateFollowcount(userId,-1);  //自己关注数-1
    }
}
