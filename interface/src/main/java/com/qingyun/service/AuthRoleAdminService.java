package com.qingyun.service;


import com.qingyun.mybatis.model.AuthRoleAdmin;

import java.util.List;

public interface AuthRoleAdminService {

    List<AuthRoleAdmin> listByAdminId(Long adminId);

    List<AuthRoleAdmin> listByAdminIdIn(List<Long> adminIds);

    List<AuthRoleAdmin> listByRoleId(Long roleId);

    int insertAuthRoleAdminAll(List<AuthRoleAdmin> authRoleAdminList);

    int insertRolesAdminIdAll(List<Long> roles, Long adminId);

    boolean deleteByAdminId(Long adminId);

    List<Long> findByRoleId(Long id);

    Integer insert(Long roleId,Long adminId);
}
