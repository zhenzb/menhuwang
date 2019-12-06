package com.qingyun.service;


import com.handongkeji.util.EntyPage;
import com.qingyun.mybatis.model.AuthRole;

import java.util.List;
import java.util.Map;

public interface AuthRoleService {

    List<AuthRole> listAdminPage(Map<String, Object> authRoleQueryForm);

    List<AuthRole> listAuthAdminRoleHDPage(EntyPage page, Integer status);

    AuthRole findByName(String name);

    boolean insertAuthRole(AuthRole authRole);

    boolean updateAuthRole(AuthRole authRole);

    boolean deleteById(Long id);

}
