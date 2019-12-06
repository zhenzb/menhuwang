package com.qingyun.service;

import com.qingyun.mybatis.model.CaseCategory;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenzhuobin
 * @Date: 2019/11/26 0026 19:21
 */
public interface CategoryService {

    List<CaseCategory> getCaseCategoryList(Integer categoryType);
    List<CaseCategory> getCaseCategoryHDPage(Map<String,Object> map);

    void addCaseCategory(CaseCategory caseCategory);

    void updateCaseCategory(CaseCategory caseCategory);

    void deleteCategory(String caseCategoryId);

}
