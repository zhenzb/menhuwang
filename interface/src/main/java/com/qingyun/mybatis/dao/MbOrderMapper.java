package com.qingyun.mybatis.dao;

import com.qingyun.mybatis.model.MbOrder;
import com.qingyun.mybatis.model.MbOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MbOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int countByExample(MbOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int deleteByExample(MbOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long orderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int insert(MbOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int insertSelective(MbOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    List<MbOrder> selectByExample(MbOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    MbOrder selectByPrimaryKey(Long orderid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MbOrder record, @Param("example") MbOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MbOrder record, @Param("example") MbOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MbOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MbOrder record);
}