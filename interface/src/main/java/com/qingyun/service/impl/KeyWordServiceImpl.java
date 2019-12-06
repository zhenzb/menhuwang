package com.qingyun.service.impl;

import com.qingyun.mybatis.dao.KeyWordMapper;
import com.qingyun.mybatis.model.KeyWord;
import com.qingyun.mybatis.model.KeyWordExample;
import com.qingyun.service.KeywordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/27 0027 16:08
 */
@Service
public class KeyWordServiceImpl implements KeywordService {

    @Resource
    KeyWordMapper keyWordMapper;
    @Override
    public void addKeyword(KeyWord keyWord) {
        keyWordMapper.insertSelective(keyWord);
    }

    @Override
    public List<KeyWord> getKeyWordHDPage(Map<String, Object> map) {
        List<KeyWord> keyWordHDPage = keyWordMapper.getKeyWordHDPage(map);
        return keyWordHDPage;
    }

    @Override
    public List<KeyWord> getKeyWordList() {
        KeyWordExample keyWordExample = new KeyWordExample();
        return keyWordMapper.selectByExample(keyWordExample);
    }

    @Override
    public void deleteKeyWord(String keyWordId) {
        List<Long> list = new ArrayList<>();
        String[] split = keyWordId.split(",");
        for(String sp:split){
            list.add(Long.valueOf(sp));
        }
        KeyWordExample keyWordExample = new KeyWordExample();
        keyWordExample.createCriteria().andKeywordIdIn(list);
        keyWordMapper.deleteByExample(keyWordExample);
    }
}
