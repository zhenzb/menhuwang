package com.qingyun.service.impl;

import com.qingyun.mybatis.dao.MbCustomerMapper;
import com.qingyun.mybatis.model.KeyWordExample;
import com.qingyun.mybatis.model.MbCustomer;
import com.qingyun.mybatis.model.MbCustomerExample;
import com.qingyun.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 18:52
 */
@Service
public class CustomerServiceImple implements CustomerService {

    @Resource
    MbCustomerMapper mbCustomerMapper;
    @Override
    public void addCustomer(MbCustomer mbCustomer) {
        mbCustomerMapper.insertSelective(mbCustomer);
    }

    @Override
    public List<MbCustomer> getMbCustomerHDPage(Map<String, Object> map) {
        return mbCustomerMapper.getMbCustomerHDPage(map);
    }

    @Override
    public void updateCustomer(MbCustomer mbCustomer) {
        mbCustomerMapper.updateByPrimaryKeySelective(mbCustomer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        List<Long> list = new ArrayList<>();
        String[] split = customerId.split(",");
        for(String sp:split){
            list.add(Long.valueOf(sp));
        }
        MbCustomerExample mbCustomerExample = new MbCustomerExample();
        mbCustomerExample.createCriteria().andCustomerIdIn(list);
        mbCustomerMapper.deleteByExample(mbCustomerExample);
    }
}
