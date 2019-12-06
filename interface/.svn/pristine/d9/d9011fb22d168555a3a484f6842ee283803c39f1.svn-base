package com.qingyun.mybatis.dao;

import com.qingyun.mybatis.model.AuthRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AuthRoleMapper {

    /**
     * 后台管理业务查询列表
     * @return 列表
     * @param authRoleQueryForm
     */
    List<AuthRole> listAdminHDPage(Map<String, Object> authRoleQueryForm);

    /**
     * 返回id,name 字段的列表
     * @return 列表
     * @param status
     */
    List<AuthRole> listAuthAdminRoleHDPage(Map<String, Object> status);

    AuthRole findByName(String name);

    /**
     * 插入
     * @param authAdmin
     * @return
     */
    boolean insertAuthRole(AuthRole authAdmin);
    
    /**
     * 更新
     * @param authAdmin
     * @return
     */
    boolean updateAuthRole(AuthRole authAdmin);
    
    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Long id);
    
}
