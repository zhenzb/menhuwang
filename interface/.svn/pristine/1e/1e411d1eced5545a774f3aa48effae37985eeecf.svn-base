package com.qingyun.service;

import com.handongkeji.util.EntyPage;
import com.qingyun.mybatis.model.MbCooperativepartner;
import com.qingyun.vo.result.ResultVo;

import java.util.List;

/**
 * @Classname MbCooperativePartnerService
 * @Date 2019/5/30 18:19
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface MbCooperativePartnerService {
    /**
     *
     * @Description 获取合作伙伴集合
     * @author Mr.Chen
     * @param
     * @param text
     * @param page
     * @date 2019/5/30 18:21
     * @return java.util.List<com.qingyun.mybatis.model.MbCooperativepartner>
     */
    List<MbCooperativepartner> getList(String text, EntyPage page);

    ResultVo addOrEdit(MbCooperativepartner mbCooperativepartner);

    ResultVo deleteOrSort(String ids, String sort);
}
