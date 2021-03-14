package com.yuandian.entity;

import java.util.ArrayList;
import java.util.List;

public class GoodsPoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsPoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSloganIsNull() {
            addCriterion("slogan is null");
            return (Criteria) this;
        }

        public Criteria andSloganIsNotNull() {
            addCriterion("slogan is not null");
            return (Criteria) this;
        }

        public Criteria andSloganEqualTo(String value) {
            addCriterion("slogan =", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotEqualTo(String value) {
            addCriterion("slogan <>", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThan(String value) {
            addCriterion("slogan >", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganGreaterThanOrEqualTo(String value) {
            addCriterion("slogan >=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThan(String value) {
            addCriterion("slogan <", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLessThanOrEqualTo(String value) {
            addCriterion("slogan <=", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganLike(String value) {
            addCriterion("slogan like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotLike(String value) {
            addCriterion("slogan not like", value, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganIn(List<String> values) {
            addCriterion("slogan in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotIn(List<String> values) {
            addCriterion("slogan not in", values, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganBetween(String value1, String value2) {
            addCriterion("slogan between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andSloganNotBetween(String value1, String value2) {
            addCriterion("slogan not between", value1, value2, "slogan");
            return (Criteria) this;
        }

        public Criteria andStackableIsNull() {
            addCriterion("stackable is null");
            return (Criteria) this;
        }

        public Criteria andStackableIsNotNull() {
            addCriterion("stackable is not null");
            return (Criteria) this;
        }

        public Criteria andStackableEqualTo(Byte value) {
            addCriterion("stackable =", value, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableNotEqualTo(Byte value) {
            addCriterion("stackable <>", value, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableGreaterThan(Byte value) {
            addCriterion("stackable >", value, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableGreaterThanOrEqualTo(Byte value) {
            addCriterion("stackable >=", value, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableLessThan(Byte value) {
            addCriterion("stackable <", value, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableLessThanOrEqualTo(Byte value) {
            addCriterion("stackable <=", value, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableIn(List<Byte> values) {
            addCriterion("stackable in", values, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableNotIn(List<Byte> values) {
            addCriterion("stackable not in", values, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableBetween(Byte value1, Byte value2) {
            addCriterion("stackable between", value1, value2, "stackable");
            return (Criteria) this;
        }

        public Criteria andStackableNotBetween(Byte value1, Byte value2) {
            addCriterion("stackable not between", value1, value2, "stackable");
            return (Criteria) this;
        }

        public Criteria andThumPicIsNull() {
            addCriterion("thum_pic is null");
            return (Criteria) this;
        }

        public Criteria andThumPicIsNotNull() {
            addCriterion("thum_pic is not null");
            return (Criteria) this;
        }

        public Criteria andThumPicEqualTo(String value) {
            addCriterion("thum_pic =", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicNotEqualTo(String value) {
            addCriterion("thum_pic <>", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicGreaterThan(String value) {
            addCriterion("thum_pic >", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicGreaterThanOrEqualTo(String value) {
            addCriterion("thum_pic >=", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicLessThan(String value) {
            addCriterion("thum_pic <", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicLessThanOrEqualTo(String value) {
            addCriterion("thum_pic <=", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicLike(String value) {
            addCriterion("thum_pic like", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicNotLike(String value) {
            addCriterion("thum_pic not like", value, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicIn(List<String> values) {
            addCriterion("thum_pic in", values, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicNotIn(List<String> values) {
            addCriterion("thum_pic not in", values, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicBetween(String value1, String value2) {
            addCriterion("thum_pic between", value1, value2, "thumPic");
            return (Criteria) this;
        }

        public Criteria andThumPicNotBetween(String value1, String value2) {
            addCriterion("thum_pic not between", value1, value2, "thumPic");
            return (Criteria) this;
        }

        public Criteria andIosResIsNull() {
            addCriterion("ios_res is null");
            return (Criteria) this;
        }

        public Criteria andIosResIsNotNull() {
            addCriterion("ios_res is not null");
            return (Criteria) this;
        }

        public Criteria andIosResEqualTo(String value) {
            addCriterion("ios_res =", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResNotEqualTo(String value) {
            addCriterion("ios_res <>", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResGreaterThan(String value) {
            addCriterion("ios_res >", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResGreaterThanOrEqualTo(String value) {
            addCriterion("ios_res >=", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResLessThan(String value) {
            addCriterion("ios_res <", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResLessThanOrEqualTo(String value) {
            addCriterion("ios_res <=", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResLike(String value) {
            addCriterion("ios_res like", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResNotLike(String value) {
            addCriterion("ios_res not like", value, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResIn(List<String> values) {
            addCriterion("ios_res in", values, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResNotIn(List<String> values) {
            addCriterion("ios_res not in", values, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResBetween(String value1, String value2) {
            addCriterion("ios_res between", value1, value2, "iosRes");
            return (Criteria) this;
        }

        public Criteria andIosResNotBetween(String value1, String value2) {
            addCriterion("ios_res not between", value1, value2, "iosRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResIsNull() {
            addCriterion("android_res is null");
            return (Criteria) this;
        }

        public Criteria andAndroidResIsNotNull() {
            addCriterion("android_res is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidResEqualTo(String value) {
            addCriterion("android_res =", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResNotEqualTo(String value) {
            addCriterion("android_res <>", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResGreaterThan(String value) {
            addCriterion("android_res >", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResGreaterThanOrEqualTo(String value) {
            addCriterion("android_res >=", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResLessThan(String value) {
            addCriterion("android_res <", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResLessThanOrEqualTo(String value) {
            addCriterion("android_res <=", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResLike(String value) {
            addCriterion("android_res like", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResNotLike(String value) {
            addCriterion("android_res not like", value, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResIn(List<String> values) {
            addCriterion("android_res in", values, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResNotIn(List<String> values) {
            addCriterion("android_res not in", values, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResBetween(String value1, String value2) {
            addCriterion("android_res between", value1, value2, "androidRes");
            return (Criteria) this;
        }

        public Criteria andAndroidResNotBetween(String value1, String value2) {
            addCriterion("android_res not between", value1, value2, "androidRes");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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