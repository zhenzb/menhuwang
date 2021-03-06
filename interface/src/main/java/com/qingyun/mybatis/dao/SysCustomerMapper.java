package com.qingyun.mybatis.dao;

import com.qingyun.mybatis.model.SysCustomer;
import com.qingyun.mybatis.model.SysCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int countByExample(SysCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int deleteByExample(SysCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer customerid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int insert(SysCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int insertSelective(SysCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    List<SysCustomer> selectByExample(SysCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    SysCustomer selectByPrimaryKey(Integer customerid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysCustomer record, @Param("example") SysCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysCustomer record, @Param("example") SysCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_customer
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysCustomer record);
}