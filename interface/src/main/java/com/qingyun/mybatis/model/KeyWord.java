package com.qingyun.mybatis.model;

import java.util.Date;

public class KeyWord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column key_word.keyword_id
     *
     * @mbggenerated
     */
    private Long keywordId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column key_word.keyword_name
     *
     * @mbggenerated
     */
    private String keywordName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column key_word.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column key_word.keyword_id
     *
     * @return the value of key_word.keyword_id
     *
     * @mbggenerated
     */
    public Long getKeywordId() {
        return keywordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column key_word.keyword_id
     *
     * @param keywordId the value for key_word.keyword_id
     *
     * @mbggenerated
     */
    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column key_word.keyword_name
     *
     * @return the value of key_word.keyword_name
     *
     * @mbggenerated
     */
    public String getKeywordName() {
        return keywordName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column key_word.keyword_name
     *
     * @param keywordName the value for key_word.keyword_name
     *
     * @mbggenerated
     */
    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column key_word.create_time
     *
     * @return the value of key_word.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column key_word.create_time
     *
     * @param createTime the value for key_word.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}