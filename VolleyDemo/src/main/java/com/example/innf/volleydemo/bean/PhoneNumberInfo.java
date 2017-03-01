package com.example.innf.volleydemo.bean;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 21:52
 * Description:
 */

public class PhoneNumberInfo {
    private static final String TAG = "PhoneNumberInfo";

    private String province;
    private String city;
    private String areacode;
    private String zip;
    private String company;

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getAreacode() {
        return areacode;
    }

    public String getZip() {
        return zip;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "[province:" + getProvince() +
                ", city:" + getCity() +
                ", areacode:" + getAreacode() +
                ", zip:" + getZip() +
                ", company:" + getCompany() +
                "]";

    }
}
