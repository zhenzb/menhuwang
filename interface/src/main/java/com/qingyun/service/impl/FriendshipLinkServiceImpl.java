package com.qingyun.service.impl;

import com.qingyun.mybatis.dao.SysFrinedshiplinkMapper;
import com.qingyun.mybatis.model.SysFrinedshiplink;
import com.qingyun.mybatis.model.SysFrinedshiplinkExample;
import com.qingyun.service.FriendshipLinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/12/3 0003 13:26
 */
@Service
public class FriendshipLinkServiceImpl implements FriendshipLinkService {

    @Resource
    SysFrinedshiplinkMapper sysFrinedshiplinkMapper;

    @Override
    public void addFriiendLink(SysFrinedshiplink frinedshiplink) {
        sysFrinedshiplinkMapper.insertSelective(frinedshiplink);
    }

    @Override
    public void deleteFriendshipLink(String friendshiplinkId) {
        List<Long> list = new ArrayList<>();
        String[] split = friendshiplinkId.split(",");
        for(String str:split){
            list.add(Long.valueOf(str));
        }
        SysFrinedshiplinkExample sysFrinedshiplinkExample = new SysFrinedshiplinkExample();
        sysFrinedshiplinkExample.createCriteria().andFriendshiplinkIdIn(list);
        sysFrinedshiplinkMapper.deleteByExample(sysFrinedshiplinkExample);
    }

    @Override
    public void updateFriendshipLink(SysFrinedshiplink sysFrinedshiplink) {
        sysFrinedshiplinkMapper.updateByPrimaryKeySelective(sysFrinedshiplink);
    }

    @Override
    public List<SysFrinedshiplink> getFriendshipLinkHDPage(Map<String, Object> map) {
        return sysFrinedshiplinkMapper.getFriendshipLinkHDPage(map);
    }
}
