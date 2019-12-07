package com.qingyun.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class MbProductIntroductionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public MbProductIntroductionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBtndowntextIsNull() {
            addCriterion("BtnDownText is null");
            return (Criteria) this;
        }

        public Criteria andBtndowntextIsNotNull() {
            addCriterion("BtnDownText is not null");
            return (Criteria) this;
        }

        public Criteria andBtndowntextEqualTo(String value) {
            addCriterion("BtnDownText =", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextNotEqualTo(String value) {
            addCriterion("BtnDownText <>", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextGreaterThan(String value) {
            addCriterion("BtnDownText >", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextGreaterThanOrEqualTo(String value) {
            addCriterion("BtnDownText >=", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextLessThan(String value) {
            addCriterion("BtnDownText <", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextLessThanOrEqualTo(String value) {
            addCriterion("BtnDownText <=", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextLike(String value) {
            addCriterion("BtnDownText like", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextNotLike(String value) {
            addCriterion("BtnDownText not like", value, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextIn(List<String> values) {
            addCriterion("BtnDownText in", values, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextNotIn(List<String> values) {
            addCriterion("BtnDownText not in", values, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextBetween(String value1, String value2) {
            addCriterion("BtnDownText between", value1, value2, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andBtndowntextNotBetween(String value1, String value2) {
            addCriterion("BtnDownText not between", value1, value2, "btndowntext");
            return (Criteria) this;
        }

        public Criteria andTextcontentIsNull() {
            addCriterion("TextContent is null");
            return (Criteria) this;
        }

        public Criteria andTextcontentIsNotNull() {
            addCriterion("TextContent is not null");
            return (Criteria) this;
        }

        public Criteria andTextcontentEqualTo(String value) {
            addCriterion("TextContent =", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentNotEqualTo(String value) {
            addCriterion("TextContent <>", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentGreaterThan(String value) {
            addCriterion("TextContent >", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentGreaterThanOrEqualTo(String value) {
            addCriterion("TextContent >=", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentLessThan(String value) {
            addCriterion("TextContent <", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentLessThanOrEqualTo(String value) {
            addCriterion("TextContent <=", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentLike(String value) {
            addCriterion("TextContent like", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentNotLike(String value) {
            addCriterion("TextContent not like", value, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentIn(List<String> values) {
            addCriterion("TextContent in", values, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentNotIn(List<String> values) {
            addCriterion("TextContent not in", values, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentBetween(String value1, String value2) {
            addCriterion("TextContent between", value1, value2, "textcontent");
            return (Criteria) this;
        }

        public Criteria andTextcontentNotBetween(String value1, String value2) {
            addCriterion("TextContent not between", value1, value2, "textcontent");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mb_product_introduction
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mb_product_introduction
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}