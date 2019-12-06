package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.SysCustomer;
import com.qingyun.service.SysCustomeService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SysCustomerController
 * @Date 2019/5/31 17:05
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("customer")
public class SysCustomerController {
    @Autowired
    SysCustomeService sysCustomeService;

    @AuthRuleAnnotation("sysCustomer/auth/index")
    @GetMapping("getCustomerInfo")
    public ResultVo getSysTextInfo(){
        try {
            SysCustomer sysCustomer=sysCustomeService.getCustomerInfo();
            return ResultVOUtils.success(sysCustomer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }


    @AuthRuleAnnotation("")
    @PostMapping("customerEdit")
    public ResultVo addOrEdit(SysCustomer sysCustomer) {
        return sysCustomeService.customerEdit(sysCustomer);
    }
}
