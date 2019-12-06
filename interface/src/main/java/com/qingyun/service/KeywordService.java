package com.qingyun.service;

import com.qingyun.mybatis.model.KeyWord;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 16:03
 */
public interface KeywordService {


    void addKeyword(KeyWord keyWord);

    List<KeyWord> getKeyWordHDPage(Map<String,Object> map);

    List<KeyWord> getKeyWordList();

    void deleteKeyWord(String keyWordId);
}
