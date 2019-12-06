package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.MbSoftware;
import com.qingyun.service.MbSoftwareService;
import com.qingyun.vo.MbSoftwareFrom;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname MbSoftwareController
 * @Date 2019/5/27 14:50
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("software")
public class MbSoftwareController {
    @Autowired
    MbSoftwareService mbSoftwareService;

    @AuthRuleAnnotation("software/auth/index")
    @GetMapping("getMbSoftware")
    public ResultVo getMbSoftware() {
        try {
            MbSoftware mbSoftwares= mbSoftwareService.getMbSoftware();
            return ResultVOUtils.success(mbSoftwares);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @AuthRuleAnnotation("")
    @PostMapping("addOrEdit")
    public ResultVo addOrEdit(MbSoftwareFrom mbSoftware) {
        return mbSoftwareService.addOrEdit(mbSoftware);
    }

    @AuthRuleAnnotation("")
    @PostMapping("deleteOrSort")
    public ResultVo deleteOrSort(String ids,String sort) {
        return mbSoftwareService.deleteOrSort(ids,sort);
    }
}
