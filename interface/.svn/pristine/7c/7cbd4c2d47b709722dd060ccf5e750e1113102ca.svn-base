package com.qingyun.controller.manager;

import com.handongkeji.config.auth.AuthRuleAnnotation;
import com.handongkeji.config.exception.JsonException;
import com.handongkeji.util.manager.IpUtils;
import com.handongkeji.util.manager.JwtUtils;
import com.handongkeji.util.manager.PasswordUtils;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.service.AuthAdminService;
import com.qingyun.service.AuthLoginService;
import com.qingyun.form.AuthAdmin.LoginForm;
import com.qingyun.form.AuthAdmin.UpdatePasswordForm;
import com.qingyun.mybatis.model.AuthAdmin;
import com.qingyun.vo.manager.LoginUserInfoVo;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录相关
 */
@RestController
@Slf4j
@RequestMapping("admin/login")
public class LoginController {

    @Autowired
    private AuthLoginService authLoginService;

    @Autowired
    private AuthAdminService authAdminService;

    /**
     * 用户登录
     * @return
     */
    @PostMapping(value = "index")
    public ResultVo index(@RequestBody @Valid LoginForm loginForm,
                          BindingResult bindingResult,
                          HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        AuthAdmin authAdmin = authAdminService.findByUserName(loginForm.getUsername());
        if (authAdmin == null) {
            throw new JsonException(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        if (!PasswordUtils.authAdminPwd(loginForm.getPwd()).equals(authAdmin.getPassword())) {
            throw new JsonException(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        if(authAdmin.getStatus()==0){
            throw new JsonException(ResultEnum.DATA_NOT, "账号被禁用");
        }
        if(authAdmin.getStatus()==2){
            throw new JsonException(ResultEnum.DATA_NOT, "账号未验证");
        }
        
        // 更新登录状态
        AuthAdmin authAdminUp = new AuthAdmin();
        authAdminUp.setId(authAdmin.getId());
        authAdminUp.setLastLoginTime(new Date());
        authAdminUp.setLastLoginIp(IpUtils.getIpAddr(request));
        authAdminService.updateAuthAdmin(authAdminUp);

        // 登录成功后获取权限，这里面会设置到缓存
        authLoginService.listRuleByAdminId(authAdmin.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("admin_id", authAdmin.getId());
        String token = JwtUtils.createToken(claims, 43200L); // 半天后过期
        //String token = JwtUtils.createToken(claims, 60L); // 半天后过期

        Map<String, Object> map = new HashMap<>();
        map.put("id", authAdmin.getId());
        map.put("token", token);

        return ResultVOUtils.success(map);
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @AuthRuleAnnotation("")
    @GetMapping("userInfo")
    public ResultVo userInfo(HttpServletRequest request) {
        String adminId = request.getHeader("X-Adminid");
        Long id = Long.valueOf(adminId);

        AuthAdmin authAdmin = authAdminService.findById(id);

        List<String> authRules = authLoginService.listRuleByAdminId(authAdmin.getId());

        LoginUserInfoVo loginUserInfoVO = new LoginUserInfoVo();
        BeanUtils.copyProperties(authAdmin, loginUserInfoVO);
        loginUserInfoVO.setAuthRules(authRules);

        return ResultVOUtils.success(loginUserInfoVO);
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("out")
    public ResultVo out(){
        return ResultVOUtils.success();
    }

    /**
     * 修改密码
     * @return
     */
    @AuthRuleAnnotation("") // 需要登录验证,但是不需要权限验证时,value 值填空字符串
    @PostMapping("password")
    public ResultVo password(@RequestBody @Valid UpdatePasswordForm updatePasswordForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        AuthAdmin authAdmin = authAdminService.findPwdById(updatePasswordForm.getAdminId());
        if (authAdmin == null) {
            throw new JsonException(ResultEnum.DATA_NOT);
        }
        String newPwd = PasswordUtils.authAdminPwd(updatePasswordForm.getOldPassword());
        // 旧密码不对
        if (authAdmin.getPassword() != null
                && !authAdmin.getPassword().equals(newPwd)) {
            throw new JsonException(ResultEnum.DATA_NOT, "旧密码匹配失败");
        }

//        AuthAdmin authAdminUp = new AuthAdmin();
        authAdmin.setPassword(PasswordUtils.authAdminPwd(updatePasswordForm.getNewPassword()));

        boolean b = authAdminService.updateAuthAdmin(authAdmin);
        if (b) {
            return ResultVOUtils.success();
        }

        return ResultVOUtils.error(ResultEnum.DATA_CHANGE);
    }
}
