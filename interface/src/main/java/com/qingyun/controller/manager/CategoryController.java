package com.qingyun.controller.manager;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.form.MbIpQueryFrom;
import com.qingyun.mybatis.model.CaseCategory;
import com.qingyun.mybatis.model.MbIp;
import com.qingyun.service.CategoryService;
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
 * @Date: 2019/11/26 0026 19:18
 */
@RestController
@Slf4j
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //查询所有分类标签
    @GetMapping("getCategoryList")
    public ResultVo getCategoryList(MbIpQueryFrom mbIpQueryFrom, EntyPage page){
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("page",page);
            List<CaseCategory> mbIps= categoryService.getCaseCategoryHDPage(map);
            PageSimpleVO<CaseCategory> sysProblemPageSimpleVO = new PageSimpleVO<CaseCategory>(page.getTotalNumber(), mbIps);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
    //查询案例/新闻 选择标签
    @GetMapping("getCategory")
    public ResultVo getCategory(Integer categoryType){
        try{
            List<CaseCategory> caseCategoryList = categoryService.getCaseCategoryList(categoryType);
            return ResultVOUtils.success(caseCategoryList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

    }

    //添加分类标签
    @PostMapping("addCaseCategory")
    public ResultVo addCaseCategory(CaseCategory caseCategory){
        try {
            caseCategory.setCreateTime(new Date());
            categoryService.addCaseCategory(caseCategory);
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    //删除标签
    @PostMapping("deleteCaseCategory")
    public ResultVo deleteCaseCategory(String ids){
        try {
            categoryService.deleteCategory(ids);
            return ResultVOUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
