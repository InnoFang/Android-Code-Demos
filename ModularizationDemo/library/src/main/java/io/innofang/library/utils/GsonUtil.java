package io.innofang.library.utils;

import com.google.gson.Gson;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 10:08
 * Description:
 */


public class GsonUtil {

    public static <T extends Object> T parseJson(String jsonData, Class<T> clz){
        Gson gson = new Gson();
        return gson.fromJson(jsonData, clz);
    }

}
