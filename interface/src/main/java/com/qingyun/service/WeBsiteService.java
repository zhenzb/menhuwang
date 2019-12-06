package com.qingyun.service;

import com.qingyun.vo.SysOpinionbackFrom;
import com.qingyun.vo.result.ResultVo;

/**
 * @Classname WeBsiteService
 * @Date 2019/6/4 14:03
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface WeBsiteService {
    /**
     *对进入此页面的Ip 进行校验
     * @param ipaddress
     * @return
     */
    ResultVo login(String ipaddress);
    /**
     * @Description 软件下载
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo getSoftware();
    /**
     * @Description 产品介绍
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo getProductIntroduction();
    /**
     * @Description 产品优势
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo getProduct();

    /**
     * @Description 服务中心
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo getServiceCentre();
    /**
     * @Description 合作伙伴
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo getCooperativePartner();
    /**
     *
     * @Description 用户反馈
     * @author Mr.Chen
     * @param
     * @param sysOpinionbackFrom
     * @date 2019/6/4 16:37
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo addOpinionBack(SysOpinionbackFrom sysOpinionbackFrom);
}
