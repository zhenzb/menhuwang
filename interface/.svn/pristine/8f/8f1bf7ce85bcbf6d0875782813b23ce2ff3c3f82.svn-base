package com.qingyun.service;

import com.qingyun.form.SysProblem.SysProblemSaveForm;
import com.qingyun.mybatis.model.SysProblem;
import com.qingyun.vo.result.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * @Classname SysProblemService
 * @Date 2019/1/16 13:52
 * @Created by Gavin
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface SysProblemService {
    /**
     *
     * @Description 获取问题列表
     * @author Mr.Chen
     * @param
     * @param map
     * @date 2019/5/30 14:17
     * @return java.util.List<com.qingyun.mybatis.model.SysProblem>
     */
    List<SysProblem> getList(Map<String, Object> map);
    /**
     *
     * @Description 添加或修改
     * @author Mr.Chen
     * @param
     * @param sysProblemSaveForm
     * @date 2019/5/30 14:16
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo addOrEdit(SysProblemSaveForm sysProblemSaveForm);
    /**
     *
     * @Description 删除或排序
     * @author Mr.Chen
     * @param
     * @param ids
     * @param sort
     * @date 2019/5/30 14:42
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo deleteOrSort(String ids, String sort);
}
