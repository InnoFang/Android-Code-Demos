package com.example.innf.volleydemo.model;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.innf.volleydemo.app.MyApplication;
import com.example.innf.volleydemo.bean.PhoneNumberInfo;
import com.example.innf.volleydemo.constant.Constant;
import com.example.innf.volleydemo.model.impl.IQueryPhoneNumberModel;
import com.example.innf.volleydemo.presenter.impl.OnQueryPhoneNumberListener;
import com.example.innf.volleydemo.util.JSONParser;
import com.example.innf.volleydemo.util.PhoneNumberInfoLab;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 22:42
 * Description:
 */

public class QueryPhoneNumberModel implements IQueryPhoneNumberModel {
    private static final String TAG = "TAG";

    private HashMap<String, PhoneNumberInfo> mQueryPhoneNumberHashMap;

    @Override
    public void loadPhoneNumberInfo(final String phoneNumber, final OnQueryPhoneNumberListener listener) {
        mQueryPhoneNumberHashMap = PhoneNumberInfoLab.get().getPhoneNumberInfoHashMap();
        PhoneNumberInfo phoneNumberInfo = mQueryPhoneNumberHashMap.get(phoneNumber);
        if (phoneNumberInfo == null) {
            StringRequest request = new StringRequest(Request.Method.POST, Constant.JUHE_URL_POST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i(TAG, response);
                            PhoneNumberInfo pni = JSONParser.parseJSON(response);
                            mQueryPhoneNumberHashMap.put(phoneNumber, pni);
                            listener.onSuccess(pni);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onError(error.toString());
                        }
                    }){
                /*
                重写getParams()
                用户在Volley中使用post方式请求数据中参数的传递
                 */
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> hashMap = new HashMap<>();
                    hashMap.put("phone", phoneNumber);
                    hashMap.put("key", Constant.JUHE_API_KEY);
                    return hashMap;
                }
            };
            request.setTag(Constant.VOLLEY_TAG);      //设置标签
            MyApplication.getHttpQueues().add(request);//加入请求队列
        } else {
            listener.onSuccess(phoneNumberInfo); // 若hashMap中已经有过记录，则直接显示
        }
    }
}
