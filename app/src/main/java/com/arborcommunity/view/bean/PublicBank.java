package com.arborcommunity.view.bean;

import android.content.Intent;

/**
 * Created by pc on 2018/12/23.
 * 公益银行实体
 */
public class PublicBank {

    private Intent id;
    private String imgUrl;//图片地址
    private String content;//内容
    private String value;//公益值

    public PublicBank(String imgUrl,String content,String value){
        this.imgUrl = imgUrl;
        this.content = content;
        this.value = value;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Intent getId() {
        return id;
    }

    public void setId(Intent id) {
        this.id = id;
    }
}
