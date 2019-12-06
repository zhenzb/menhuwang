package com.qingyun.service.impl;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.form.MbIpQueryFrom;
import com.qingyun.mybatis.dao.MbIpMapper;
import com.qingyun.mybatis.model.MbIp;
import com.qingyun.service.MbipService;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MbipServiceImpl
 * @Date 2019/6/1 14:47
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
public class MbipServiceImpl implements MbipService {
    @Autowired
    MbIpMapper mbIpMapper;

    @Override
    public List<MbIp> getList(MbIpQueryFrom mbIpQueryFrom, EntyPage page) {
        Map<String, Object> map = new HashMap<>();
        map.put("mbIpQueryFrom", mbIpQueryFrom);
        map.put("page", page);
        return mbIpMapper.getListByHDPage(map);
    }

    @Override
    public ResultVo ipstatsEdit(Long ipid, Integer ipstats) {
        try {
            MbIp mbIp=new MbIp();
            mbIp.setIpid(ipid);
            mbIp.setIpstats((short)ipstats.intValue());
            mbIpMapper.updateByPrimaryKeySelective(mbIp);
            return ResultVOUtils.success();
        } catch (Exception e) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @Override
    public ResultVo delOrNolanding(String ids, Integer type) {
        try {
            String arry[] =ids.split(",");
            if(type==1){
                for (String id:arry) {
                    MbIp mbIp=new MbIp();
                    mbIp.setIpid(Long.parseLong(id));
                    mbIp.setIpstats((short)1);
                    mbIpMapper.updateByPrimaryKeySelective(mbIp);
                }
            }else {
                for (String id:arry) {
                    mbIpMapper.deleteByPrimaryKey(Long.parseLong(id));
                }
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
