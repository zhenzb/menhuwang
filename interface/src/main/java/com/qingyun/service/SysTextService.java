package com.qingyun.service;

import com.qingyun.mybatis.model.SysText;
import com.qingyun.vo.result.ResultVo;

/**
 * @Classname SysVersionService
 * @Date 2019/1/11 18:59
 * @Created by Gavin
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface SysTextService {

    SysText getSysTextInfo();

    ResultVo textEdit(SysText sysText);
}
