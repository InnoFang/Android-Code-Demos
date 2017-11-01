package com.innofang.callbackdemo.call_back_demo_2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.innofang.callbackdemo.R;

public class LoadImageActivity extends AppCompatActivity {

    private static final String IMG_URL = "http://img5.imgtn.bdimg.com/it/u=992411260,1164059995&fm=11&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loade_image);

        final ImageView imageView = (ImageView) findViewById(R.id.image_view);

        ImageUtil.loadeIamge(IMG_URL, new ImageUtil.LoadeImageListener() {
            @Override
            public void onLoadImageListener(final Bitmap bitmap) {
                LoadImageActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });

    }
}
