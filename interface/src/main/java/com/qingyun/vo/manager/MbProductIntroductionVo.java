package com.qingyun.vo.manager;

import com.qingyun.mybatis.model.MbProductIntroduction;
import lombok.Data;

/**
 * @Classname MbProductIntroductionVo
 * @Date 2019/5/28 16:59
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Data
public class MbProductIntroductionVo extends MbProductIntroduction {
    private String backgroundpicurl;
    private String titlepicurl;
}
