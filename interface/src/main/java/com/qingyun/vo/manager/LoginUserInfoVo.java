package com.qingyun.vo.manager;

import lombok.Data;

import java.util.List;

/**
 * 登录用户的信息视图
 */
@Data
public class LoginUserInfoVo {

    private Long id;
    private String username;
    private String avatar;
    // 权限列表
    private List<String> authRules;
}
