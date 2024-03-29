package com.qingyun.form.AuthAdmin;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 修改密码的表单
 */
@Data
public class UpdatePasswordForm {

    @NotNull(message = "参数错误！")
    private Long adminId;

    @NotEmpty(message = "请输入旧密码")
    private String oldPassword;

    @NotEmpty(message = "请输入新密码")
    private String newPassword;

}
