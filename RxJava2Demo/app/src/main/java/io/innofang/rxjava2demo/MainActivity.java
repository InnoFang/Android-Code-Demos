package io.innofang.rxjava2demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493904614&di=877dc7601c6c5379d4003e15eb4f3dbf&imgtype=jpg&er=1&src=http%3A%2F%2Fv1.qzone.cc%2Fskin%2F201508%2F14%2F16%2F47%2F55cdab06e0894774.jpg%21600x600.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        final ImageView imageView = (ImageView) findViewById(R.id.image_view);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.create(new ObservableOnSubscribe<Bitmap>() {
                    @Override
                    public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                        // 网络请求
                        // 将请求资源转化为 Bitmap
                        if (null != bitmap) {
                            e.onNext(bitmap);
                        }
                    }
                }).subscribeOn(Schedulers.io())                     /* 在子线程中进行网络请求 */
                        .observeOn(AndroidSchedulers.mainThread())  /* 在主线程给 View 添加请求的资源 */
                        .subscribe(new Observer<Bitmap>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Bitmap value) {
                                imageView.setImageBitmap(value);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });


    }

}
