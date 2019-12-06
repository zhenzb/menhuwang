package com.qingyun.form.AuthAdmin;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理员的提交保存表单
 */
@Data
public class AuthAdminSaveForm {
    // id
    private Long id;
    //ids
    private String ids;
    // 昵称
    @NotEmpty(message = "请输入用户名")
    private String username;
    // 登录密码
    private String password;
    // 状态
    @NotNull(message = "请选择状态")
    private Integer status;
    // 角色ids
    private List<Long> roles;
}
