package com.qingyun.service.impl;

import com.google.zxing.WriterException;
import com.handongkeji.util.QRCodeGenerator;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.dao.MbSoftwareMapper;
import com.qingyun.mybatis.model.MbSoftware;
import com.qingyun.mybatis.model.MbSoftwareExample;
import com.qingyun.service.MbSoftwareService;
import com.qingyun.vo.MbSoftwareFrom;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Classname MbSoftwareServiceImpl
 * @Date 2019/5/27 14:52
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class MbSoftwareServiceImpl implements MbSoftwareService {
    @Autowired
    MbSoftwareMapper mbSoftwareMapper;

    @Override
    public MbSoftware getMbSoftware() {
        MbSoftwareExample example=new MbSoftwareExample();
        List<MbSoftware> mbSoftwares= mbSoftwareMapper.selectByExample(example);
        MbSoftware mbSoftware=null;
        if(mbSoftwares.size()>0){
            mbSoftware=mbSoftwares.get(0);
        }
        return mbSoftware;
    }

    @Override
    public ResultVo addOrEdit(MbSoftwareFrom mbSoftwareFrom) {
        MbSoftware mbSoftware=null;
        try {
            if(mbSoftwareFrom.getSoftwareid()!=null){
                mbSoftware=mbSoftwareMapper.selectByPrimaryKey(mbSoftwareFrom.getSoftwareid());
                BeanUtils.copyProperties(mbSoftwareFrom,mbSoftware);
                mbSoftwareMapper.updateByPrimaryKey(mbSoftware);
            }else {
                mbSoftware=new MbSoftware();
                BeanUtils.copyProperties(mbSoftwareFrom,mbSoftware);
                mbSoftware.setCreationtime(new Date());
                mbSoftwareMapper.insertSelective(mbSoftware);
            }
            if(mbSoftware.getIphoneaddress()!=null){
                try {
                    QRCodeGenerator.generateQRCodeImage(mbSoftware.getIphoneaddress(),200,200,1);
                    mbSoftware.setIphoneqrcode("http://47.105.204.10/qingyun/qingyunupload/qingyun/common/iPhoneQRCode.png");
                } catch (WriterException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(mbSoftware.getAndroidaddress()!=null){
                try {
                    QRCodeGenerator.generateQRCodeImage(mbSoftware.getAndroidaddress(),200,200,2);
                    mbSoftware.setAndroidqrcode("http://47.105.204.10/qingyun/qingyunupload/qingyun/common/AndroidQRCode.png");
                } catch (WriterException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mbSoftwareMapper.updateByPrimaryKey(mbSoftware);
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @Override
    public ResultVo deleteOrSort(String ids, String sort) {
        try {
//            String [] array=ids.split(",");
//            MbSoftware mbSoftware=null;
//            if(StringUtil.isNullOrEmpty(sort)){
//                for (String id:array) {
//                    mbSoftware=new MbSoftware();
//                    mbSoftware.setSoftwareid(Long.parseLong(id));
//                    mbSoftware.setIsdel((short) 1);
//                    mbSoftwareMapper.updateByPrimaryKeySelective(mbSoftware);
//                }
//            }else{
//                for (String id:array) {
//                    mbSoftware=new MbSoftware();
//                    mbSoftware.setSoftwareid(Long.parseLong(id));
//                    mbSoftware.setSoftwaresort(Integer.parseInt(sort));
//                    mbSoftwareMapper.updateByPrimaryKeySelective(mbSoftware);
//                }
//            }
            return  ResultVOUtils.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
