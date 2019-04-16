package com.arborcommunity.view.bean;


import java.io.Serializable;

/**
 * @author 李品先
 * @description:社区表
 * @Date 2019-03-09 17:22
 */

public class Community implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    private String name; //社区名称

    private String cityId; //社区所在城市

    private String describe;//社区描叙

    private String address;//社区地址

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
