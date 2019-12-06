package com.qingyun.service.impl;

import com.qingyun.mybatis.dao.CaseCategoryMapper;
import com.qingyun.mybatis.model.CaseCategory;
import com.qingyun.mybatis.model.CaseCategoryExample;
import com.qingyun.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/26 0026 19:30
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CaseCategoryMapper caseCategoryMapper;

    @Override
    public List<CaseCategory> getCaseCategoryList(Integer categoryType) {
        CaseCategoryExample caseCategoryExample = new CaseCategoryExample();
        caseCategoryExample.createCriteria().andCategoryTypeEqualTo(categoryType);
        return caseCategoryMapper.selectByExample(caseCategoryExample);
    }

    @Override
    public List<CaseCategory> getCaseCategoryHDPage(Map<String,Object> map) {
        List<CaseCategory> caseCategories = caseCategoryMapper.getCaseCategoryHDPage(map);
        return caseCategories;
    }

    @Override
    public void addCaseCategory(CaseCategory caseCategory) {
        caseCategoryMapper.insert(caseCategory);

    }

    @Override
    public void updateCaseCategory(CaseCategory caseCategory) {
        caseCategoryMapper.updateByPrimaryKey(caseCategory);
    }

    @Override
    public void deleteCategory(String caseCategoryId) {
        List<Long> list = new ArrayList<>();
        String[] split = caseCategoryId.split(",");
        for(String sp:split){
            list.add(Long.valueOf(sp));
        }
        CaseCategoryExample caseCategoryExample = new CaseCategoryExample();
        caseCategoryExample.createCriteria().andCategoryIdIn(list);
        caseCategoryMapper.deleteByExample(caseCategoryExample);
    }
}
