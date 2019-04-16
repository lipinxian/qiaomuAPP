package com.arborcommunity.view.bean;

/**
 * @Author: 李品先
 * @Date: 2019/1/21 17:12
 * @Description: 省份和城市接收实体
 */
public class ProvinceCityData {

    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
