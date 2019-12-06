package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.PasswordUtils;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.service.AuthAdminService;
import com.qingyun.service.AuthRoleAdminService;
import com.qingyun.service.AuthRoleService;
import com.qingyun.form.AuthAdmin.AuthAdminQueryForm;
import com.qingyun.form.AuthAdmin.AuthAdminSaveForm;
import com.qingyun.mybatis.model.AuthAdmin;
import com.qingyun.mybatis.model.AuthRole;
import com.qingyun.mybatis.model.AuthRoleAdmin;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.manager.AuthAdminRoleVo;
import com.qingyun.vo.manager.AuthAdminVo;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
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
 * 管理员相关
 */
@RestController
@RequestMapping("admin")
public class AuthAdminController {

    @Resource
    private AuthAdminService authAdminService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthRoleAdminService authRoleAdminService;

    /**
     * 获取管理员列表
     */
    @AuthRuleAnnotation("admin/auth/admin/index")
    @GetMapping("index")
    public ResultVo index(@Valid AuthAdminQueryForm authAdminQueryForm, @Valid EntyPage page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authAdminQueryForm.getRoleId() != null) {
            List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByRoleId(authAdminQueryForm.getRoleId());
            List<Long> ids = new ArrayList<>();
            if (authRoleAdmins != null && !authRoleAdmins.isEmpty()) {
                ids = authRoleAdmins.stream().map(AuthRoleAdmin::getAdminId).collect(Collectors.toList());
            }
            authAdminQueryForm.setIds(ids);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("authAdminQueryForm", authAdminQueryForm);
        map.put("page", page);
        List<AuthAdmin> authAdminList = authAdminService.listAdminPage(map);

        // 查询所有的权限
        List<Long> adminIds = authAdminList.stream().map(AuthAdmin::getId).collect(Collectors.toList());
        List<AuthRoleAdmin> authRoleAdminList = authRoleAdminService.listByAdminIdIn(adminIds);

        List<AuthAdminVo> authAdminVoList = null;
        try {
            // 视图列表
            authAdminVoList = authAdminList.stream().map(item -> {
                AuthAdminVo authAdminVo = new AuthAdminVo();
                BeanUtils.copyProperties(item, authAdminVo);
                List<Long> roles = authRoleAdminList.stream()
                        .filter(authRoleAdmin -> authAdminVo.getId().equals(authRoleAdmin.getAdminId()))
                        .map(AuthRoleAdmin::getRoleId)
                        .collect(Collectors.toList());
                authAdminVo.setRoles(roles);
                return authAdminVo;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }


        PageSimpleVO<AuthAdminVo> authAdminPageSimpleVO = new PageSimpleVO<>();
        authAdminPageSimpleVO.setTotalNumber(page.getTotalNumber());
        authAdminPageSimpleVO.setList(authAdminVoList);

        return ResultVOUtils.success(authAdminPageSimpleVO);

    }


    /**
     * 获取角色列表
     */
    @AuthRuleAnnotation("")
    @GetMapping("roleList")
    public ResultVo roleList(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                             @RequestParam(value = "pageSize", defaultValue = "100") Integer pageSize) {

        EntyPage page = new EntyPage(currentPage, pageSize);
        List<AuthRole> authRoleList = authRoleService.listAuthAdminRoleHDPage(page, null);
        PageSimpleVO<AuthAdminRoleVo> pageSimpleVO = new PageSimpleVO<>();
        pageSimpleVO.setTotalNumber(page.getTotalNumber());
        List<AuthAdminRoleVo> authAdminRoleVOS = authRoleList.stream().map(e -> {
            AuthAdminRoleVo authAdminRoleVO = new AuthAdminRoleVo();
            BeanUtils.copyProperties(e, authAdminRoleVO);
            return authAdminRoleVO;
        }).collect(Collectors.toList());
        pageSimpleVO.setList(authAdminRoleVOS);

        return ResultVOUtils.success(pageSimpleVO);

    }


    /**
     * 新增
     *
     * @return
     */
    /*@AuthRuleAnnotation("admin/auth/admin/save")*/
    @AuthRuleAnnotation("")
    @PostMapping("save")
    public ResultVo save(@RequestBody @Valid AuthAdminSaveForm authAdminSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 检查是否存在相同名称的管理员
        AuthAdmin byUserName = authAdminService.findByUserName(authAdminSaveForm.getUsername());
        if (byUserName != null) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前管理员已存在");
        }


        if (authAdminSaveForm.getRoles() != null) {
            //检查选择的角色中是否存在超级管理员
            for (Long role : authAdminSaveForm.getRoles()) {
                if (role == 1) {
                    return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "超级管理员不可再添加");
                }
            }
        }


        AuthAdmin authAdmin = new AuthAdmin();
        BeanUtils.copyProperties(authAdminSaveForm, authAdmin);
        authAdmin.setIsbd(0);
        if (authAdmin.getPassword() != null) {
            authAdmin.setPassword(PasswordUtils.authAdminPwd(authAdmin.getPassword()));
        }

        boolean b = authAdminService.insertAuthAdmin(authAdmin);

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        // 插入角色
        if (authAdminSaveForm.getRoles() != null) {
            authRoleAdminService.insertRolesAdminIdAll(authAdminSaveForm.getRoles(), authAdmin.getId());
        }

        AuthAdminVo authAdminVo = new AuthAdminVo();
        BeanUtils.copyProperties(authAdmin, authAdminVo);
        authAdminVo.setRoles(authAdminSaveForm.getRoles());

        return ResultVOUtils.success(authAdminVo);
    }

    /**
     * 修改
     *
     * @return
     */
    /*@AuthRuleAnnotation("admin/auth/admin/edit")*/
    @AuthRuleAnnotation("")
    @PostMapping("edit")
    public ResultVo edit(@RequestBody @Valid AuthAdminSaveForm authAdminSaveForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (authAdminSaveForm.getId() == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        // 检查是否存在除了当前管理员的其它名称的管理员
        AuthAdmin byUserName = authAdminService.findByUserName(authAdminSaveForm.getUsername());
        if (byUserName != null && !authAdminSaveForm.getId().equals(byUserName.getId())) {
            return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "当前管理员已存在");
        }

        AuthAdmin authAdmin = new AuthAdmin();
        BeanUtils.copyProperties(authAdminSaveForm, authAdmin);
        if (authAdmin.getPassword() != null && !StringUtils.isEmpty(authAdmin.getPassword())) {
            authAdmin.setPassword(PasswordUtils.authAdminPwd(authAdmin.getPassword()));
        }

        boolean b = authAdminService.updateAuthAdmin(authAdmin);

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        // 修改角色
        if (authAdminSaveForm.getRoles() != null) {
            // 先删除之前的
            authRoleAdminService.deleteByAdminId(authAdmin.getId());
            authRoleAdminService.insertRolesAdminIdAll(authAdminSaveForm.getRoles(), authAdmin.getId());
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除(支持批量删除)
     *
     * @return
     */
    @AuthRuleAnnotation("admin/auth/admin/delete")
    @PostMapping("delete")
    public ResultVo delete(@RequestBody AuthAdminSaveForm authAdminSaveForm) {

        if (authAdminSaveForm.getIds() == null || authAdminSaveForm.getIds().length() == 0) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误！");
        }

        String[] ids = authAdminSaveForm.getIds().split(",");

        for (String id : ids) {
            boolean b = authAdminService.deleteById(Long.parseLong(id));
            if (!b) {
                return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
            }
            // 先删除之前的角色
            authRoleAdminService.deleteByAdminId(Long.parseLong(id));
        }

        return ResultVOUtils.success();
    }


}
