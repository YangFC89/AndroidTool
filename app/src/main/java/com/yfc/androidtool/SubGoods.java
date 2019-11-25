package com.yfc.androidtool;

import java.io.Serializable;

public class SubGoods implements Serializable {


    private String created;
    private String updated;
    private int id;
    private String subGoodId;//子商品id
    private String parentGoodId; //父商品id
    private String goodSpec;//说明
    private double goodPrice;//价格
    private int goodSavedCount;//库存
    private String goodThumbnailLogo;//缩略图
    private String goodBigLogo;//大图
    private String insertTime;//入库时间
    private String goodName;//商品名
    private String companyUserId;//企业id
    /**
     * subGoodId : 440000000006
     * parentGoodId : 20000000027
     * goodPrice : 28.65
     * companyUserId : 10000000001
     * goodCostPrice : null
     * goodSaleNum : null
     * isIntroduced : null
     * goodRecommendations : null
     */

    private String goodCostPrice;
    private String goodSaleNum;
    private String isIntroduced;
    private String goodRecommendations;
    private String appPrice;
    private String appOldPrice;
    private String actionPrice;
    private String parentGoodVerifyStatus;

    public String getActionPrice() {
        return actionPrice;
    }

    public void setActionPrice(String actionPrice) {
        this.actionPrice = actionPrice;
    }

    public String getParentGoodVerifyStatus() {
        return parentGoodVerifyStatus;
    }

    public void setParentGoodVerifyStatus(String parentGoodVerifyStatus) {
        this.parentGoodVerifyStatus = parentGoodVerifyStatus;
    }

    public String getAppPrice() {
        return appPrice;
    }

    public void setAppPrice(String appPrice) {
        this.appPrice = appPrice;
    }

    public String getAppOldPrice() {
        return appOldPrice;
    }

    public void setAppOldPrice(String appOldPrice) {
        this.appOldPrice = appOldPrice;
    }

    /**
     * subGoodId : 440000000046
     * parentGoodId : 20000000066
     * goodPrice : 200
     * companyUserId : 10000000001
     * goodCostPrice : 100
     * goodSaleNum : 0
     * isIntroduced : 1
     * isYouLike : 1
     * actionType : null
     */

    private int isYouLike;
    private String actionType;

    public String getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(String companyUserId) {
        this.companyUserId = companyUserId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubGoodId() {
        return subGoodId;
    }

    public void setSubGoodId(String subGoodId) {
        this.subGoodId = subGoodId;
    }

    public String getParentGoodId() {
        return parentGoodId;
    }

    public void setParentGoodId(String parentGoodId) {
        this.parentGoodId = parentGoodId;
    }

    public String getGoodSpec() {
        return goodSpec;
    }

    public void setGoodSpec(String goodSpec) {
        this.goodSpec = goodSpec;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getGoodSavedCount() {
        return goodSavedCount;
    }

    public void setGoodSavedCount(int goodSavedCount) {
        this.goodSavedCount = goodSavedCount;
    }

    public String getGoodThumbnailLogo() {
        return goodThumbnailLogo;
    }

    public void setGoodThumbnailLogo(String goodThumbnailLogo) {
        this.goodThumbnailLogo = goodThumbnailLogo;
    }

    public String getGoodBigLogo() {
        return goodBigLogo;
    }

    public void setGoodBigLogo(String goodBigLogo) {
        this.goodBigLogo = goodBigLogo;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }


    public String getGoodCostPrice() {
        return goodCostPrice;
    }

    public void setGoodCostPrice(String goodCostPrice) {
        this.goodCostPrice = goodCostPrice;
    }

    public String getGoodSaleNum() {
        return goodSaleNum;
    }

    public void setGoodSaleNum(String goodSaleNum) {
        this.goodSaleNum = goodSaleNum;
    }

    public String getIsIntroduced() {
        return isIntroduced;
    }

    public void setIsIntroduced(String isIntroduced) {
        this.isIntroduced = isIntroduced;
    }

    public String getGoodRecommendations() {
        return goodRecommendations;
    }

    public void setGoodRecommendations(String goodRecommendations) {
        this.goodRecommendations = goodRecommendations;
    }

    public int getIsYouLike() {
        return isYouLike;
    }

    public void setIsYouLike(int isYouLike) {
        this.isYouLike = isYouLike;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}

