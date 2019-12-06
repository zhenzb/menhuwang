package com.qingyun.service.impl;

import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.MbShoppingmallMapper;
import com.qingyun.mybatis.model.MbShoppingmall;
import com.qingyun.mybatis.model.MbShoppingmallExample;
import com.qingyun.service.MbShoppingMallService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname MbShoppingMallServiceImpl
 * @Date 2019/5/30 16:51
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class MbShoppingMallServiceImpl implements MbShoppingMallService {
    @Autowired
    MbShoppingmallMapper mbShoppingmallMapper;

    /**
     *
     * @Description 获取商城信息
     * @author Mr.Chen
     * @param
     * @date 2019/5/30 16:52
     * @return com.qingyun.mybatis.model.MbShoppingmall
     */
    @Override
    public MbShoppingmall getShoppingMallInfo() {
        MbShoppingmallExample mbShoppingmallExample=new MbShoppingmallExample();
        List<MbShoppingmall> mbShoppingmalls= mbShoppingmallMapper.selectByExampleWithBLOBs(mbShoppingmallExample);
        MbShoppingmall mbShoppingmall=new MbShoppingmall();
        if(mbShoppingmalls.size()>0){
            mbShoppingmall=mbShoppingmalls.get(0);
        }
        return mbShoppingmall;
    }

    @Override
    public ResultVo shoppingMallEdit(MbShoppingmall mbShoppingmall) {
        try {
            if(mbShoppingmall.getShoppingmallid()!=null){
                mbShoppingmallMapper.updateByPrimaryKeySelective(mbShoppingmall);
            }else {
                mbShoppingmallMapper.insertSelective(mbShoppingmall);
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

    }
}
