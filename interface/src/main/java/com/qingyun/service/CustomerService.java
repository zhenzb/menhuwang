package com.qingyun.service;

import com.qingyun.mybatis.model.MbCustomer;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 18:39
 */
public interface CustomerService {

    void addCustomer(MbCustomer mbCustomer);

    List<MbCustomer> getMbCustomerHDPage(Map<String,Object> map);

    void updateCustomer(MbCustomer mbCustomer);

    void deleteCustomer(String customerId);


}
