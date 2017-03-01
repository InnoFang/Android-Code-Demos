package com.example.innf.volleydemo.bean;

/**
 * Author: Inno Fang
 * Time: 2016/12/24 11:09
 * Description:
 * 对应Volley返回的response(JSON字符串)
 */

public class Response {
    private String resultcode;
    private String reason;
    private Result result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public String getReason() {
        return reason;
    }


    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result{
        private String province;
        private String city;
        private String areacode;
        private String zip;
        private String company;
        private String card;

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
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

        public String getCard() {
            return card;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setCard(String card) {
            this.card = card;
        }
    }

    @Override
    public String toString() {
        return "Response [resultcode=" + resultcode + ", reason=" + reason + ", result=" + result + ", error_code="
                + error_code + ", getResultcode()=" + getResultcode() + ", getReason()=" + getReason()
                + ", getError_code()=" + getError_code() + ", getResult()=" + getResult() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
}
