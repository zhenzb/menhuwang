package com.qingyun.service;

import com.qingyun.mybatis.model.NewsInfo;
import com.qingyun.vo.manager.NewsInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 13:35
 */
public interface NewsInfoService {

    void addNewsInfo(NewsInfo newsInfo);

    List<NewsInfoVo> getNewsInfoHDPage(Map<String,Object> map);

    NewsInfo getNewsInfoDetail(String newsInfoId);

    void updateNewsInfo(NewsInfo newsInfo);

    void deleteNewsInfo(String newsInfoId);

    List<NewsInfo> getPrepageNewsInfo(String newsInfoId);
    List<NewsInfo> getNextpageNewsInfo(String newsInfoId);

}
