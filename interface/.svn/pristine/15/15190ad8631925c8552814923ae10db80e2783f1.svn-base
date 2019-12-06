package com.qingyun.service.impl;

import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.MbBackgroundPicMapper;
import com.qingyun.mybatis.model.MbBackgroundPic;
import com.qingyun.mybatis.model.MbBackgroundPicExample;
import com.qingyun.service.MbBackgroundPicService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname MbBackgroundPicServiceImpl
 * @Date 2019/5/30 10:05
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
public class MbBackgroundPicServiceImpl implements MbBackgroundPicService {
    @Autowired
    MbBackgroundPicMapper mbBackgroundPicMapper;
    @Override
    public MbBackgroundPic getBackgroudPic(int type) {
        MbBackgroundPicExample picExample=new MbBackgroundPicExample();
        picExample.createCriteria().andBackgroundtypeEqualTo(type);
        List<MbBackgroundPic> picList=mbBackgroundPicMapper.selectByExample(picExample);
        MbBackgroundPic backgroundPic=new MbBackgroundPic();
        if(picList.size()>0){
            backgroundPic=picList.get(0);
        }
        return backgroundPic;
    }

    @Override
    public ResultVo backgroudPicEdit(MbBackgroundPic mbBackgroundPic) {
        try {
            if(mbBackgroundPic.getBackgroundpicid()!=null){
                mbBackgroundPicMapper.updateByPrimaryKeySelective(mbBackgroundPic);
            }else {
                mbBackgroundPicMapper.insertSelective(mbBackgroundPic);
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

    }
}
