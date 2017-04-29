package io.innofang.rxjava2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import io.innofang.rxjava2demo.imageloader.RxImageLoader;

public class LoadImageActivity extends AppCompatActivity {

    private static final String TAG = "LoadImageActivity";
    private static final String URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493904614&di=877dc7601c6c5379d4003e15eb4f3dbf&imgtype=jpg&er=1&src=http%3A%2F%2Fv1.qzone.cc%2Fskin%2F201508%2F14%2F16%2F47%2F55cdab06e0894774.jpg%21600x600.jpg";

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        final ImageView imageView = (ImageView) findViewById(R.id.image_view);
        findViewById(R.id.load_image_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxImageLoader.with(LoadImageActivity.this)
                        .load(URL)
                        .into(imageView);
            }
        });

        /*final Observable memoryObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("null");
                e.onComplete();
            }
        });
        final Observable diskObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("disk");
                e.onComplete();
            }
        });
        final Observable networkObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("network");
                e.onComplete();
            }
        });

        findViewById(R.id.load_image_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.concat(memoryObservable, diskObservable, networkObservable)
                        .first(new Function<String, Boolean>() {
                            @Override
                            public Boolean apply(String s) throws Exception {
                                return s.equals("null");
                            }
                        })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.i(TAG, "accept: " + s);
                            }
                        });
            }
        });*/
    }
}
