package com.qingyun.mybatis.model;

import lombok.Data;

/**
 * 用户角色对应表
 */
@Data
public class AuthRoleAdmin {
  private Long id;
  private Long roleId;
  private Long adminId;

}
