package com.qingyun.mybatis.model;

import lombok.Data;

import java.util.Date;

@Data
public class AuthAdmin {
    
    // 主键
    private Long id;
    // 昵称
    private String username;
    //真实姓名
    private String realname;
    // 登录密码
    private String password;
    // 手机号
    private String tel;
    // 邮箱
    private String email;
    // 头像
    private String avatar;
    // 性别
    private Integer sex;
    // 最后登录ip
    private String lastLoginIp;
    // 最后登录时间
    private Date lastLoginTime;
    // 邀请码
    private String invitationCode;
    // 省
    private int province;
    // 市
    private int city;
    // 是否BD
    private int isbd;
    // 备注
    private String remark;
    // 创建时间
    private Date createTime;
    // 状态
    private Integer status;
    //医院名称
    private String hospitalname;
    
}
