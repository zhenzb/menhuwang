package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.manager.PermissionRuleTreeUtils;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.service.AuthPermissionRuleService;
import com.qingyun.form.AuthPermission.AuthPermissionRuleSaveForm;
import com.qingyun.mybatis.model.AuthPermissionRule;
import com.qingyun.vo.manager.AuthPermissionRuleMergeVo;
import com.qingyun.vo.manager.AuthPermissionRuleVo;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限规则相关
 */
@RestController
@RequestMapping("admin/permission_rule")
public class AuthPermissionRuleController {

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;

    /**
     * 列表
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/index")
    @GetMapping("index")
    public ResultVo index() {


        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll();
        List<AuthPermissionRuleMergeVo> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList,0L);

        Map<String,Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        return ResultVOUtils.success(restMap);
    }

    /**
     * 新增
     * @param authPermissionRuleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/save")
    @PostMapping("save")
    public ResultVo save(@RequestBody @Valid AuthPermissionRuleSaveForm authPermissionRuleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authPermissionRuleSaveForm.getPid() == null) {
            authPermissionRuleSaveForm.setPid(0L); // 默认设置
        }
        AuthPermissionRule authPermissionRule = new AuthPermissionRule();
        BeanUtils.copyProperties(authPermissionRuleSaveForm, authPermissionRule);

        boolean b = authPermissionRuleService.insertAuthPermissionRule(authPermissionRule);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        AuthPermissionRuleVo authPermissionRuleVO = new AuthPermissionRuleVo();
        BeanUtils.copyProperties(authPermissionRule, authPermissionRuleVO);

        return ResultVOUtils.success(authPermissionRuleVO);
    }

    /**
     * 编辑
     * @param authPermissionRuleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/edit")
    @PostMapping("edit")
    public ResultVo edit(@RequestBody @Valid AuthPermissionRuleSaveForm authPermissionRuleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authPermissionRuleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        authPermissionRuleSaveForm.setPid(null); // 不能修改父级 pid

        AuthPermissionRule authPermissionRule = new AuthPermissionRule();
        BeanUtils.copyProperties(authPermissionRuleSaveForm, authPermissionRule);

        boolean b = authPermissionRuleService.updateAuthPermissionRule(authPermissionRule);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     * @param authPermissionRuleSaveForm
     * @return
     */
    @AuthRuleAnnotation("admin/auth/permission_rule/delete")
    @PostMapping("delete")
    public ResultVo delete(@RequestBody AuthPermissionRuleSaveForm authPermissionRuleSaveForm) {

        if (authPermissionRuleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        boolean b = authPermissionRuleService.deleteById(authPermissionRuleSaveForm.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }


}
