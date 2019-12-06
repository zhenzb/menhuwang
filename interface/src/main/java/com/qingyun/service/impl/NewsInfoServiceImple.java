package com.qingyun.service.impl;

import com.qingyun.mybatis.dao.NewsInfoMapper;
import com.qingyun.mybatis.model.CaseCategoryExample;
import com.qingyun.mybatis.model.NewsInfo;
import com.qingyun.mybatis.model.NewsInfoExample;
import com.qingyun.service.NewsInfoService;
import com.qingyun.vo.manager.NewsInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 13:39
 */
@Service
public class NewsInfoServiceImple implements NewsInfoService {

    @Resource
    NewsInfoMapper newsInfoMapper;

    @Override
    public void addNewsInfo(NewsInfo newsInfo) {
        newsInfoMapper.insertSelective(newsInfo);
    }

    @Override
    public List<NewsInfoVo> getNewsInfoHDPage(Map<String, Object> map) {
        return newsInfoMapper.getNewsInfoHDPage(map);
    }

    @Override
    public NewsInfo getNewsInfoDetail(String newsInfoId) {
        return newsInfoMapper.selectByPrimaryKey(Long.valueOf(newsInfoId));
    }

    @Override
    public void updateNewsInfo(NewsInfo newsInfo) {
        newsInfoMapper.updateByPrimaryKeySelective(newsInfo);

    }

    @Override
    public void deleteNewsInfo(String newsInfoId) {
        List<Long> list = new ArrayList<>();
        String[] split = newsInfoId.split(",");
        for(String sp:split){
            list.add(Long.valueOf(sp));
        }
        NewsInfoExample newsInfoExample = new NewsInfoExample();
        newsInfoExample.createCriteria().andNewsinfoIdIn(list);
        newsInfoMapper.deleteByExample(newsInfoExample);
    }

    @Override
    public List<NewsInfo> getPrepageNewsInfo(String newsInfoId) {
        NewsInfoExample newsInfoExample = new NewsInfoExample();
        newsInfoExample.setOrderByClause("newsinfo_id ASC");
        newsInfoExample.createCriteria().andNewsinfoIdGreaterThan(Long.valueOf(newsInfoId));
        return newsInfoMapper.selectByExample(newsInfoExample);
    }

    @Override
    public List<NewsInfo> getNextpageNewsInfo(String newsInfoId) {
        NewsInfoExample newsInfoExample = new NewsInfoExample();
        newsInfoExample.setOrderByClause("newsinfo_id DESC");
        newsInfoExample.createCriteria().andNewsinfoIdLessThan(Long.valueOf(newsInfoId));
        return newsInfoMapper.selectByExample(newsInfoExample);
    }

}
