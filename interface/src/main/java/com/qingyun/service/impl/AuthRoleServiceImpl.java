package com.qingyun.service.impl;

import com.handongkeji.util.EntyPage;
import com.qingyun.service.AuthRoleService;
import com.qingyun.mybatis.dao.AuthRoleMapper;
import com.qingyun.mybatis.model.AuthRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Resource
    private AuthRoleMapper authRoleDao;

    /**
     * 查询列表
     * @return
     * @param map
     */
    @Override
    public List<AuthRole> listAdminPage(Map<String, Object> map) {
//        int offset = (authRoleQueryForm.getPage() - 1) * authRoleQueryForm.getLimit();
//        PageHelper.offsetPage(offset, authRoleQueryForm.getLimit());
        List<AuthRole> list = authRoleDao.listAdminHDPage(map);
        return list;
    }

    /**
     * 查询管理员页面的列表
     * @param page
     * @param status
     * @return
     */
    @Override
    public List<AuthRole> listAuthAdminRoleHDPage(EntyPage page, Integer status) {
//        page = page != null && page > 0 ? page : 1;
//        limit = limit != null && limit > 0 && limit < 100 ? limit : 100;
//        int offset = (page - 1) * limit;
//        PageHelper.offsetPage(offset, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("status", status);
        List<AuthRole> list = authRoleDao.listAuthAdminRoleHDPage(map);
        return list;
    }

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public AuthRole findByName(String name) {
        return authRoleDao.findByName(name);
    }

    /**
     * 插入
     * @param authRole
     * @return
     */
    @Override
    public boolean insertAuthRole(AuthRole authRole) {

        authRole.setCreateTime(new Date());
        authRole.setUpdateTime(new Date());

        return authRoleDao.insertAuthRole(authRole);
    }

    /**
     * 修改
     * @param authRole
     * @return
     */
    @Override
    public boolean updateAuthRole(AuthRole authRole) {
        authRole.setUpdateTime(new Date());
        return authRoleDao.updateAuthRole(authRole);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return authRoleDao.deleteById(id);
    }
}
