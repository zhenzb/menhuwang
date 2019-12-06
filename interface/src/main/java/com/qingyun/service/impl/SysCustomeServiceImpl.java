package com.qingyun.service.impl;

import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.SysCustomerMapper;
import com.qingyun.mybatis.model.SysCustomer;
import com.qingyun.mybatis.model.SysCustomerExample;
import com.qingyun.service.SysCustomeService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname SysCustomeServiceImpl
 * @Date 2019/5/31 17:06
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class SysCustomeServiceImpl implements SysCustomeService {
    @Autowired
    SysCustomerMapper sysCustomerMapper;
    @Override
    public SysCustomer getCustomerInfo() {
        SysCustomerExample example=new SysCustomerExample();
        List<SysCustomer> sysCustomers=sysCustomerMapper.selectByExample(example);
        SysCustomer sysCustomer=new SysCustomer();
        if(sysCustomers.size()>0){
            sysCustomer=sysCustomers.get(0);
        }
        return sysCustomer;

    }

    @Override
    public ResultVo customerEdit(SysCustomer sysCustomer) {
        try {
            if(sysCustomer.getCustomerid()!=null){
                sysCustomerMapper.updateByPrimaryKeySelective(sysCustomer);
            }else {
                sysCustomerMapper.insertSelective(sysCustomer);
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
