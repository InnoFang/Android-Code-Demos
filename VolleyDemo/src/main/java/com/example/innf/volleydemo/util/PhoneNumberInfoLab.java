package com.example.innf.volleydemo.util;

import com.example.innf.volleydemo.bean.PhoneNumberInfo;

import java.util.HashMap;

/**
 * Author: Inno Fang
 * Time: 2016/12/24 09:03
 * Description:
 */

public class PhoneNumberInfoLab {

    private HashMap<String, PhoneNumberInfo> mPhoneNumberInfoHashMap;

    private PhoneNumberInfoLab(){
        mPhoneNumberInfoHashMap = new HashMap<>();
    }

    public static PhoneNumberInfoLab get(){
        return SingletonHolder.instance;
    }

    public HashMap<String, PhoneNumberInfo> getPhoneNumberInfoHashMap() {
        return mPhoneNumberInfoHashMap;
    }

    private static class SingletonHolder{
        private static final PhoneNumberInfoLab instance = new PhoneNumberInfoLab();
    }

}
