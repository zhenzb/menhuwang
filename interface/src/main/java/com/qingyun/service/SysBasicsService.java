package com.qingyun.service;

import com.qingyun.mybatis.model.SysBasics;
import com.qingyun.vo.result.ResultVo;

/**
 * @Classname SysBasicsService
 * @Date 2019/6/1 13:41
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface SysBasicsService {
    /**
     *
     * @Description 基本设置信息
     * @author Mr.Chen
     * @param
     * @date 2019/6/1 13:43
     * @return com.qingyun.vo.result.ResultVo
     */
    SysBasics getBasicsInfo();
    /**
     *
     * @Description 基本设置信息修改
     * @author Mr.Chen
     * @param
     * @param basics
     * @date 2019/6/1 14:07
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo basicsEdit(SysBasics basics);
}
