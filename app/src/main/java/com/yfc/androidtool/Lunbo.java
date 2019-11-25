package com.yfc.androidtool;

import java.io.Serializable;

public class Lunbo implements Serializable {

    /**
     * created : 2019-07-04T23:04:19.000+0000
     * updated : 2019-07-04T23:04:19.000+0000
     * id : 1
     * recordId : 570000000001
     * imgUrl : http://houyuantest.oss-cn-zhangjiakou.aliyuncs.com/images/2019/06/28/15616917881383101.png
     * type : 2
     * detailId : 20000000028
     * serialNum : 1
     * insertTime : 2019-07-05 15:04:19
     * section : 1
     */

    private String created;
    private String updated;
    private int id;
    private long recordId;//记录id
    private String imgUrl;//图片url
    private int type;//类型，1为活动详情，2为商品详情，3为帖子详情，4为游戏下单页面
    private long detailId;//详情页id
    private int serialNum;//序号，轮播区序号1-5，第一张广告图序号6，第二张广告图序号7，活动专区序号8-10，优质专区序号11-14
    private String insertTime;//插入记录的时间
    private int section;//分区，1为轮播区，2为第一张广告区，3为第二张广告区，4为活动专区，5为优质推荐专区
    private String detailUrl;//活动链接

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
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

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }
}
