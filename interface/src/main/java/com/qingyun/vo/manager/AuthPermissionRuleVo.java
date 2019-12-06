package com.qingyun.vo.manager;

import lombok.Data;

/**
 * 权限规则的视图
 */
@Data
public class AuthPermissionRuleVo {

    private Long id;
    private Long pid;
    private String name;
    private String title;
    private Long status;
    private String condition;
    private Long listorder;

}
