package com.qingyun.service.impl;

import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.MbBackgroundPicMapper;
import com.qingyun.mybatis.dao.MbProductIntroductionMapper;
import com.qingyun.mybatis.model.*;
import com.qingyun.service.MbProductIntroductionService;
import com.qingyun.vo.manager.MbProductIntroductionVo;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @Classname MbProductIntroductionServiceImpl
 * @Date 2019/5/28 16:57
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
public class MbProductIntroductionServiceImpl implements MbProductIntroductionService {
    @Autowired
    MbBackgroundPicMapper mbBackgroundPicMapper;
    @Autowired
    MbProductIntroductionMapper mbProductIntroductionMapper;

    @Override
    public MbProductIntroductionVo getProductIntroduction() {
        MbProductIntroductionExample example = new MbProductIntroductionExample();
        List<MbProductIntroduction> introductions=mbProductIntroductionMapper.selectByExampleWithBLOBs(example);
        MbProductIntroductionVo vo=new MbProductIntroductionVo();
        if(introductions.size()>0){
            BeanUtils.copyProperties(introductions.get(0),vo);
        }
        MbBackgroundPicExample picExample=new MbBackgroundPicExample();
        picExample.createCriteria().andBackgroundtypeEqualTo(0);
        List<MbBackgroundPic> picList=mbBackgroundPicMapper.selectByExample(picExample);
        if(picList.size()>0){
            BeanUtils.copyProperties(picList.get(0),vo);
        }
        return vo;
    }

    @Override
    public ResultVo introductionEdit(MbProductIntroductionVo vo) {
        try {
            MbProductIntroduction introduction=new MbProductIntroduction();
            BeanUtils.copyProperties(vo,introduction);
            if(introduction.getId()!=null){
                mbProductIntroductionMapper.updateByPrimaryKeySelective(introduction);
            }else {
                mbProductIntroductionMapper.insertSelective(introduction);
            }
            MbBackgroundPicExample picExample=new MbBackgroundPicExample();
            picExample.createCriteria().andBackgroundtypeEqualTo(0);
            List<MbBackgroundPic> picList=mbBackgroundPicMapper.selectByExample(picExample);
            MbBackgroundPic backgroundPic =null;
            if(picList.size()>0){
                backgroundPic=picList.get(0);
                BeanUtils.copyProperties(vo,backgroundPic);
                mbBackgroundPicMapper.updateByPrimaryKeySelective(backgroundPic);
            }else {
                backgroundPic =new MbBackgroundPic();
                BeanUtils.copyProperties(vo,backgroundPic);
                mbBackgroundPicMapper.insertSelective(backgroundPic);
            }
            return ResultVOUtils.success();
        } catch (BeansException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
