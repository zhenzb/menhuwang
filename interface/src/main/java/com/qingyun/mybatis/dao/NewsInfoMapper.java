package com.qingyun.mybatis.dao;

import com.qingyun.mybatis.model.NewsInfo;
import com.qingyun.mybatis.model.NewsInfoExample;
import java.util.List;
import java.util.Map;

import com.qingyun.vo.manager.NewsInfoVo;
import org.apache.ibatis.annotations.Param;

public interface NewsInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int countByExample(NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int deleteByExample(NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long newsinfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int insert(NewsInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int insertSelective(NewsInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    List<NewsInfo> selectByExampleWithBLOBs(NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    List<NewsInfo> selectByExample(NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    NewsInfo selectByPrimaryKey(Long newsinfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") NewsInfo record, @Param("example") NewsInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(NewsInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(NewsInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(NewsInfo record);

    List<NewsInfoVo> getNewsInfoHDPage(Map<String,Object> map);
}