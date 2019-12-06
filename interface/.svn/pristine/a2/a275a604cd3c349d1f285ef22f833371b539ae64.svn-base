package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.MbBackgroundPic;
import com.qingyun.mybatis.model.MbCooperativepartner;
import com.qingyun.service.MbBackgroundPicService;
import com.qingyun.service.MbCooperativePartnerService;
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
 * @Classname MbCooperativePartnerController
 * @Date 2019/5/30 18:18
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("cooperativePartner")
public class MbCooperativePartnerController {
    @Autowired
    MbCooperativePartnerService mbCooperativePartnerService;

    @Autowired
    MbBackgroundPicService mbBackgroundPicService;

    @AuthRuleAnnotation("cooperativePartner/auth/cooperativePartner")
    @GetMapping("getList")
    public ResultVo getList(String text, EntyPage page) {
        try {
            List<MbCooperativepartner> mbCooperativepartners= mbCooperativePartnerService.getList(text,page);
            PageSimpleVO<MbCooperativepartner> sysProblemPageSimpleVO = new PageSimpleVO<MbCooperativepartner>(page.getTotalNumber(), mbCooperativepartners);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
    @AuthRuleAnnotation("")
    @PostMapping("addOrEdit")
    public ResultVo addOrEdit(MbCooperativepartner mbCooperativepartner) {
        return mbCooperativePartnerService.addOrEdit(mbCooperativepartner);
    }

    @AuthRuleAnnotation("")
    @PostMapping("deleteOrSort")
    public ResultVo deleteOrSort(String ids,String sort) {
        return mbCooperativePartnerService.deleteOrSort(ids,sort);
    }

    @AuthRuleAnnotation("cooperativePartner/auth/background")
    @GetMapping("getBackgroudPic")
    public ResultVo getBackgroudPic(Integer type) {
        try {
            MbBackgroundPic backgroundPic =mbBackgroundPicService.getBackgroudPic(type);
            return ResultVOUtils.success(backgroundPic);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
