package com.qingyun.service.impl;

import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.SysBasicsMapper;
import com.qingyun.mybatis.model.SysBasics;
import com.qingyun.mybatis.model.SysBasicsExample;
import com.qingyun.service.SysBasicsService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname SysBasicsServiceImpl
 * @Date 2019/6/1 13:41
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
public class SysBasicsServiceImpl implements SysBasicsService {
    @Autowired
    SysBasicsMapper sysBasicsMapper;
    /**
     *
     * @Description 基本设置信息
     * @author Mr.Chen
     * @param
     * @date 2019/6/1 13:43
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public SysBasics getBasicsInfo() {
        SysBasicsExample example=new SysBasicsExample();
        List<SysBasics> sysBasics=sysBasicsMapper.selectByExample(example);
        SysBasics basics=new SysBasics();
        if(sysBasics.size()>0){
            basics=sysBasics.get(0);
        }
        return basics;

    }
    /**
     *
     * @Description 基本设置信息修改
     * @author Mr.Chen
     * @param
     * @param basics
     * @date 2019/6/1 14:07
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo basicsEdit(SysBasics basics) {
        try {
            if(basics.getId()!=null){
                sysBasicsMapper.updateByPrimaryKeySelective(basics);
            }else {
                sysBasicsMapper.insertSelective(basics);
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
