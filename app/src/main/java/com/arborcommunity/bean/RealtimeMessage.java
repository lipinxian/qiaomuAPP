package com.arborcommunity.bean;

import com.arborcommunity.view.bean.NewsContent;

/**
 * Created by pc on 2018/8/28.
 */
public class RealtimeMessage {
    private NewsContent message;
    private String date;

    public RealtimeMessage(NewsContent message,String date){
        this.message = message;
        this.date = date;
    }

    public NewsContent getMessage() {
        return message;
    }

    public void setMessage(NewsContent message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
