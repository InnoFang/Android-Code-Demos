package io.innofang.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

     private GankIOService mGankIOService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGankIOService = retrofit.create(GankIOService.class);

    }

    public void requestAPI(View view) {
        Call<Gank> ganks = mGankIOService.requestGank("Android", 10, 1);
        ganks.enqueue(new Callback<Gank>() {
            @Override
            public void onResponse(Call<Gank> call, Response<Gank> response) {
                Gank result = response.body();
                int size = result.getResults().size();
                List<Gank.ResultsBean> resultsBean = result.getResults();
                StringBuilder sb = new StringBuilder("");
                for (int i = 0; i < size; i++) {
                    sb.append(resultsBean.get(i).getDesc() + "\n");
                }
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                Log.i(TAG, "onResponse: " + sb.toString());
            }

            @Override
            public void onFailure(Call<Gank> call, Throwable t) {

            }
        });
    }
}
