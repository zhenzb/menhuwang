package com.qingyun.controller.manager;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.KeyWord;
import com.qingyun.mybatis.model.MbCustomer;
import com.qingyun.service.CustomerService;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/28 0028 09:13
 */

@Slf4j
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * 获取客服列表
     * @param customerName
     * @param page
     * @return
     */
    @GetMapping("getCustomerList")
    public ResultVo getCustomerList(String customerName,String customerStatus, EntyPage page){
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("customerName", customerName);
            map.put("customerStatus",customerStatus);
            map.put("page", page);
            List<MbCustomer> mbCustomerHDPage = customerService.getMbCustomerHDPage(map);
            PageSimpleVO<MbCustomer> sysProblemPageSimpleVO = new PageSimpleVO<MbCustomer>(page.getTotalNumber(), mbCustomerHDPage);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 添加客服
     * @param mbCustomer
     * @return
     */
    @PostMapping("addCustomer")
    public ResultVo addCustomer(MbCustomer mbCustomer){
        try{
            if(mbCustomer.getCustomerId() == null){
                mbCustomer.setCreateTime(new Date());
                customerService.addCustomer(mbCustomer);
            }else {
                mbCustomer.setEditTime(new Date());
                customerService.updateCustomer(mbCustomer);
            }
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 删除客服
     * @param ids
     * @return
     */
    @PostMapping("deleteCustomer")
    public ResultVo deleteCustomer(String ids){
        try{
            customerService.deleteCustomer(ids);
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

}
