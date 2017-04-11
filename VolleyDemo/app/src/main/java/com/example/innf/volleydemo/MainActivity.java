package com.example.innf.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.innf.volleydemo.app.MyApplication;
import com.example.innf.volleydemo.constant.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private static final String STRING_GET_TAG = "string_get";
    private static final String STRING_POST_TAG = "string_post";
    private static final String JSON_OBJECT_GET_TAG = "json_object_get";
    private static final String JSON_OBJECT_POST_TAG = "json_object_post";

    private String tag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tag = volleyGetStringRequest();       // 获取字符串get请求
//        tag = volleyGetJsonObjectRequest();  // 获取JsonObject的get请求

        tag = volleyPostStringRequest();        // 获取字符串post请求
//        tag = volleyPostJsonObjectRequest();    // 获取JsonObject的post请求

//        volleyRequestGetByCustom();

    }

    private void volleyRequestGetByCustom() {
        VolleyRequest.RequestGet(this, Constant.JUHE_URL_GET, STRING_GET_TAG, new VolleyInterface(this, VolleyInterface.sListener, VolleyInterface.sErrorListener) {
            @Override
            public void onMySuccess(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                Log.i(TAG, result);
            }

            @Override
            public void onMyError(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    与Activity的生命周期进行关联
     */
    @Override
    protected void onStop() {
        super.onStop();
        // cancelAll() 通过给定的tag值，将指定的队列全部关闭
        MyApplication.getHttpQueues().cancelAll(tag);
    }

    private String volleyPostJsonObjectRequest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("phone", "13429667914");
        hashMap.put("key", Constant.JUHE_API_KEY);
        JSONObject object = new JSONObject(hashMap);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constant.JUHE_URL_POST, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        request.setTag(JSON_OBJECT_POST_TAG);
        MyApplication.getHttpQueues().add(request);
        return request.getTag().toString();
    }

    private String volleyPostStringRequest() {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.JUHE_URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            /*
            getParams()
            用户在Volley中使用post方式请求数据中参数的传递
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("phone", "13429667914");
                hashMap.put("key", "562609042fbd47baa063b1a2c4637758");
                return hashMap;
            }
        };
        request.setTag(STRING_POST_TAG);
        MyApplication.getHttpQueues().add(request);
        return request.getTag().toString();
    }


    private String volleyGetJsonObjectRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constant.JUHE_URL_GET, null, // 用post方式时，需更改为带请求参数的Object
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
            });
        request.setTag(JSON_OBJECT_GET_TAG);
        MyApplication.getHttpQueues().add(request);
        return request.getTag().toString();
    }


    private String volleyGetStringRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, Constant.JUHE_URL_GET,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag(STRING_GET_TAG);
        MyApplication.getHttpQueues().add(request);
        return request.getTag().toString();
    }
}
