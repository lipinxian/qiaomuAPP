package com.arborcommunity.view.bean;

import android.net.Uri;


import java.util.Date;

/**
 * Created by pc on 2018/8/12.
 */
public class NewsContent {
    private String newsMessageUrl;//新闻图片或者视频地址
    private String newsName;//新闻名称
    private int seePeople;//观看人数
    private String newsAbstract;//新闻摘要
    private Date time;
    private Long id;
    public NewsContent.Type type;//新闻类型
    public NewsContent.Direct direct;

    private String from;//新闻来源
    public  NewsContent (){

    }
    public NewsContent(String  newsMessageUrl,String newsName,int seePeople,String newsAbstract,Long id){
        this.newsMessageUrl = newsMessageUrl;
        this.newsName = newsName;
        this.seePeople =seePeople;
        this.newsAbstract = newsAbstract;
        this.id = id;
    }
    public NewsContent(String  newsMessageUrl,String newsName,int seePeople,String newsAbstract){
        this.newsMessageUrl = newsMessageUrl;
        this.newsName = newsName;
        this.seePeople =seePeople;
        this.newsAbstract = newsAbstract;
    }

    public String getNewsMessageUrl() {
        return newsMessageUrl;
    }

    public void setNewsMessageUrl(String newsMessageUrl) {
        this.newsMessageUrl = newsMessageUrl;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public int getSeePeople() {
        return seePeople;
    }

    public void setSeePeople(int seePeople) {
        this.seePeople = seePeople;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public Direct getDirect() {
        return direct;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public static enum Direct {
        SEND,
        RECEIVE;

        private Direct() {
        }
    }
    public static enum Status {
        SUCCESS,
        FAIL,
        INPROGRESS,
        CREATE;

        private Status() {
        }
    }

    public static enum Type {
        TXT,
        IMAGE,
        VIDEO,
        LOCATION,
        VOICE,
        FILE,
        CMD;

        private Type() {
        }
    }
}
