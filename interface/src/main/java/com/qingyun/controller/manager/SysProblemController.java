package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.form.SysProblem.SysProblemSaveForm;
import com.qingyun.mybatis.model.MbBackgroundPic;
import com.qingyun.mybatis.model.SysProblem;
import com.qingyun.service.MbBackgroundPicService;
import com.qingyun.service.SysProblemService;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SysProblemController
 * @Date 2019年1月16日13:42:33
 * @Created by Gavin
 * ____              _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@RestController
@RequestMapping("problem")
public class SysProblemController {

    @Autowired
    private SysProblemService sysProblemService;
    @Autowired
    MbBackgroundPicService mbBackgroundPicService;
    /**
     *
     * @Description 常见问题列表
     * @author Gavin
     * @param text
     * @param page
     * @date 2019/1/16 14:40
     * @return ResultVo
     */
    @AuthRuleAnnotation("serviceCentre/auth/problem")
    @GetMapping("getList")
    public ResultVo getList(String text, @Valid EntyPage page) {
        Map<String, Object> map = new HashMap<>();
        PageSimpleVO<SysProblem> sysProblemPageSimpleVO = null;
        try {
            map.put("text", text);
            map.put("page", page);
            List<SysProblem> sysProblems = sysProblemService.getList(map);
            sysProblemPageSimpleVO=new PageSimpleVO<>(page.getTotalNumber(),sysProblems);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        return ResultVOUtils.success(sysProblemPageSimpleVO);
    }
    /**
     *
     * @Description 添加或修改
     * @author Gavin
     * @param sysProblemSaveForm
     * @param bindingResult
     * @date 2019/1/17 9:31
     * @return com.qingyun.vo.manager.ResultVo
     */
    @AuthRuleAnnotation("")
    @PostMapping("addOrEdit")
    public ResultVo addOrEdit(@Valid SysProblemSaveForm sysProblemSaveForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
        return sysProblemService.addOrEdit(sysProblemSaveForm);

    }
    @AuthRuleAnnotation("")
    @PostMapping("deleteOrSort")
    public ResultVo deleteOrSort(String ids,String sort) {
        return sysProblemService.deleteOrSort(ids,sort);
    }

    @AuthRuleAnnotation("serviceCentre/auth/background")
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
