package com.qingyun.service.impl;

import com.handongkeji.config.exception.JsonException;
import com.qingyun.service.AuthAdminService;
import com.qingyun.mybatis.dao.AuthAdminMapper;
import com.qingyun.mybatis.model.AuthAdmin;
import com.qingyun.vo.result.ResultEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {

    @Resource
    private AuthAdminMapper authAdminDao;

    @Override
    public List<AuthAdmin> listAdminPage(Map<String,Object> map) {
        if (map.size()==0) {
            return Collections.emptyList();
        }
        //int offset = (authAdminQueryForm.getPage() - 1) * authAdminQueryForm.getLimit();
        //PageHelper.offsetPage(offset, authAdminQueryForm.getLimit());
        return authAdminDao.listAdminHDPage(map);
    }

    @Override
    public AuthAdmin findByUserName(String userName) {
        return authAdminDao.findByUserName(userName);
    }

    /**
     * 根据id 获取需要的info
     * @param id
     * @return
     */
    @Override
    public AuthAdmin findById(Long id) {
        return authAdminDao.findById(id);
    }

    /**
     * 根据 id 获取密码字段
     * @param id
     * @return
     */
    @Override
    public AuthAdmin findPwdById(Long id) {
        return authAdminDao.findPwdById(id);
    }

    /**
     * 新增
     * @param authAdmin
     * @return
     */
    @Override
    public boolean insertAuthAdmin(AuthAdmin authAdmin) {

        if (authAdmin.getUsername() != null) {
            AuthAdmin byUserName = authAdminDao.findByUserName(authAdmin.getUsername());
            if (byUserName != null) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前管理员已存在");
            }
        }
        authAdmin.setCreateTime(new Date());
        return authAdminDao.insertAuthAdmin(authAdmin);
    }

    /**
     * 更新
     * @param authAdmin
     * @return
     */
    @Override
    public boolean updateAuthAdmin(AuthAdmin authAdmin) {

        if (authAdmin.getId() == null) {
            return false;
        }
        // 当用户名不为空时，检查是否存在
        if (authAdmin.getUsername() != null) {
            AuthAdmin byUserName = authAdminDao.findByUserName(authAdmin.getUsername());
            // 判断是否存在，剔除自己
            if (byUserName != null && !authAdmin.getId().equals(byUserName.getId())) {
                throw new JsonException(ResultEnum.DATA_REPEAT, "当前管理员已存在");
            }
        }

        return authAdminDao.updateAuthAdmin(authAdmin);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return authAdminDao.deleteById(id);
    }
}
