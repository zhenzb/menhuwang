package com.qingyun.controller.client;

import com.handongkeji.util.EntyPage;
import com.handongkeji.util.manager.ResultVOUtils;
import com.qingyun.mybatis.model.*;
import com.qingyun.service.*;
import com.qingyun.vo.PageSimpleVO;
import com.qingyun.vo.SysOpinionbackFrom;
import com.qingyun.vo.manager.MbProductVo;
import com.qingyun.vo.manager.NewsInfoVo;
import com.qingyun.vo.result.ResultEnum;
import com.qingyun.vo.result.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname PcIndex
 * @Date 2019/6/4 13:56
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
@Api(description = "案例模块")
@RestController
@RequestMapping("index")
public class WeBsiteController {
    @Autowired
    WeBsiteService weBsiteService;
    @Autowired
    MbShoppingMallService mbShoppingMallService;
    @Autowired
    private SysTextService sysTextService;
    @Autowired
    SysCustomeService sysCustomeService;
    @Autowired
    SysBasicsService sysBasicsService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    MbProductService mbProductService;

    @Autowired
    NewsInfoService newsInfoService;

    @Autowired
    KeywordService keywordService;

    @Autowired
    CustomerService customerService;
    /**
     *对进入此页面的Ip 进行校验
     * @param
     * @return
     */
//    @GetMapping("login")
//    public ResultVo login(HttpServletRequest request){
//        String ip = request.getHeader("x-forwarded-for");
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return weBsiteService.login(ip);
//    }

    /**
     * @Description 产品APP
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getSoftware")
//    public ResultVo getSoftware(){
//        return weBsiteService.getSoftware();
//    }
    /**
     * @Description 产品介绍
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getProductIntroduction")
//    public ResultVo getProductIntroduction(){
//        return weBsiteService.getProductIntroduction();
//    }

    /**
     * @Description 案例列表
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @ApiOperation(value="案例列表", notes="案例列表")
//    @ApiImplicitParams({})
//    @GetMapping("getProduct")
//    public ResultVo getProduct(){
//        return weBsiteService.getProduct();
//    }

    /**
     * @Description 服务中心
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getServiceCentre")
//    public ResultVo getServiceCentre(){
//        return weBsiteService.getServiceCentre();
//    }

    /**
     * @Description 首页图片
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getShoppingMall")
//    public ResultVo getShoppingMall(){
//        try {
//            MbShoppingmall mbShoppingmall = mbShoppingMallService.getShoppingMallInfo();
//            return ResultVOUtils.success(mbShoppingmall);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
//        }
//    }

    /**
     * @Description 获取公司简介
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getTextInfo")
//    public ResultVo getSysTextInfo(){
//        try {
//            SysText sysText=sysTextService.getSysTextInfo();
//            return ResultVOUtils.success(sysText);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
//        }
//    }

    /**
     * @Description 业务合作
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getCooperativePartner")
//    public ResultVo getCooperativePartner(){
//        return weBsiteService.getCooperativePartner();
//    }

    /**
     * @Description 联系我们
     * @author Mr.Chen
     * @param
     * @date 2019/6/4 14:56
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getCustomer")
//    public ResultVo getCustomerInfo(){
//        try {
//            SysCustomer sysCustomer=sysCustomeService.getCustomerInfo();
//            return ResultVOUtils.success(sysCustomer);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
//        }
//    }
    /**
     *
     * @Description 基本设置信息
     * @author Mr.Chen
     * @param
     * @date 2019/6/1 13:43
     * @return com.qingyun.vo.result.ResultVo
     */
//    @GetMapping("getBasics")
//    public ResultVo getBasicsInfo(){
//        try {
//            SysBasics sysBasics=sysBasicsService.getBasicsInfo();
//            return ResultVOUtils.success(sysBasics);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
//        }
//    }
    /**
     *
     * @Description 用户反馈
     * @author Mr.Chen
     * @param
     * @param sysOpinionbackFrom
     * @date 2019/6/4 16:37
     * @return com.qingyun.vo.result.ResultVo
     */
//    @PostMapping("addOpinionBack")
//    public ResultVo addOpinionBack(SysOpinionbackFrom sysOpinionbackFrom){
//          return weBsiteService.addOpinionBack(sysOpinionbackFrom);
//
//    }

