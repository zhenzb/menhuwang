package com.qingyun.form.MbUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Shirley
 * @date 2019/2/20 0020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MbBDForm {
    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //省
    private Integer provinceId;

    //市
    private Integer cityId;

    //其他查询条件（姓名、手机号、BD吗、医院）
    private String content;

    //权限问题，bd身份登录进来只能看见自己， bdID
    private String authadminid;
}
