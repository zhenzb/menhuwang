package com.qingyun.controller.manager;


import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.PermissionRuleTreeUtils;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.form.AuthRole.AuthRoleAuthForm;
import com.qingyun.form.AuthRole.AuthRoleQueryForm;
import com.qingyun.form.AuthRole.AuthRoleSaveForm;
import com.qingyun.mybatis.model.AuthPermission;
import com.qingyun.mybatis.model.AuthPermissionRule;
import com.qingyun.mybatis.model.AuthRole;
import com.qingyun.mybatis.model.AuthRoleAdmin;
import com.qingyun.service.AuthPermissionRuleService;
import com.qingyun.service.AuthPermissionService;
import com.qingyun.service.AuthRoleAdminService;
import com.qingyun.service.AuthRoleService;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.manager.AuthPermissionRuleMergeVo;
import com.qingyun.vo.manager.AuthRoleVo;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色相关
 */
@RestController
@RequestMapping("admin/role")
public class AuthRoleController {

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private AuthRoleAdminService authRoleAdminService;

    /**
     * 角色列表
     */
    @AuthRuleAnnotation("admin/auth/role/index")
    @GetMapping("index")
    public ResultVo index(@Valid AuthRoleQueryForm authRoleQueryForm, @Valid EntyPage page,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("authRoleQueryForm", authRoleQueryForm);
        map.put("page", page);
        List<AuthRole> authRoleList = authRoleService.listAdminPage(map);
        List<AuthRoleVo> authRoleVoList = authRoleList.stream().map(item -> {
            AuthRoleVo authRoleVO = new AuthRoleVo();
            BeanUtils.copyProperties(item, authRoleVO);
            return authRoleVO;
        }).collect(Collectors.toList());

//        PageInfo<AuthRole> pageInfo = new PageInfo<>(authRoleList);
//        PageSimpleVO<AuthRoleVo> pageSimpleVO = new PageSimpleVO<>();
//        //pageSimpleVO.setTotal(pageInfo.getTotal());
//        pageSimpleVO.setList(authRoleVoList);

        PageSimpleVO<AuthRoleVo> authRoleVOPageSimpleVO = new PageSimpleVO<>();
        authRoleVOPageSimpleVO.setTotalNumber(page.getTotalNumber());
        authRoleVOPageSimpleVO.setList(authRoleVoList);

        return ResultVOUtils.success(authRoleVOPageSimpleVO);
    }

    /**
     * 获取授权列表
     *
     * @param id
     * @return
     */
    @AuthRuleAnnotation("admin/auth/role/authList")
    @GetMapping("authList")
    public ResultVo authList(@RequestParam("id") Long id) {

        // 查询当前角色拥有的权限id
        List<AuthPermission> authPermissionList = authPermissionService.listByRoleId(id);
        List<Long> checkedKeys = authPermissionList.stream()
                .map(AuthPermission::getPermissionRuleId)
                .collect(Collectors.toList());

        // 查询所有权限规则
        List<AuthPermissionRule> authPermissionRuleList = authPermissionRuleService.listAll();
        List<AuthPermissionRuleMergeVo> merge = PermissionRuleTreeUtils.merge(authPermissionRuleList, 0L);

        Map<String, Object> restMap = new HashMap<>();
        restMap.put("list", merge);
        restMap.put("checkedKeys", checkedKeys);
        return ResultVOUtils.success(restMap);
    }

    /**
     *
     * @Description 授权操作
     * @author Gavin
     * @param authRoleAuthForm
     * @param bindingResult
     * @date 2019/4/29 19:11
     * @return com.qingyun.vo.manager.ResultVo
     *
     */
    @AuthRuleAnnotation("admin/auth/role/manager")
    @PostMapping("auths")
    public ResultVo auths(@RequestBody @Valid AuthRoleAuthForm authRoleAuthForm,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 先删除之前的授权
        authPermissionService.deleteByRoleId(authRoleAuthForm.getRoleId());

        List<AuthPermission> authPermissionList = authRoleAuthForm.getAuthRules().stream()
                .map(aLong -> {
                    AuthPermission authPermission = new AuthPermission();
                    authPermission.setRoleId(authRoleAuthForm.getRoleId());
                    authPermission.setPermissionRuleId(aLong);
                    authPermission.setType("admin");
                    return authPermission;
                }).collect(Collectors.toList());

        int i = authPermissionService.insertAuthPermissionAll(authPermissionList);

        //获取角色下的所有用户
        List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByRoleId(authRoleAuthForm.getRoleId());
        List<Long> ids = new ArrayList<>();
        if (authRoleAdmins != null && !authRoleAdmins.isEmpty()) {
            ids = authRoleAdmins.stream().map(AuthRoleAdmin::getAdminId).collect(Collectors.toList());
        }


        //此处需配置redis，不配置则注释掉

//        for (Long adminId : ids) {
//            //删除redis中的值
//            // 如果存在，从缓存中删除
//            String aarKey = String.format(RedisConstant.ADMIN_AUTH_RULES, adminId);
//            if (CacheUtils.hasKey(aarKey)) {
//                CacheUtils.delete(aarKey);
//            }
//        }

        return ResultVOUtils.success();
    }

    /**
     * 新增
     *
     * @param authRoleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/role/save")
    @PostMapping("save")
    public ResultVo save(@RequestBody @Valid AuthRoleSaveForm authRoleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AuthRole byName = authRoleService.findByName(authRoleSaveForm.getName());
        if (byName != null) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前角色已存在");
        }

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(authRoleSaveForm, authRole);

        boolean b = authRoleService.insertAuthRole(authRole);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        AuthRoleVo authRoleVO = new AuthRoleVo();
        BeanUtils.copyProperties(authRole, authRoleVO);

        return ResultVOUtils.success(authRoleVO);
    }

    /**
     * 编辑
     *
     * @param authRoleSaveForm
     * @param bindingResult
     * @return
     */
    @AuthRuleAnnotation("admin/auth/role/edit")
    @PostMapping("edit")
    public ResultVo edit(@RequestBody @Valid AuthRoleSaveForm authRoleSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authRoleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        // 检查是否存在当前角色
        AuthRole byName = authRoleService.findByName(authRoleSaveForm.getName());
        if (byName != null && !authRoleSaveForm.getId().equals(byName.getId())) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前角色已存在");
        }

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(authRoleSaveForm, authRole);

        boolean b = authRoleService.updateAuthRole(authRole);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除
     *
     * @param authRoleSaveForm
     * @return
     */
    @AuthRuleAnnotation("admin/auth/role/delete")
    @PostMapping("delete")
    public ResultVo delete(@RequestBody AuthRoleSaveForm authRoleSaveForm) {

        if (authRoleSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL);
        }

        //如果还有当前角色的管理员 提示先删除对应管理员
        List<Long> adminId = authRoleAdminService.findByRoleId(authRoleSaveForm.getId());
        if (!adminId.isEmpty()) {
            return ResultVOUtils.error(ResultEnum.PRE_ADMIN);
        }

        boolean b = authRoleService.deleteById(authRoleSaveForm.getId());
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        // 再删除之前的授权
        authPermissionService.deleteByRoleId(authRoleSaveForm.getId());

        return ResultVOUtils.success();
    }


}
