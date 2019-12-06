package com.qingyun.service;

import com.qingyun.mybatis.model.SysCustomer;
import com.qingyun.vo.result.ResultVo;

/**
 * @Classname SysCustomeService
 * @Date 2019/5/31 17:06
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface SysCustomeService {

    SysCustomer getCustomerInfo();

    ResultVo customerEdit(SysCustomer sysCustomer);
}
