package com.qingyun.mybatis.model;

import lombok.Data;

/**
 * 权限授权表
 */
@Data
public class AuthPermission {

    private Long id;

    private Long roleId;

    private Long permissionRuleId;

    private String type;

}
