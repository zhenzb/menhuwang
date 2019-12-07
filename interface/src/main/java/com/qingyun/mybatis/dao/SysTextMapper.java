package com.qingyun.mybatis.dao;

import com.qingyun.mybatis.model.SysText;
import com.qingyun.mybatis.model.SysTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysTextMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int countByExample(SysTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int deleteByExample(SysTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer textid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int insert(SysText record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int insertSelective(SysText record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    List<SysText> selectByExample(SysTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    SysText selectByPrimaryKey(Integer textid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SysText record, @Param("example") SysTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SysText record, @Param("example") SysTextExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysText record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_text
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysText record);
}