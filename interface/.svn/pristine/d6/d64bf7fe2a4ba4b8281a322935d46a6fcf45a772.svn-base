package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.form.MbIpQueryFrom;
import com.qingyun.mybatis.model.MbIp;
import com.qingyun.service.MbipService;
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
 * @Classname MbIpController
 * @Date 2019/6/1 14:45
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("mbIp")
public class MbIpController {
    @Autowired
    MbipService mbipService;

    @GetMapping("getList")
    public ResultVo getSysTextInfo(MbIpQueryFrom mbIpQueryFrom, EntyPage page){
        try {
            List<MbIp> mbIps= mbipService.getList(mbIpQueryFrom,page);
            PageSimpleVO<MbIp> sysProblemPageSimpleVO = new PageSimpleVO<MbIp>(page.getTotalNumber(), mbIps);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @AuthRuleAnnotation("")
    @PostMapping("ipstatsEdit")
    public ResultVo ipstatsEdit(Long ipid, Integer ipstats) {
        return mbipService.ipstatsEdit(ipid,ipstats);
    }

    @AuthRuleAnnotation("")
    @PostMapping("delOrNolanding")
    public ResultVo delOrNolanding(String ids, Integer type) {

        return mbipService.delOrNolanding(ids,type);
    }

}
