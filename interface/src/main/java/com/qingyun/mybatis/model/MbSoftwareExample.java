package com.qingyun.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MbSoftwareExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    public MbSoftwareExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
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
     * This method corresponds to the database table mb_software
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
     * This method corresponds to the database table mb_software
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_software
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
     * This class corresponds to the database table mb_software
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

        public Criteria andSoftwareidIsNull() {
            addCriterion("SoftwareId is null");
            return (Criteria) this;
        }

        public Criteria andSoftwareidIsNotNull() {
            addCriterion("SoftwareId is not null");
            return (Criteria) this;
        }

        public Criteria andSoftwareidEqualTo(Long value) {
            addCriterion("SoftwareId =", value, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidNotEqualTo(Long value) {
            addCriterion("SoftwareId <>", value, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidGreaterThan(Long value) {
            addCriterion("SoftwareId >", value, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidGreaterThanOrEqualTo(Long value) {
            addCriterion("SoftwareId >=", value, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidLessThan(Long value) {
            addCriterion("SoftwareId <", value, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidLessThanOrEqualTo(Long value) {
            addCriterion("SoftwareId <=", value, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidIn(List<Long> values) {
            addCriterion("SoftwareId in", values, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidNotIn(List<Long> values) {
            addCriterion("SoftwareId not in", values, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidBetween(Long value1, Long value2) {
            addCriterion("SoftwareId between", value1, value2, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwareidNotBetween(Long value1, Long value2) {
            addCriterion("SoftwareId not between", value1, value2, "softwareid");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleIsNull() {
            addCriterion("SoftwareTitle is null");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleIsNotNull() {
            addCriterion("SoftwareTitle is not null");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleEqualTo(String value) {
            addCriterion("SoftwareTitle =", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleNotEqualTo(String value) {
            addCriterion("SoftwareTitle <>", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleGreaterThan(String value) {
            addCriterion("SoftwareTitle >", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleGreaterThanOrEqualTo(String value) {
            addCriterion("SoftwareTitle >=", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleLessThan(String value) {
            addCriterion("SoftwareTitle <", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleLessThanOrEqualTo(String value) {
            addCriterion("SoftwareTitle <=", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleLike(String value) {
            addCriterion("SoftwareTitle like", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleNotLike(String value) {
            addCriterion("SoftwareTitle not like", value, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleIn(List<String> values) {
            addCriterion("SoftwareTitle in", values, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleNotIn(List<String> values) {
            addCriterion("SoftwareTitle not in", values, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleBetween(String value1, String value2) {
            addCriterion("SoftwareTitle between", value1, value2, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwaretitleNotBetween(String value1, String value2) {
            addCriterion("SoftwareTitle not between", value1, value2, "softwaretitle");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicIsNull() {
            addCriterion("SoftwarePic is null");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicIsNotNull() {
            addCriterion("SoftwarePic is not null");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicEqualTo(String value) {
            addCriterion("SoftwarePic =", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicNotEqualTo(String value) {
            addCriterion("SoftwarePic <>", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicGreaterThan(String value) {
            addCriterion("SoftwarePic >", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicGreaterThanOrEqualTo(String value) {
            addCriterion("SoftwarePic >=", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicLessThan(String value) {
            addCriterion("SoftwarePic <", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicLessThanOrEqualTo(String value) {
            addCriterion("SoftwarePic <=", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicLike(String value) {
            addCriterion("SoftwarePic like", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicNotLike(String value) {
            addCriterion("SoftwarePic not like", value, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicIn(List<String> values) {
            addCriterion("SoftwarePic in", values, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicNotIn(List<String> values) {
            addCriterion("SoftwarePic not in", values, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicBetween(String value1, String value2) {
            addCriterion("SoftwarePic between", value1, value2, "softwarepic");
            return (Criteria) this;
        }

        public Criteria andSoftwarepicNotBetween(String value1, String value2) {
            addCriterion("SoftwarePic not between", value1, value2, "softwarepic");
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

        public Criteria andSoftwareremarksIsNull() {
            addCriterion("SoftwareRemarks is null");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksIsNotNull() {
            addCriterion("SoftwareRemarks is not null");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksEqualTo(String value) {
            addCriterion("SoftwareRemarks =", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksNotEqualTo(String value) {
            addCriterion("SoftwareRemarks <>", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksGreaterThan(String value) {
            addCriterion("SoftwareRemarks >", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksGreaterThanOrEqualTo(String value) {
            addCriterion("SoftwareRemarks >=", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksLessThan(String value) {
            addCriterion("SoftwareRemarks <", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksLessThanOrEqualTo(String value) {
            addCriterion("SoftwareRemarks <=", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksLike(String value) {
            addCriterion("SoftwareRemarks like", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksNotLike(String value) {
            addCriterion("SoftwareRemarks not like", value, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksIn(List<String> values) {
            addCriterion("SoftwareRemarks in", values, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksNotIn(List<String> values) {
            addCriterion("SoftwareRemarks not in", values, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksBetween(String value1, String value2) {
            addCriterion("SoftwareRemarks between", value1, value2, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andSoftwareremarksNotBetween(String value1, String value2) {
            addCriterion("SoftwareRemarks not between", value1, value2, "softwareremarks");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressIsNull() {
            addCriterion("iPhoneAddress is null");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressIsNotNull() {
            addCriterion("iPhoneAddress is not null");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressEqualTo(String value) {
            addCriterion("iPhoneAddress =", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressNotEqualTo(String value) {
            addCriterion("iPhoneAddress <>", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressGreaterThan(String value) {
            addCriterion("iPhoneAddress >", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressGreaterThanOrEqualTo(String value) {
            addCriterion("iPhoneAddress >=", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressLessThan(String value) {
            addCriterion("iPhoneAddress <", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressLessThanOrEqualTo(String value) {
            addCriterion("iPhoneAddress <=", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressLike(String value) {
            addCriterion("iPhoneAddress like", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressNotLike(String value) {
            addCriterion("iPhoneAddress not like", value, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressIn(List<String> values) {
            addCriterion("iPhoneAddress in", values, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressNotIn(List<String> values) {
            addCriterion("iPhoneAddress not in", values, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressBetween(String value1, String value2) {
            addCriterion("iPhoneAddress between", value1, value2, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andIphoneaddressNotBetween(String value1, String value2) {
            addCriterion("iPhoneAddress not between", value1, value2, "iphoneaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressIsNull() {
            addCriterion("AndroidAddress is null");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressIsNotNull() {
            addCriterion("AndroidAddress is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressEqualTo(String value) {
            addCriterion("AndroidAddress =", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressNotEqualTo(String value) {
            addCriterion("AndroidAddress <>", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressGreaterThan(String value) {
            addCriterion("AndroidAddress >", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressGreaterThanOrEqualTo(String value) {
            addCriterion("AndroidAddress >=", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressLessThan(String value) {
            addCriterion("AndroidAddress <", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressLessThanOrEqualTo(String value) {
            addCriterion("AndroidAddress <=", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressLike(String value) {
            addCriterion("AndroidAddress like", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressNotLike(String value) {
            addCriterion("AndroidAddress not like", value, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressIn(List<String> values) {
            addCriterion("AndroidAddress in", values, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressNotIn(List<String> values) {
            addCriterion("AndroidAddress not in", values, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressBetween(String value1, String value2) {
            addCriterion("AndroidAddress between", value1, value2, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andAndroidaddressNotBetween(String value1, String value2) {
            addCriterion("AndroidAddress not between", value1, value2, "androidaddress");
            return (Criteria) this;
        }

        public Criteria andQrcodeIsNull() {
            addCriterion("QRCode is null");
            return (Criteria) this;
        }

        public Criteria andQrcodeIsNotNull() {
            addCriterion("QRCode is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodeEqualTo(String value) {
            addCriterion("QRCode =", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotEqualTo(String value) {
            addCriterion("QRCode <>", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThan(String value) {
            addCriterion("QRCode >", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("QRCode >=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThan(String value) {
            addCriterion("QRCode <", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThanOrEqualTo(String value) {
            addCriterion("QRCode <=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLike(String value) {
            addCriterion("QRCode like", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotLike(String value) {
            addCriterion("QRCode not like", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeIn(List<String> values) {
            addCriterion("QRCode in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotIn(List<String> values) {
            addCriterion("QRCode not in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeBetween(String value1, String value2) {
            addCriterion("QRCode between", value1, value2, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotBetween(String value1, String value2) {
            addCriterion("QRCode not between", value1, value2, "qrcode");
            return (Criteria) this;
        }

        public Criteria andTitlepicIsNull() {
            addCriterion("TitlePic is null");
            return (Criteria) this;
        }

        public Criteria andTitlepicIsNotNull() {
            addCriterion("TitlePic is not null");
            return (Criteria) this;
        }

        public Criteria andTitlepicEqualTo(String value) {
            addCriterion("TitlePic =", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicNotEqualTo(String value) {
            addCriterion("TitlePic <>", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicGreaterThan(String value) {
            addCriterion("TitlePic >", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicGreaterThanOrEqualTo(String value) {
            addCriterion("TitlePic >=", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicLessThan(String value) {
            addCriterion("TitlePic <", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicLessThanOrEqualTo(String value) {
            addCriterion("TitlePic <=", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicLike(String value) {
            addCriterion("TitlePic like", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicNotLike(String value) {
            addCriterion("TitlePic not like", value, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicIn(List<String> values) {
            addCriterion("TitlePic in", values, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicNotIn(List<String> values) {
            addCriterion("TitlePic not in", values, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicBetween(String value1, String value2) {
            addCriterion("TitlePic between", value1, value2, "titlepic");
            return (Criteria) this;
        }

        public Criteria andTitlepicNotBetween(String value1, String value2) {
            addCriterion("TitlePic not between", value1, value2, "titlepic");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeIsNull() {
            addCriterion("iPhoneQRCode is null");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeIsNotNull() {
            addCriterion("iPhoneQRCode is not null");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeEqualTo(String value) {
            addCriterion("iPhoneQRCode =", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeNotEqualTo(String value) {
            addCriterion("iPhoneQRCode <>", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeGreaterThan(String value) {
            addCriterion("iPhoneQRCode >", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("iPhoneQRCode >=", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeLessThan(String value) {
            addCriterion("iPhoneQRCode <", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeLessThanOrEqualTo(String value) {
            addCriterion("iPhoneQRCode <=", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeLike(String value) {
            addCriterion("iPhoneQRCode like", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeNotLike(String value) {
            addCriterion("iPhoneQRCode not like", value, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeIn(List<String> values) {
            addCriterion("iPhoneQRCode in", values, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeNotIn(List<String> values) {
            addCriterion("iPhoneQRCode not in", values, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeBetween(String value1, String value2) {
            addCriterion("iPhoneQRCode between", value1, value2, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andIphoneqrcodeNotBetween(String value1, String value2) {
            addCriterion("iPhoneQRCode not between", value1, value2, "iphoneqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeIsNull() {
            addCriterion("AndroidQRCode is null");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeIsNotNull() {
            addCriterion("AndroidQRCode is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeEqualTo(String value) {
            addCriterion("AndroidQRCode =", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeNotEqualTo(String value) {
            addCriterion("AndroidQRCode <>", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeGreaterThan(String value) {
            addCriterion("AndroidQRCode >", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("AndroidQRCode >=", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeLessThan(String value) {
            addCriterion("AndroidQRCode <", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeLessThanOrEqualTo(String value) {
            addCriterion("AndroidQRCode <=", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeLike(String value) {
            addCriterion("AndroidQRCode like", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeNotLike(String value) {
            addCriterion("AndroidQRCode not like", value, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeIn(List<String> values) {
            addCriterion("AndroidQRCode in", values, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeNotIn(List<String> values) {
            addCriterion("AndroidQRCode not in", values, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeBetween(String value1, String value2) {
            addCriterion("AndroidQRCode between", value1, value2, "androidqrcode");
            return (Criteria) this;
        }

        public Criteria andAndroidqrcodeNotBetween(String value1, String value2) {
            addCriterion("AndroidQRCode not between", value1, value2, "androidqrcode");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table mb_software
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
     * This class corresponds to the database table mb_software
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