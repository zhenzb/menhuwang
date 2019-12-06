package com.qingyun.service.impl;

import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.*;
import com.qingyun.mybatis.model.*;
import com.qingyun.service.WeBsiteService;
import com.qingyun.vo.SysOpinionbackFrom;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname WeBsiteServiceImpl
 * @Date 2019/6/4 14:03
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class WeBsiteServiceImpl implements WeBsiteService {
    @Autowired
    MbIpMapper mbIpMapper;
    @Autowired
    MbSoftwareMapper mbSoftwareMapper;
    @Autowired
    MbBackgroundPicMapper mbBackgroundPicMapper;
    @Autowired
    MbProductIntroductionMapper mbProductIntroductionMapper;
    @Autowired
    MbProductMapper mbProductMapper;
    @Autowired
    SysProblemMapper sysProblemMapper;
    @Autowired
    MbCooperativepartnerMapper mbCooperativepartnerMapper;
    @Autowired
    SysOpinionbackMapper sysOpinionbackMapper;

    /**
     *对进入此页面的Ip 进行校验
     * @param ipaddress
     * @return
     */
    @Override
    public ResultVo login(String ipaddress) {
        try {
            MbIpExample example=new MbIpExample();
            example.createCriteria().andIpaddressEqualTo(ipaddress);
            List<MbIp> mbIps=mbIpMapper.selectByExample(example);
            MbIp mbIp=null;
            if (mbIps.size()>0){
                if(mbIps.get(0).getIpstats()==1){
                    return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_RIGHT);
                }else {
                    mbIp=mbIps.get(0);
                    mbIp.setAccesstime(new Date());
                    mbIpMapper.updateByPrimaryKeySelective(mbIp);
                    return ResultVOUtils.success(ipaddress);
                }
            }else {
                mbIp=new MbIp();
                mbIp.setIpaddress(ipaddress);
                mbIp.setAccesstime(new Date());
                mbIpMapper.insertSelective(mbIp);
                return ResultVOUtils.success(ipaddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * @Description 软件下载
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo getSoftware() {
        try {
            MbSoftwareExample example=new MbSoftwareExample();
            List<MbSoftware> mbSoftwares= mbSoftwareMapper.selectByExample(example);
            MbSoftware mbSoftware=null;
            if(mbSoftwares.size()>0){
                mbSoftware=mbSoftwares.get(0);
            }
            return ResultVOUtils.success(mbSoftware);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * @Description 产品介绍
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo getProductIntroduction() {
        try {
            MbProductIntroductionExample example=new MbProductIntroductionExample();
            List<MbProductIntroduction> introductions= mbProductIntroductionMapper.selectByExampleWithBLOBs(example);
            MbProductIntroduction introduction=new MbProductIntroduction();
            if(introductions.size()>0){
                introduction=introductions.get(0);
            }
            MbBackgroundPic backgroundPic=getBackgroundPic(0);
            Map<String,Object> map=new HashMap<>();
            map.put("introduction",introduction);
            map.put("backgroundPic",backgroundPic);
            return ResultVOUtils.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * @Description 产品优势
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo getProduct() {
        try {
            List<MbProduct> mbProducts=mbProductMapper.getProduct();
            MbBackgroundPic backgroundPic=getBackgroundPic(1);
            Map<String,Object> map=new HashMap<>();
            map.put("mbProducts",mbProducts);
            map.put("backgroundPic",backgroundPic);
            return ResultVOUtils.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * @Description 服务中心
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo getServiceCentre() {
        try {
            List<SysProblem> sysProblems=sysProblemMapper.getProblem();

            MbBackgroundPic backgroundPic=getBackgroundPic(2);
            Map<String,Object> map=new HashMap<>();
            map.put("sysProblems",sysProblems);
            map.put("backgroundPic",backgroundPic);
            return ResultVOUtils.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * @Description 合作伙伴
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo getCooperativePartner() {
        try {
            List<MbCooperativepartner> mbCooperativepartners=mbCooperativepartnerMapper.getCooperativePartner();
            Map<String,Object> map=new HashMap<>();
            map.put("Cooperativepartner",mbCooperativepartners);
            return ResultVOUtils.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
    /**
     *
     * @Description 用户反馈
     * @author Mr.Chen
     * @param
     * @param sysOpinionbackFrom
     * @date 2019/6/4 16:37
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo addOpinionBack(SysOpinionbackFrom sysOpinionbackFrom) {
        try {
            SysOpinionback sysOpinionback=new SysOpinionback();
            BeanUtils.copyProperties(sysOpinionbackFrom,sysOpinionback);
            sysOpinionback.setCreatetime(new Date());
            sysOpinionbackMapper.insertSelective(sysOpinionback);
            return ResultVOUtils.success();
        } catch (BeansException e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     *
     * @Description 根据类型获取对应背景图
     * @author Mr.Chen
     * @param
     * @param type
     * @date 2019/6/4 15:33
     * @return com.qingyun.mybatis.model.MbBackgroundPic
     */
    public  MbBackgroundPic getBackgroundPic(Integer type){
        MbBackgroundPicExample picExample=new MbBackgroundPicExample();
        picExample.createCriteria().andBackgroundtypeEqualTo(type);
        List<MbBackgroundPic> picList=mbBackgroundPicMapper.selectByExample(picExample);
        MbBackgroundPic backgroundPic=new MbBackgroundPic();
        if(picList.size()>0){
            backgroundPic=picList.get(0);
        }
        return backgroundPic;
    }
}
