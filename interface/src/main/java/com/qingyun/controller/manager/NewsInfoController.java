package com.qingyun.controller.manager;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.ibm.icu.text.SimpleDateFormat;
import com.qingyun.form.MbIpQueryFrom;
import com.qingyun.mybatis.model.CaseCategory;
import com.qingyun.mybatis.model.NewsInfo;
import com.qingyun.service.NewsInfoService;
import com.qingyun.util.HtmlGenerator;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.manager.NewsInfoVo;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 13:33
 */
@RestController
@Slf4j
@RequestMapping("newsInfo")
public class NewsInfoController {

    @Autowired
    NewsInfoService newsInfoService;

    //添加新闻动态
    @PostMapping("addNewsInfo")
    public ResultVo addNewsInfo(NewsInfo newsInfo){
        Map<String,Object> map = new HashMap<>();
        try {
            if(newsInfo.getNewsinfoId() ==null){
                //添加
                Date date = new Date();
                newsInfo.setCreateTime(date);
                newsInfo.setEditTime(date);
                newsInfoService.addNewsInfo(newsInfo);
                //生成静态页面
                HtmlGenerator htmlGenerator = new HtmlGenerator();
                map.put("customerPic","");
                map.put("newsTitel",newsInfo.getNewsTitel());
                map.put("newsContent",newsInfo.getNewsContent());
                map.put("newsPic",newsInfo.getNewsPic());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = simpleDateFormat.format(date);
                map.put("editTime",format);
                htmlGenerator.createHtml(map,"newsInfo.html",newsInfo.getNewsinfoId().toString());
            }else{
                //编辑
                newsInfo.setEditTime(new Date());
                newsInfoService.updateNewsInfo(newsInfo);
            }
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    //查询新闻动态
    @GetMapping("getNewsInfoList")
    public ResultVo getNewsInfoList(String text, EntyPage page){
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("page",page);
            map.put("newsTitle",text);
            List<NewsInfoVo> newsInfoHDPage = newsInfoService.getNewsInfoHDPage(map);
            PageSimpleVO<NewsInfoVo> sysProblemPageSimpleVO = new PageSimpleVO<NewsInfoVo>(page.getTotalNumber(), newsInfoHDPage);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    //删除新闻动态
    @PostMapping("deleteNewsInfo")
    public ResultVo deleteNewsInfo(String ids){
        try{
            newsInfoService.deleteNewsInfo(ids);
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

}
