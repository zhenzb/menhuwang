package com.qingyun.form.AuthAdmin;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录验证
 */
@Data
public class LoginForm {

    @NotEmpty(message = "请输入账号")
    private String username;

    @NotEmpty(message = "请输入密码")
    private String pwd;

}
