package com.qingyun.vo.manager;

import com.qingyun.mybatis.model.NewsInfo;
import lombok.Data;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/29 0029 13:35
 */
@Data
public class NewsInfoVo extends NewsInfo {
    private String categoryName;
    private String url;
    private String editeTimes;
}
