package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.SysText;
import com.qingyun.service.SysTextService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname SysTextController
 * @Date 2019/1/11 18:58
 * @Created by Gavin
 * ____              _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("text")
public class SysTextController {

    @Autowired
    private SysTextService sysTextService;


    @AuthRuleAnnotation("sysText/auth/index")
    @GetMapping("getSysTextInfo")
    public ResultVo getSysTextInfo(){
        try {
            SysText sysText=sysTextService.getSysTextInfo();
            return ResultVOUtils.success(sysText);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }


    @AuthRuleAnnotation("")
    @PostMapping("textEdit")
    public ResultVo addOrEdit(SysText sysText) {
        return sysTextService.textEdit(sysText);
    }



}
