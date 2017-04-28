package io.innofang.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 10:23
 * Description:
 */


public interface GankIOService {

    @GET("api/data/{gank}/{num}/{page}")
    Call<Gank> requestGank(@Path("gank") String gank,
                                 @Path("num") int num,
                                 @Path("page") int page);
}
