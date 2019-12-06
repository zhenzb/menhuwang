package com.qingyun.service.impl;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.SysOpinionbackMapper;
import com.qingyun.mybatis.model.SysOpinionback;
import com.qingyun.service.SysOpinionBackService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SysOpinionBackServiceImpl
 * @Date 2019/5/30 15:10
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class SysOpinionBackServiceImpl implements SysOpinionBackService {
    @Autowired
    SysOpinionbackMapper sysOpinionbackMapper;
    /**
     *
     * @Description 获取反馈集合
     * @author Mr.Chen
     * @param
     * @param text
     * @param page
     * @date 2019/5/30 15:38
     * @return java.util.List<com.qingyun.mybatis.model.SysOpinionback>
     */
    @Override
    public List<SysOpinionback> getList(String text, EntyPage page) {
        Map<String,Object> map=new HashMap<>();
        map.put("text",text);
        map.put("page",page);
        return sysOpinionbackMapper.getListByHDPage(map);
    }
    /**
     *
     * @Description 删除反馈
     * @author Mr.Chen
     * @param
     * @param ids
     * @date 2019/5/30 16:20
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo delOpinionback(String ids) {
        try {
            String [] array=ids.split(",");
            SysOpinionback sysOpinionback=null;
            for (String id:array) {
                sysOpinionback=new SysOpinionback();
                sysOpinionback.setOpinionbackid(Long.parseLong(id));
                sysOpinionback.setIsdel(1);
                sysOpinionbackMapper.updateByPrimaryKeySelective(sysOpinionback);
            }
            return  ResultVOUtils.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
