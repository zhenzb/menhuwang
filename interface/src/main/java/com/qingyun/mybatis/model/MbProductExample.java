package com.qingyun.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MbProductExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    public MbProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
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
     * This method corresponds to the database table mb_product
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
     * This method corresponds to the database table mb_product
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_product
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
     * This class corresponds to the database table mb_product
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

        public Criteria andProductidIsNull() {
            addCriterion("ProductId is null");
            return (Criteria) this;
        }

        public Criteria andProductidIsNotNull() {
            addCriterion("ProductId is not null");
            return (Criteria) this;
        }

        public Criteria andProductidEqualTo(Long value) {
            addCriterion("ProductId =", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotEqualTo(Long value) {
            addCriterion("ProductId <>", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThan(Long value) {
            addCriterion("ProductId >", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThanOrEqualTo(Long value) {
            addCriterion("ProductId >=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThan(Long value) {
            addCriterion("ProductId <", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThanOrEqualTo(Long value) {
            addCriterion("ProductId <=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidIn(List<Long> values) {
            addCriterion("ProductId in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotIn(List<Long> values) {
            addCriterion("ProductId not in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidBetween(Long value1, Long value2) {
            addCriterion("ProductId between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotBetween(Long value1, Long value2) {
            addCriterion("ProductId not between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProducttitleIsNull() {
            addCriterion("ProductTitle is null");
            return (Criteria) this;
        }

        public Criteria andProducttitleIsNotNull() {
            addCriterion("ProductTitle is not null");
            return (Criteria) this;
        }

        public Criteria andProducttitleEqualTo(String value) {
            addCriterion("ProductTitle =", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleNotEqualTo(String value) {
            addCriterion("ProductTitle <>", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleGreaterThan(String value) {
            addCriterion("ProductTitle >", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleGreaterThanOrEqualTo(String value) {
            addCriterion("ProductTitle >=", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleLessThan(String value) {
            addCriterion("ProductTitle <", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleLessThanOrEqualTo(String value) {
            addCriterion("ProductTitle <=", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleLike(String value) {
            addCriterion("ProductTitle like", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleNotLike(String value) {
            addCriterion("ProductTitle not like", value, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleIn(List<String> values) {
            addCriterion("ProductTitle in", values, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleNotIn(List<String> values) {
            addCriterion("ProductTitle not in", values, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleBetween(String value1, String value2) {
            addCriterion("ProductTitle between", value1, value2, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProducttitleNotBetween(String value1, String value2) {
            addCriterion("ProductTitle not between", value1, value2, "producttitle");
            return (Criteria) this;
        }

        public Criteria andProductstateIsNull() {
            addCriterion("ProductState is null");
            return (Criteria) this;
        }

        public Criteria andProductstateIsNotNull() {
            addCriterion("ProductState is not null");
            return (Criteria) this;
        }

        public Criteria andProductstateEqualTo(Integer value) {
            addCriterion("ProductState =", value, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateNotEqualTo(Integer value) {
            addCriterion("ProductState <>", value, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateGreaterThan(Integer value) {
            addCriterion("ProductState >", value, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("ProductState >=", value, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateLessThan(Integer value) {
            addCriterion("ProductState <", value, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateLessThanOrEqualTo(Integer value) {
            addCriterion("ProductState <=", value, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateIn(List<Integer> values) {
            addCriterion("ProductState in", values, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateNotIn(List<Integer> values) {
            addCriterion("ProductState not in", values, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateBetween(Integer value1, Integer value2) {
            addCriterion("ProductState between", value1, value2, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductstateNotBetween(Integer value1, Integer value2) {
            addCriterion("ProductState not between", value1, value2, "productstate");
            return (Criteria) this;
        }

        public Criteria andProductsortIsNull() {
            addCriterion("ProductSort is null");
            return (Criteria) this;
        }

        public Criteria andProductsortIsNotNull() {
            addCriterion("ProductSort is not null");
            return (Criteria) this;
        }

        public Criteria andProductsortEqualTo(Integer value) {
            addCriterion("ProductSort =", value, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortNotEqualTo(Integer value) {
            addCriterion("ProductSort <>", value, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortGreaterThan(Integer value) {
            addCriterion("ProductSort >", value, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortGreaterThanOrEqualTo(Integer value) {
            addCriterion("ProductSort >=", value, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortLessThan(Integer value) {
            addCriterion("ProductSort <", value, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortLessThanOrEqualTo(Integer value) {
            addCriterion("ProductSort <=", value, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortIn(List<Integer> values) {
            addCriterion("ProductSort in", values, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortNotIn(List<Integer> values) {
            addCriterion("ProductSort not in", values, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortBetween(Integer value1, Integer value2) {
            addCriterion("ProductSort between", value1, value2, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductsortNotBetween(Integer value1, Integer value2) {
            addCriterion("ProductSort not between", value1, value2, "productsort");
            return (Criteria) this;
        }

        public Criteria andProductpicIsNull() {
            addCriterion("ProductPic is null");
            return (Criteria) this;
        }

        public Criteria andProductpicIsNotNull() {
            addCriterion("ProductPic is not null");
            return (Criteria) this;
        }

        public Criteria andProductpicEqualTo(String value) {
            addCriterion("ProductPic =", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicNotEqualTo(String value) {
            addCriterion("ProductPic <>", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicGreaterThan(String value) {
            addCriterion("ProductPic >", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicGreaterThanOrEqualTo(String value) {
            addCriterion("ProductPic >=", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicLessThan(String value) {
            addCriterion("ProductPic <", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicLessThanOrEqualTo(String value) {
            addCriterion("ProductPic <=", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicLike(String value) {
            addCriterion("ProductPic like", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicNotLike(String value) {
            addCriterion("ProductPic not like", value, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicIn(List<String> values) {
            addCriterion("ProductPic in", values, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicNotIn(List<String> values) {
            addCriterion("ProductPic not in", values, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicBetween(String value1, String value2) {
            addCriterion("ProductPic between", value1, value2, "productpic");
            return (Criteria) this;
        }

        public Criteria andProductpicNotBetween(String value1, String value2) {
            addCriterion("ProductPic not between", value1, value2, "productpic");
            return (Criteria) this;
        }

        public Criteria andCreationtimeIsNull() {
            addCriterion("CreationTime is null");
            return (Criteria) this;
        }

        public Criteria andCreationtimeIsNotNull() {
            addCriterion("CreationTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreationtimeEqualTo(Date value) {
            addCriterion("CreationTime =", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeNotEqualTo(Date value) {
            addCriterion("CreationTime <>", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeGreaterThan(Date value) {
            addCriterion("CreationTime >", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreationTime >=", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeLessThan(Date value) {
            addCriterion("CreationTime <", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeLessThanOrEqualTo(Date value) {
            addCriterion("CreationTime <=", value, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeIn(List<Date> values) {
            addCriterion("CreationTime in", values, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeNotIn(List<Date> values) {
            addCriterion("CreationTime not in", values, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeBetween(Date value1, Date value2) {
            addCriterion("CreationTime between", value1, value2, "creationtime");
            return (Criteria) this;
        }

        public Criteria andCreationtimeNotBetween(Date value1, Date value2) {
            addCriterion("CreationTime not between", value1, value2, "creationtime");
            return (Criteria) this;
        }

        public Criteria andProductremarksIsNull() {
            addCriterion("ProductRemarks is null");
            return (Criteria) this;
        }

        public Criteria andProductremarksIsNotNull() {
            addCriterion("ProductRemarks is not null");
            return (Criteria) this;
        }

        public Criteria andProductremarksEqualTo(String value) {
            addCriterion("ProductRemarks =", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksNotEqualTo(String value) {
            addCriterion("ProductRemarks <>", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksGreaterThan(String value) {
            addCriterion("ProductRemarks >", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksGreaterThanOrEqualTo(String value) {
            addCriterion("ProductRemarks >=", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksLessThan(String value) {
            addCriterion("ProductRemarks <", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksLessThanOrEqualTo(String value) {
            addCriterion("ProductRemarks <=", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksLike(String value) {
            addCriterion("ProductRemarks like", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksNotLike(String value) {
            addCriterion("ProductRemarks not like", value, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksIn(List<String> values) {
            addCriterion("ProductRemarks in", values, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksNotIn(List<String> values) {
            addCriterion("ProductRemarks not in", values, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksBetween(String value1, String value2) {
            addCriterion("ProductRemarks between", value1, value2, "productremarks");
            return (Criteria) this;
        }

        public Criteria andProductremarksNotBetween(String value1, String value2) {
            addCriterion("ProductRemarks not between", value1, value2, "productremarks");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNull() {
            addCriterion("IsDel is null");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNotNull() {
            addCriterion("IsDel is not null");
            return (Criteria) this;
        }

        public Criteria andIsdelEqualTo(Short value) {
            addCriterion("IsDel =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(Short value) {
            addCriterion("IsDel <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(Short value) {
            addCriterion("IsDel >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(Short value) {
            addCriterion("IsDel >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(Short value) {
            addCriterion("IsDel <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(Short value) {
            addCriterion("IsDel <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<Short> values) {
            addCriterion("IsDel in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<Short> values) {
            addCriterion("IsDel not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(Short value1, Short value2) {
            addCriterion("IsDel between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(Short value1, Short value2) {
            addCriterion("IsDel not between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNull() {
            addCriterion("key_word is null");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNotNull() {
            addCriterion("key_word is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWordEqualTo(String value) {
            addCriterion("key_word =", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotEqualTo(String value) {
            addCriterion("key_word <>", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThan(String value) {
            addCriterion("key_word >", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThanOrEqualTo(String value) {
            addCriterion("key_word >=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThan(String value) {
            addCriterion("key_word <", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThanOrEqualTo(String value) {
            addCriterion("key_word <=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLike(String value) {
            addCriterion("key_word like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotLike(String value) {
            addCriterion("key_word not like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordIn(List<String> values) {
            addCriterion("key_word in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotIn(List<String> values) {
            addCriterion("key_word not in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordBetween(String value1, String value2) {
            addCriterion("key_word between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotBetween(String value1, String value2) {
            addCriterion("key_word not between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mb_product
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
     * This class corresponds to the database table mb_product
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