package com.qingyun.service.impl;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.StringUtil;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.MbCooperativepartnerMapper;
import com.qingyun.mybatis.model.MbCooperativepartner;
import com.qingyun.service.MbCooperativePartnerService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MbCooperativePartnerServiceImpl
 * @Date 2019/5/30 18:19
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 *
 */
@Service
@Transactional
public class MbCooperativePartnerServiceImpl implements MbCooperativePartnerService {
    @Autowired
    MbCooperativepartnerMapper mbCooperativepartnerMapper;
    @Override
    public List<MbCooperativepartner> getList(String text, EntyPage page) {
        Map<String,Object> map=new HashMap<>();
        map.put("text",text);
        map.put("page",page);
        return mbCooperativepartnerMapper.getListByHDPage(map);
    }

    @Override
    public ResultVo addOrEdit(MbCooperativepartner mbCooperativepartner) {
        try {
            if(mbCooperativepartner.getCooperativepartnerid()!=null){
                mbCooperativepartnerMapper.updateByPrimaryKeySelective(mbCooperativepartner);
            }else {
                mbCooperativepartner.setCreationtime(new Date());
                mbCooperativepartnerMapper.insertSelective(mbCooperativepartner);
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @Override
    public ResultVo deleteOrSort(String ids, String sort) {
        try {
            String [] array=ids.split(",");
            MbCooperativepartner mbCooperativepartner=null;
            if(StringUtil.isNullOrEmpty(sort)){
                for (String id:array) {
                    mbCooperativepartner=new MbCooperativepartner();
                    mbCooperativepartner.setCooperativepartnerid(Long.parseLong(id));
                    mbCooperativepartner.setIsdel((short) 1);
                    mbCooperativepartnerMapper.updateByPrimaryKeySelective(mbCooperativepartner);
                }
            }else{
                for (String id:array) {
                    mbCooperativepartner=new MbCooperativepartner();
                    mbCooperativepartner.setCooperativepartnerid(Long.parseLong(id));
                    mbCooperativepartner.setCooperativepartnersort(Integer.parseInt(sort));
                    mbCooperativepartnerMapper.updateByPrimaryKeySelective(mbCooperativepartner);
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
