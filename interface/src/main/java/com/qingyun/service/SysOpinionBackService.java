package com.qingyun.service;

import com.handongkeji.util.EntyPage;
import com.qingyun.mybatis.model.SysOpinionback;
import com.qingyun.vo.result.ResultVo;

import java.util.List;

/**
 * @Classname SysOpinionBackService
 * @Date 2019/5/30 15:10
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface SysOpinionBackService {
    /**
     *
     * @Description 获取反馈集合
     * @author Mr.Chen
     * @param
     * @param text
     * @param page
     * @date 2019/5/30 15:38
     * @return java.util.List<com.qingyun.mybatis.model.SysOpinionback>
     */
    List<SysOpinionback> getList(String text, EntyPage page);
    /**
     *
     * @Description 删除反馈
     * @author Mr.Chen
     * @param
     * @param ids
     * @date 2019/5/30 16:18
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo delOpinionback(String ids);
}
