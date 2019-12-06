package com.qingyun.service.impl;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.StringUtil;
import com.handongkeji.util.manager.ResultVOUtils;
import com.ibm.icu.text.SimpleDateFormat;
import com.qingyun.mybatis.dao.CaseCategoryMapper;
import com.qingyun.mybatis.dao.MbProductMapper;
import com.qingyun.mybatis.model.CaseCategory;
import com.qingyun.mybatis.model.CaseCategoryExample;
import com.qingyun.mybatis.model.MbProduct;
import com.qingyun.mybatis.model.MbProductExample;
import com.qingyun.service.MbProductService;
import com.qingyun.util.HtmlGenerator;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.PagingResult;
import com.qingyun.vo.manager.MbProductVo;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * @Classname MbProductServiceImpl
 * @Date 2019/5/29 17:31
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Service
@Transactional
public class MbProductServiceImpl implements MbProductService {
    @Autowired
    MbProductMapper mbProductMapper;
    @Autowired
    CaseCategoryMapper caseCategoryMapper;

    @Override
    public List<MbProductVo> getProductList(String categoryId,String text, EntyPage page) {
        Map<String,Object> map=new HashMap<>();
        map.put("text",text);
        map.put("categoryId",categoryId);
        map.put("page",page);
        return mbProductMapper.getProductListByHDPage(map);
    }

    /**
     *
     * @Description 添加或修改产品信息
     * @author Mr.Chen
     * @param
     * @param mbProduct
     * @date 2019/5/29 17:07
     * @return com.qingyun.vo.result.ResultVo
     */
    @Override
    public ResultVo advantageAddOrEdit(MbProduct mbProduct) {
        try {
            if(mbProduct.getProductid()!=null){
                mbProductMapper.updateByPrimaryKeySelective(mbProduct);
            }else {
                mbProduct.setCreationtime(new Date());
                mbProductMapper.insertSelective(mbProduct);
            }
            return ResultVOUtils.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
    @Override
    public ResultVo deleteOrSort(String ids, String sort) {
        try {
            String [] array=ids.split(",");
            MbProduct mbProduct=null;
            if(StringUtil.isNullOrEmpty(sort)){
                for (String id:array) {
                    mbProduct=new MbProduct();
                    mbProduct.setProductid(Long.parseLong(id));
                    mbProduct.setIsdel((short) 1);
                    mbProductMapper.updateByPrimaryKeySelective(mbProduct);
                }
            }else{
                for (String id:array) {
                    mbProduct=new MbProduct();
                    mbProduct.setProductid(Long.parseLong(id));
                    mbProduct.setProductsort(Integer.parseInt(sort));
                    mbProductMapper.updateByPrimaryKeySelective(mbProduct);
                }
            }
            return  ResultVOUtils.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    @Override
    public MbProductVo getCaseDetail(String caseId) {
        return mbProductMapper.getCaseDetail(caseId);
    }

    @Override
    public List<MbProduct> getPrepageCase(String caseId,String categoryId) {
        MbProductExample mbProductExample = new MbProductExample();
        mbProductExample.setOrderByClause("ProductId ASC");
        mbProductExample.createCriteria().andProductidGreaterThan(Long.valueOf(caseId)).andCategoryIdEqualTo(Long.valueOf(categoryId));
        return mbProductMapper.selectByExample(mbProductExample);

    }

    @Override
    public List<MbProduct> getNextpageCase(String caseId,String categoryId) {
        MbProductExample mbProductExample = new MbProductExample();
        mbProductExample.setOrderByClause("ProductId DESC");
        mbProductExample.createCriteria().andProductidLessThan(Long.valueOf(caseId)).andCategoryIdEqualTo(Long.valueOf(categoryId));
        return mbProductMapper.selectByExample(mbProductExample);
    }

    @Override
    public void staticState() {
        try {
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            Map<String, Object> map = new HashMap<>();
            Map<String,Object> mapList = new HashMap<>();
            MbProductExample mbProductExample = new MbProductExample();
            List<MbProduct> mbProducts = mbProductMapper.selectByExampleWithBLOBs(mbProductExample);
            //生成详情静态页面
            for (MbProduct mbProduct : mbProducts) {
                map.put("caseDetailData", mbProduct.getProductcontext()==null?"" :mbProduct.getProductcontext());
                map.put("customerPic", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575275823829&di=1805f36c0dbd5b3b6134d168b8636437&imgtype=0&src=http%3A%2F%2Fy1.ifengimg.com%2Fcmpp%2F2015%2F01%2F29%2F06%2Feb484823-7409-4f9f-b46f-556a4b10119b_size101_w400_h400.jpg");
                htmlGenerator.createHtml(map, "caseinfo.html", "caseinfo" + mbProduct.getProductid());
            }
            //生成分页列表页
            CaseCategoryExample caseCategoryExample = new CaseCategoryExample();
            List<CaseCategory> caseCategories = caseCategoryMapper.selectByExample(caseCategoryExample);
            int totalPage = 1;
            EntyPage page = new EntyPage();
            for(int i=1;i<=totalPage;i++){
                Map<String,Object> productMap = new HashMap<>();
                page.setCurrentPage(i);
                page.setPageSize(9);
                productMap.put("page",page);
                List<MbProductVo> productListByHDPage = mbProductMapper.getProductListByHDPage(productMap);
                mapList.put("categoryList",caseCategories);
                mapList.put("customerPic","");
                mapList.put("productList",productListByHDPage);
                System.out.println("www:"+productListByHDPage);
                totalPage = page.getTotalPage();
                    PagingResult pagingResult = new PagingResult();
                    pagingResult.setPage(i);
                    pagingResult.setPageSize(page.getPageSize());
                    pagingResult.setTotal(page.getTotalNumber());
                    mapList.put("pagingList",pagingResult);
                    mapList.put("url","file:///D:/handongkeji/html");
                    htmlGenerator.createHtml(mapList, "case.html", "case" + i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
