package com.handongkeji.util.manager;

import com.qingyun.mybatis.model.AuthPermissionRule;
import com.qingyun.vo.manager.AuthPermissionRuleMergeVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限规则生成树形节点工具类
 */
public class PermissionRuleTreeUtils {

    /**
     * 多维数组
     */
    public static List<AuthPermissionRuleMergeVo> merge(List<AuthPermissionRule> authPermissionRuleList,
                                                        Long pid) {

        List<AuthPermissionRuleMergeVo> authPermissionRuleMergeVoList = new ArrayList<>();
        for (AuthPermissionRule v : authPermissionRuleList) {
            AuthPermissionRuleMergeVo authPermissionRuleMergeVO = new AuthPermissionRuleMergeVo();
            BeanUtils.copyProperties(v, authPermissionRuleMergeVO);
            if (pid.equals(v.getPid())) {
                authPermissionRuleMergeVO.setChildren(merge(authPermissionRuleList, v.getId()));
                authPermissionRuleMergeVoList.add(authPermissionRuleMergeVO);
            }
        }

        return authPermissionRuleMergeVoList;
    }



}