    /**
     *
     * @Description 获取案例模块标签
     * @author Mr.Chen
     * @param
     * @param
     * @date 2019/6/4 16:37
     * @return com.qingyun.vo.result.ResultVo
     */
    @ApiOperation(value="模块标签", notes="模块标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="categoryType",value = "1:案例 2:新闻",required=true,dataType="int",paramType="query"),
    })
    @GetMapping("getCategoryList")
    public ResultVo getCategoryList(Integer categoryType){
        try{
            List<CaseCategory> caseCategoryList = categoryService.getCaseCategoryList(categoryType);
            return ResultVOUtils.success(caseCategoryList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

    }

    /**
     *
     * @Description 获取案例列表
     * @author Mr.Chen
     * @param
     * @param
     * @date 2019/6/4 16:37
     * @return com.qingyun.vo.result.ResultVo
     */
    @ApiOperation(value="案例列表", notes="案例列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="categoryId",value = "标签ID",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="currentPage",value = "页码",required=true,dataType="int",paramType="query"),
            @ApiImplicitParam(name="pageSize",value = "每页记录数",required=true,dataType="int",paramType="query"),
    })
    @GetMapping("getCaseList")
    public ResultVo getCaseList(EntyPage page,String categoryId){
        try{
            List<MbProductVo> productList = mbProductService.getProductList(categoryId,null, page);
            PageSimpleVO<MbProductVo> sysProblemPageSimpleVO = new PageSimpleVO<MbProductVo>(page.getTotalNumber(), productList);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

    }

    /**
     * 获取案例详情
     * @param caseId
     * @return
     */
    @ApiOperation(value="获取案例详情", notes="获取案例详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="caseId",value = "案例ID",required=true,dataType="int",paramType="query"),
            @ApiImplicitParam(name="categoryId",value = "标签ID",required=false,dataType="int",paramType="query"),
    })
    @PostMapping("getCaseDetail")
    public ResultVo getCaseDetail(String caseId,String categoryId){
        Map<String,Object> map = new HashMap<>();
        try {
            MbProductVo caseDetail = mbProductService.getCaseDetail(caseId);
            if(caseDetail!=null){
                List<MbProduct> prepage = mbProductService.getPrepageCase(caseId,categoryId);
                List<MbProduct> nextpage = mbProductService.getNextpageCase(caseId,categoryId);
                map.put("caseDetail",caseDetail);
                if(prepage.size() == 0){
                    map.put("prepage",0);
                }else{
                    map.put("prepage",prepage.get(0));
                }
                if(nextpage.size() == 0){
                    map.put("nextpage",0);
                }else{
                    map.put("nextpage",nextpage.get(0));
                }

            }
            return ResultVOUtils.success(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 获取新闻列表
     * @return
     */
    @ApiOperation(value="获取新闻列表", notes="获取新闻列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="categoryId",value = "标签ID",required=false,dataType="int",paramType="query"),
            @ApiImplicitParam(name="currentPage",value = "页码",required=true,dataType="int",paramType="query"),
            @ApiImplicitParam(name="pageSize",value = "每页记录数",required=true,dataType="int",paramType="query"),
    })
    @GetMapping("getNewsInfo")
    public ResultVo getNewsInfo(EntyPage page,String categoryId){
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("page",page);
            map.put("categoryId",categoryId);
            List<NewsInfoVo> newsInfoHDPage = newsInfoService.getNewsInfoHDPage(map);
            PageSimpleVO<NewsInfoVo> sysProblemPageSimpleVO = new PageSimpleVO<NewsInfoVo>(page.getTotalNumber(), newsInfoHDPage);
            return ResultVOUtils.success(sysProblemPageSimpleVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 获取新闻详情
     * @param newsInfoId
     * @return
     */
    @ApiOperation(value="获取新闻详情", notes="获取新闻详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="newsInfoId",value = "ID",required=true,dataType="int",paramType="query")
    })
    @PostMapping("getNewsInfoDetail")
    public ResultVo getNewsInfoDetail(String newsInfoId){
        try{
            Map<String,Object> map = new HashMap<>();
            NewsInfo newsInfoDetail = newsInfoService.getNewsInfoDetail(newsInfoId);
            if(newsInfoDetail !=null){
                List<NewsInfo> prepage = newsInfoService.getPrepageNewsInfo(newsInfoId);
                List<NewsInfo> nextpage = newsInfoService.getNextpageNewsInfo(newsInfoId);
                map.put("newsInfoDetail",newsInfoDetail);
                if(prepage.size() == 0){
                    map.put("prepage",0);
                }else{
                    map.put("prepage",prepage.get(0));
                }
                if(nextpage.size() == 0){
                    map.put("nextpage",0);
                }else {
                    map.put("nextpage",nextpage.get(0));
                }
            }
            return ResultVOUtils.success(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 获取SEO优化关键字
     * @param
     * @return
     */
    @ApiOperation(value="获取SEO优化关键字", notes="获取SEO优化关键字")
    @ApiImplicitParams({

    })
    @GetMapping("getKeyWord")
    public ResultVo getKeyWord(){
        try{
            List<KeyWord> keyWordList = keywordService.getKeyWordList();
            return  ResultVOUtils.success(keyWordList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }

    /**
     * 获取客服
     * @param
     * @return
     */
    @ApiOperation(value="获取客服", notes="获取客服")
    @ApiImplicitParams({

    })
    @GetMapping("getCustomer")
    public ResultVo getCustomer(EntyPage page){
        try{
            MbCustomer mbCustomer = null;
            Map<String,Object> map = new HashMap<>();
            map.put("customerStatus",1);
            map.put("page",page);
            List<MbCustomer> mbCustomerHDPage = customerService.getMbCustomerHDPage(map);
            if(mbCustomerHDPage.size()>0){
               mbCustomer = mbCustomerHDPage.get(0);
            }
            return  ResultVOUtils.success(mbCustomer);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
    }
}
