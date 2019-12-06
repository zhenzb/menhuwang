package com.qingyun.service.impl;

import com.handongkeji.util.StringUtil;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.form.SysProblem.SysProblemSaveForm;
import com.qingyun.mybatis.dao.SysProblemMapper;
import com.qingyun.mybatis.model.SysProblem;
import com.qingyun.service.SysProblemService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname SysProblemServiceImpl
 * @Date 2019/1/16 13:52
 * @Created by Gavin
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class SysProblemServiceImpl implements SysProblemService {

    @Autowired
    private SysProblemMapper sysProblemMapper;
    /**
     *
     * @Description 获取问题列表
     * @author Mr.Chen
     * @param
     * @param map
     * @date 2019/5/30 14:17
     * @return java.util.List<com.qingyun.mybatis.model.SysProblem>
     */
    @Override
    public List<SysProblem> getList(Map<String, Object> map) {
       return sysProblemMapper.getListByHDPage(map);
    }
    /**
     *
     * @Description 添加或修改
     * @author Mr.Chen
     * @param
     * @param sysProblemSaveForm
     * @date 2019/5/30 14:16
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo addOrEdit(SysProblemSaveForm sysProblemSaveForm) {
        try {
            SysProblem sysProblem = new SysProblem();
            BeanUtils.copyProperties(sysProblemSaveForm,sysProblem);
            if(sysProblem.getProblemid()!=null){
                sysProblemMapper.updateByPrimaryKeySelective(sysProblem);
            }else {
                sysProblem.setCreatetime(new Date());
                sysProblemMapper.insertSelective(sysProblem);
            }
            return ResultVOUtils.success();
        } catch (BeansException e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
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
    @Override
    public ResultVo deleteOrSort(String ids, String sort) {
        try {
            String [] array=ids.split(",");
            SysProblem sysProblem=null;
            if(StringUtil.isNullOrEmpty(sort)){
                for (String id:array) {
                    sysProblem=new SysProblem();
                    sysProblem.setProblemid(Long.parseLong(id));
                    sysProblem.setIsdel(1);
                    sysProblemMapper.updateByPrimaryKeySelective(sysProblem);
                }
            }else{
                for (String id:array) {
                    sysProblem=new SysProblem();
                    sysProblem.setProblemid(Long.parseLong(id));
                    sysProblem.setProblemsort(Integer.parseInt(sort));
                    sysProblemMapper.updateByPrimaryKeySelective(sysProblem);
                }
            }
            return  ResultVOUtils.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
