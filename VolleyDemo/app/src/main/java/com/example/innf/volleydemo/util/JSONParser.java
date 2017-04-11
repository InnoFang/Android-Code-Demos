package com.example.innf.volleydemo.util;

import android.util.Log;

import com.example.innf.volleydemo.bean.PhoneNumberInfo;
import com.example.innf.volleydemo.bean.Response;
import com.google.gson.Gson;

/**
 * Author: Inno Fang
 * Time: 2016/12/24 10:04
 * Description:
 */

public class JSONParser {
    private static final String TAG = "JSONParser";
    public static PhoneNumberInfo parseJSON(String jsonData){

        Gson gson = new Gson();
        Response response = gson.fromJson(jsonData, Response.class);
        Log.i(TAG, response.toString());

        PhoneNumberInfo phoneNumberInfo = new PhoneNumberInfo();
        Response.Result result = response.getResult();
        phoneNumberInfo.setProvince(result.getProvince());
        phoneNumberInfo.setCity(result.getCity());
        phoneNumberInfo.setAreacode(result.getAreacode());
        phoneNumberInfo.setZip(result.getZip());
        phoneNumberInfo.setCompany(result.getCompany());
        Log.i(TAG, phoneNumberInfo.toString());
        return phoneNumberInfo;
    }
}
