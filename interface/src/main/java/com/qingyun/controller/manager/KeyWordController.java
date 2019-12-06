package com.qingyun.controller.manager;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.KeyWord;
import com.qingyun.mybatis.model.NewsInfo;
import com.qingyun.service.KeywordService;
import com.qingyun.vo.PageSimpleVO;
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
 * @Date: 2019/11/27 0027 16:27
 */

@RestController
@Slf4j
@RequestMapping("keyWord")
public class KeyWordController {

    @Autowired
    KeywordService keywordService;

    //添加SEO优化关键字
    @PostMapping("addKeyWord")
    public ResultVo addKeyWord(KeyWord keyWord){
        try {
            keyWord.setCreateTime(new Date());
            keywordService.addKeyword(keyWord);
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @GetMapping("getKeyWordList")
    public ResultVo getKeyWordList(String keyWordName, EntyPage page){
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("keyWordName", keyWordName);
            map.put("page", page);
            List<KeyWord> keyWordHDPage = keywordService.getKeyWordHDPage(map);
            PageSimpleVO<KeyWord> sysProblemPageSimpleVO = new PageSimpleVO<KeyWord>(page.getTotalNumber(), keyWordHDPage);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }




    /**
     * 删除SEO优化关键字
     * @param ids
     * @return
     */
    @PostMapping("deleteKeyWord")
    public ResultVo deleteKeyWord(String ids){
        try {
            keywordService.deleteKeyWord(ids);
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
