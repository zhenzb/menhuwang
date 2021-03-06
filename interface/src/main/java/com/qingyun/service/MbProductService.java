package com.qingyun.service;

import com.handongkeji.util.EntyPage;
import com.qingyun.mybatis.model.MbProduct;
import com.qingyun.vo.manager.MbProductVo;
import com.qingyun.vo.result.ResultVo;

import java.util.List;

/**
 * @Classname MbProductService
 * @Date 2019/5/29 17:31
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public interface MbProductService {
    /**
     *
     * @Description 产品集合
     * @author Mr.Chen
     * @param
     * @param text
     * @param page
     * @date 2019/5/29 17:34
     * @return java.util.List<com.qingyun.mybatis.model.MbProduct>
     */
    List<MbProductVo> getProductList(String categoryId,String text, EntyPage page);
    /**
     *
     * @Description 添加或修改产品信息
     * @author Mr.Chen
     * @param
     * @param mbProduct
     * @date 2019/5/29 17:07
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo advantageAddOrEdit(MbProduct mbProduct);

    /**
     *
     * @Description 删除或排序
     * @author Mr.Chen
     * @param
     * @param ids
     * @param sort
     * @date 2019/5/29 18:49
     * @return com.qingyun.vo.result.ResultVo
     */
    ResultVo deleteOrSort(String ids, String sort);

    MbProductVo getCaseDetail(String caseId);

    List<MbProduct> getPrepageCase(String caseId,String categoryId);
    List<MbProduct> getNextpageCase(String caseId,String categoryId);

    void staticState();
}
