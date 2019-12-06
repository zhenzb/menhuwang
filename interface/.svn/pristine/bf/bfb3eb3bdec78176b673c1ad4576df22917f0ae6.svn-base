package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.SysBasics;
import com.qingyun.service.SysBasicsService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SysBasicsController
 * @Date 2019/6/1 13:40
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("basics")
public class SysBasicsController {
    @Autowired
    SysBasicsService sysBasicsService;

    /**
     *
     * @Description 基本设置信息
     * @author Mr.Chen
     * @param
     * @date 2019/6/1 13:43
     * @return com.qingyun.vo.result.ResultVo
     */
    @AuthRuleAnnotation("website/auth/basics")
    @GetMapping("getBasicsInfo")
    public ResultVo getBasicsInfo(){
        try {
            SysBasics sysBasics=sysBasicsService.getBasicsInfo();
            return ResultVOUtils.success(sysBasics);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
    /**
     *
     * @Description 基本设置信息修改
     * @author Mr.Chen
     * @param
     * @param basics
     * @date 2019/6/1 14:07
     * @return com.qingyun.vo.result.ResultVo
     */
    @AuthRuleAnnotation("")
    @PostMapping("basicsEdit")
    public ResultVo basicsEdit(SysBasics basics) {
        return sysBasicsService.basicsEdit(basics);
    }

}
