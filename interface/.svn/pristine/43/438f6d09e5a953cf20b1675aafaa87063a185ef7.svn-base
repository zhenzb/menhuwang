package com.qingyun.service.impl;

import com.handongkeji.config.exception.JsonException;
import com.qingyun.service.AuthPermissionRuleService;
import com.qingyun.mybatis.dao.AuthPermissionRuleMapper;
import com.qingyun.mybatis.model.AuthPermissionRule;
import com.qingyun.vo.result.ResultEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class AuthPermissionRuleServiceImpl implements AuthPermissionRuleService {

    @Resource
    private AuthPermissionRuleMapper authPermissionRuleMapper;

    /**
     * 根据多个id查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<AuthPermissionRule> listByIdIn(List<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return authPermissionRuleMapper.listByIdIn(ids);
    }

    /**
     * 根据父级 pid 查询
     *
     * @param pid
     * @return
     */
    @Override
    public List<AuthPermissionRule> listByPid(Long pid) {
        return authPermissionRuleMapper.listByPid(pid);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<AuthPermissionRule> listAll() {
        return authPermissionRuleMapper.listAll();
    }

    /**
     * 插入
     * @param authPermissionRule
     * @return
     */
    @Override
    public boolean insertAuthPermissionRule(AuthPermissionRule authPermissionRule) {

        // 查询是否存在
        AuthPermissionRule byName = authPermissionRuleMapper.findByName(authPermissionRule.getName());
        if (byName != null) {
            throw new JsonException(ResultEnum.DATA_REPEAT, "当前权限规则已存在");
        }

        authPermissionRule.setCreateTime(new Date());
        authPermissionRule.setUpdateTime(new Date());
        if (authPermissionRule.getListorder() == null) {
            authPermissionRule.setListorder(999);
        }
        return authPermissionRuleMapper.insertAuthPermissionRule(authPermissionRule);
    }

    /**
     * 更新
     * @param authPermissionRule
     * @return
     */
    @Override
    public boolean updateAuthPermissionRule(AuthPermissionRule authPermissionRule) {

        if (authPermissionRule.getName() != null) {
            // 查询是否存在
            AuthPermissionRule byName = authPermissionRuleMapper.findByName(authPermissionRule.getName());
            if (byName != null && !authPermissionRule.getId().equals(byName.getId())) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前权限规则已存在");
            }
        }

        authPermissionRule.setUpdateTime(new Date());
        return authPermissionRuleMapper.updateAuthPermissionRule(authPermissionRule);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return authPermissionRuleMapper.deleteById(id);
    }
}
