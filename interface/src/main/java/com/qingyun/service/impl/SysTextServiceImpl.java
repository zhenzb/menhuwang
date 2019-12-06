package com.qingyun.service.impl;

import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.SysTextMapper;
import com.qingyun.mybatis.model.SysText;
import com.qingyun.mybatis.model.SysTextExample;
import com.qingyun.service.SysTextService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname SysVersionServiceImpl
 * @Date 2019/1/11 18:59
 * @Created by Gavin
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class SysTextServiceImpl implements SysTextService {

    @Autowired
    private SysTextMapper sysTextMapper;

    @Override
    public SysText getSysTextInfo() {
        SysTextExample example=new SysTextExample();
        List<SysText> texts=sysTextMapper.selectByExample(example);
        SysText sysText=new SysText();
        if(texts.size()>0){
            sysText=texts.get(0);
        }
        return sysText;
    }

    @Override
    public ResultVo textEdit(SysText sysText) {
        try {
            if(sysText.getTextid()!=null){
                sysTextMapper.updateByPrimaryKeySelective(sysText);
            }else {
                sysTextMapper.insertSelective(sysText);
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
