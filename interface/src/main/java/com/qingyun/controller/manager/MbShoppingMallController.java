package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.MbShoppingmall;
import com.qingyun.service.MbShoppingMallService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname MbShoppingMallController
 * @Date 2019/5/30 16:49
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("shoppingMall")
public class MbShoppingMallController {
    @Autowired
    MbShoppingMallService mbShoppingMallService;

    /**
     *
     * @Description 获取商城信息
     * @author Mr.Chen
     * @param
     * @date 2019/5/30 16:52
     * @return com.qingyun.vo.result.ResultVo
     */
    @AuthRuleAnnotation("mbShoppingMall/auth/index")
    @GetMapping("getShoppingMallInfo")
    public ResultVo getShoppingMallInfo() {
        try {
            MbShoppingmall mbShoppingmall = mbShoppingMallService.getShoppingMallInfo();
            return ResultVOUtils.success(mbShoppingmall);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @AuthRuleAnnotation("")
    @PostMapping("shoppingMallEdit")
    public ResultVo shoppingMallEdit(MbShoppingmall mbShoppingmall) {
        return mbShoppingMallService.shoppingMallEdit(mbShoppingmall);
    }
}
