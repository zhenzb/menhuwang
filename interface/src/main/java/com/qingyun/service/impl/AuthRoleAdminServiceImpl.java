package com.qingyun.service.impl;

import com.qingyun.service.AuthRoleAdminService;
import com.qingyun.mybatis.dao.AuthRoleAdminMapper;
import com.qingyun.mybatis.model.AuthRoleAdmin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthRoleAdminServiceImpl implements AuthRoleAdminService {

    @Resource
    private AuthRoleAdminMapper authRoleAdminDao;

    /**
     * 根据 adminid 获取角色id
     * @param adminId
     * @return
     */
    @Override
    public List<AuthRoleAdmin> listByAdminId(Long adminId) {
        return authRoleAdminDao.listByAdminId(adminId);
    }

    /**
     * 根据多个 adminId 查询角色列表
     * @param adminIds
     * @return
     */
    @Override
    public List<AuthRoleAdmin> listByAdminIdIn(List<Long> adminIds) {
        if (adminIds.isEmpty()) {
            return Collections.emptyList();
        }
        return authRoleAdminDao.listByAdminIdIn(adminIds);
    }

    /**
     * 根据 roleId 获取 管理员id
     * @param roleId
     * @return
     */
    @Override
    public List<AuthRoleAdmin> listByRoleId(Long roleId) {
        List<AuthRoleAdmin> authRoleAdmins=authRoleAdminDao.listByRoleId(roleId);
        if(authRoleAdmins.size()==0){
            AuthRoleAdmin authRoleAdmin=new AuthRoleAdmin();
            authRoleAdmin.setId(9999L);
            authRoleAdmin.setRoleId(9999L);
            authRoleAdmin.setAdminId(9999L);
            authRoleAdmins.add(authRoleAdmin);
        }
        return authRoleAdmins;
    }

    /**
     * 批量插入
     * @param authRoleAdminList
     * @return
     */
    @Override
    public int insertAuthRoleAdminAll(List<AuthRoleAdmin> authRoleAdminList) {

        if (authRoleAdminList == null || authRoleAdminList.isEmpty()) {
            return 0;
        }

        return authRoleAdminDao.insertAuthRoleAdminAll(authRoleAdminList);
    }

    /**
     * 根据 角色ids 和 管理员 adminId 批量插入
     * @param roles
     * @param adminId
     * @return
     */
    @Override
    public int insertRolesAdminIdAll(List<Long> roles, Long adminId) {

        List<AuthRoleAdmin> authRoleAdminList = roles.stream().map(aLong -> {
            AuthRoleAdmin authRoleAdmin = new AuthRoleAdmin();
            authRoleAdmin.setRoleId(aLong);
            authRoleAdmin.setAdminId(adminId);
            return authRoleAdmin;
        }).collect(Collectors.toList());
        if (!authRoleAdminList.isEmpty()) {
            return insertAuthRoleAdminAll(authRoleAdminList);
        }

        return 0;
    }

    /**
     * 根据 adminId 删除对应的权限
     * @param adminId
     * @return
     */
    @Override
    public boolean deleteByAdminId(Long adminId) {
        // 删除之前缓存权限规则
//        String aarKey = String.format(RedisConstant.ADMIN_AUTH_RULES, adminId);
//        CacheUtils.delete(aarKey);

        return authRoleAdminDao.deleteByAdminId(adminId);
    }

    @Override
    public List<Long> findByRoleId(Long id) {
        return authRoleAdminDao.findByRoleId(id);
    }

    @Override
    public Integer insert(Long roleId, Long adminId) {
        return authRoleAdminDao.insert(roleId,adminId);
    }
}
