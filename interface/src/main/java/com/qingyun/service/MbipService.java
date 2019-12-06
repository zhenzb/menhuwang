package com.qingyun.service;

import com.handongkeji.util.EntyPage;
import com.qingyun.form.MbIpQueryFrom;
import com.qingyun.mybatis.model.MbIp;
import com.qingyun.vo.result.ResultVo;

import java.util.List;

/**
 * @Classname MbipService
 * @Date 2019/6/1 14:47
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface MbipService {
    List<MbIp> getList(MbIpQueryFrom mbIpQueryFrom, EntyPage page);

    ResultVo ipstatsEdit(Long ipid, Integer ipstats);

    ResultVo delOrNolanding(String ids, Integer type);
}
