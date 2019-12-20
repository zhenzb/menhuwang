package com.qingyun.vo.manager;

import com.qingyun.mybatis.model.MbProduct;
import lombok.Data;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 13:07
 */
@Data
public class MbProductVo extends MbProduct {
    private String categoryName;
    private String url;
}
