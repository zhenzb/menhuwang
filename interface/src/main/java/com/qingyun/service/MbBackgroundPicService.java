package com.qingyun.service;

import com.qingyun.mybatis.model.MbBackgroundPic;
import com.qingyun.vo.result.ResultVo;

/**
 * @Classname MbBackgroundPicService
 * @Date 2019/5/30 10:05
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface MbBackgroundPicService {
    MbBackgroundPic getBackgroudPic(int type);

    ResultVo backgroudPicEdit(MbBackgroundPic mbBackgroundPic);
}
