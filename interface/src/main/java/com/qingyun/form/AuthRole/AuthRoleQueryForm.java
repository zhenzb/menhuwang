package com.qingyun.form.AuthRole;

import com.handongkeji.util.EntyPage;
import lombok.Data;

/**
 * 角色的查询表单
 */
@Data
public class AuthRoleQueryForm {
    private String name;

    private Integer status;

    private EntyPage page;
}
