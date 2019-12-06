package com.qingyun.controller.manager;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.KeyWord;
import com.qingyun.mybatis.model.SysFrinedshiplink;
import com.qingyun.service.FriendshipLinkService;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/12/3 0003 13:58
 */

@RestController
@Slf4j
@RequestMapping("friendshipLink")
public class FriendshipLinkController {

    @Autowired
    FriendshipLinkService friendshipLinkService;

    /**
     * 新增友情链接
     * @param sysFrinedshiplink
     * @return
     */
    @PostMapping("addFriendshipLink")
    public ResultVo addFriendshipLink(SysFrinedshiplink sysFrinedshiplink){
        try {
            if (sysFrinedshiplink.getFriendshiplinkId() == null) {
                //新增
                sysFrinedshiplink.setCreateTime(new Date());
                friendshipLinkService.addFriiendLink(sysFrinedshiplink);
            } else {
                //编辑
                sysFrinedshiplink.setEditTime(new Date());
                friendshipLinkService.updateFriendshipLink(sysFrinedshiplink);
            }
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 删除友情链接
     * @param ids
     * @return
     */
    @GetMapping("deleteFriendshipLink")
    public ResultVo deleteFriendshipLink(String ids){
        try{
            friendshipLinkService.deleteFriendshipLink(ids);
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 获取友情链接列表
     * @param page
     * @return
     */
    @GetMapping("getFriendshipLink")
    public ResultVo getFriendshipLink(EntyPage page){
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("page",page);
            List<SysFrinedshiplink> friendshipLinkHDPage = friendshipLinkService.getFriendshipLinkHDPage(map);
            PageSimpleVO<SysFrinedshiplink> sysProblemPageSimpleVO = new PageSimpleVO<SysFrinedshiplink>(page.getTotalNumber(), friendshipLinkHDPage);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

}
