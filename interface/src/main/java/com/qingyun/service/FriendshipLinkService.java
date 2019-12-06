package com.qingyun.service;

import com.qingyun.mybatis.model.SysFrinedshiplink;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/12/3 0003 11:47
 */
public interface FriendshipLinkService {

    void addFriiendLink(SysFrinedshiplink frinedshiplink);

    void deleteFriendshipLink(String friendshiplinkId);

    void updateFriendshipLink(SysFrinedshiplink sysFrinedshiplink);

    List<SysFrinedshiplink> getFriendshipLinkHDPage(Map<String,Object> map);
}
