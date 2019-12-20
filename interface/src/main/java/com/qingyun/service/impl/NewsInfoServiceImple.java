package com.qingyun.service.impl;

import com.handongkeji.util.EntyPage;
import com.qingyun.mybatis.dao.CaseCategoryMapper;
import com.qingyun.mybatis.dao.MbCustomerMapper;
import com.qingyun.mybatis.dao.NewsInfoMapper;
import com.qingyun.mybatis.model.*;
import com.qingyun.service.NewsInfoService;
import com.qingyun.util.HtmlGenerator;
import com.qingyun.vo.PagingResult;
import com.qingyun.vo.manager.MbProductVo;
import com.qingyun.vo.manager.NewsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 13:39
 */
@Service
public class NewsInfoServiceImple implements NewsInfoService {

    @Resource
    NewsInfoMapper newsInfoMapper;
    @Resource
    MbCustomerMapper mbCustomerMapper;
    @Autowired
    CaseCategoryMapper caseCategoryMapper;

    @Value("${file.inputTemplatePath}")
    private String inputTemplatePath;
    @Value("${file.outNewsPath}")
    private String outNewsPath;

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


    @Override
    public void staticState() {
        try {
            EntyPage page = new EntyPage();
            MbCustomer mbCustomer = null;
            Map<String,Object> map1 = new HashMap<>();
            map1.put("customerStatus",1);
            map1.put("page",page);
            List<MbCustomer> mbCustomerHDPage =mbCustomerMapper.getMbCustomerHDPage(map1);
            if(mbCustomerHDPage.size()>0){
                mbCustomer = mbCustomerHDPage.get(0);
            }
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            Map<String, Object> map = new HashMap<>();
            Map<String,Object> mapList = new HashMap<>();
            NewsInfoExample newsInfoExample = new NewsInfoExample();
            List<NewsInfo> newsInfos = newsInfoMapper.selectByExampleWithBLOBs(newsInfoExample);
            //生成详情静态页面
            for (NewsInfo newsInfo : newsInfos) {
                List<NewsInfo> prepageNewsInfo = this.getPrepageNewsInfo(String.valueOf(newsInfo.getNewsinfoId()));
                List<NewsInfo> nextpageNewsInfo = this.getNextpageNewsInfo(String.valueOf(newsInfo.getNewsinfoId()));
                if(prepageNewsInfo.size() == 0){
                    map.put("prepageTitle","0");
                }else{
                    map.put("prepageTitle",prepageNewsInfo.get(0).getNewsTitel());
                    map.put("prepageUrl","/handongkeji/html/news/newsInfo"+prepageNewsInfo.get(0).getNewsinfoId()+".html");
                }
                if(nextpageNewsInfo.size() == 0){
                    map.put("nextpageTitle","0");
                }else {
                    map.put("nextpageTitle",nextpageNewsInfo.get(0).getNewsTitel());
                    map.put("nextpageUrl","/handongkeji/html/news/newsInfo"+nextpageNewsInfo.get(0).getNewsinfoId()+".html");
                }
                map.put("newsContent", newsInfo.getNewsContent());
                map.put("customerPic", mbCustomer.getCustomerPic());
                map.put("newsTitel",newsInfo.getNewsTitel());
                map.put("newsPic",newsInfo.getNewsPic());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(newsInfo.getEditTime());
                map.put("editTime",format);
                htmlGenerator.createHtml(map, "newsinfo.html",inputTemplatePath,outNewsPath, "newsInfo" + newsInfo.getNewsinfoId());
            }
            //生成分页列表页
            CaseCategoryExample caseCategoryExample = new CaseCategoryExample();
            caseCategoryExample.createCriteria().andCategoryTypeEqualTo(2);
            List<CaseCategory> caseCategories = caseCategoryMapper.selectByExample(caseCategoryExample);
            int totalPage = 1;
            for(int i=1;i<=totalPage;i++){
                Map<String,Object> newsMap = new HashMap<>();
                page.setCurrentPage(i);
                page.setPageSize(2);
                newsMap.put("page",page);
                List<NewsInfoVo> newsListByHDPage = newsInfoMapper.getNewsInfoHDPage(newsMap);
                List<NewsInfoVo> list = new ArrayList<>();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
                for(NewsInfoVo newsInfoVo:newsListByHDPage){
                    newsInfoVo.setUrl("/handongkeji/html/news/newsInfo"+newsInfoVo.getNewsinfoId()+".html");
                    String format = simpleDateFormat.format(newsInfoVo.getEditTime());
                    newsInfoVo.setEditeTimes(format);
                    list.add(newsInfoVo);
                }
                mapList.put("categoryList",caseCategories);
                mapList.put("customerPic",mbCustomer.getCustomerPic());
                mapList.put("newsList",list);
                System.out.println("newsList:"+newsListByHDPage);
                totalPage = page.getTotalPage();
                PagingResult pagingResult = new PagingResult();
                pagingResult.setPage(i);
                pagingResult.setPageSize(page.getPageSize());
                pagingResult.setTotal(page.getTotalNumber());
                mapList.put("pagingList",pagingResult);
                mapList.put("url","/handongkeji/html/news");
                htmlGenerator.createHtml(mapList, "news.html", inputTemplatePath,outNewsPath,"news" + i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
