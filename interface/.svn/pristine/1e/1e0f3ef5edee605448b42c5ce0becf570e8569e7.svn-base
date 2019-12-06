package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.SysOpinionback;
import com.qingyun.service.SysOpinionBackService;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname SysOpinionBackController
 * @Date 2019/5/30 15:09
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("opinionBack")
public class SysOpinionBackController {
    @Autowired
    SysOpinionBackService sysOpinionBackService;

    @AuthRuleAnnotation("serviceCentre/auth/opinionBack")
    @GetMapping("getList")
    public ResultVo getList(String text, EntyPage page) {
        try {
            List<SysOpinionback> opinionbacks= sysOpinionBackService.getList(text,page);
            PageSimpleVO<SysOpinionback> sysProblemPageSimpleVO = new PageSimpleVO<SysOpinionback>(page.getTotalNumber(), opinionbacks);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
    @AuthRuleAnnotation("")
    @PostMapping("delOpinionback")
    public ResultVo deleteOrSort(String ids) {
        return sysOpinionBackService.delOpinionback(ids);
    }
}
