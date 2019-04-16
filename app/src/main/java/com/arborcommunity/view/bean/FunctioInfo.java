package com.arborcommunity.view.bean;

import android.content.Intent;

import java.util.Date;

/**
 * Created by pc on 2018/9/1.
 */
public class FunctioInfo {
    private Integer id;
    private String name;//名称
    private Date time;
    private String imgUrl;
    private String infoType;//信息类型  1:政策公告  2: 家事讨论     3:七彩生活 4:信息共享 5: 广告灌水  6:理财交流
    private String financeInfo;//理财信息

    public FunctioInfo(Integer id,String name,Date time,String imgUrl,String infoType){
        this.id = id;
        this.name = name;
        this.time = time;
        this.imgUrl = imgUrl;
        this.infoType = infoType;
    }
    public FunctioInfo(Integer id,String name,Date time,String imgUrl,String infoType,String financeInfo){
        this.id = id;
        this.name = name;
        this.time = time;
        this.imgUrl = imgUrl;
        this.infoType = infoType;
        this.financeInfo= financeInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getFinanceInfo() {
        return financeInfo;
    }

    public void setFinanceInfo(String financeInfo) {
        this.financeInfo = financeInfo;
    }
}
