package io.innofang.rxjava2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.innofang.rxjava2demo.imageloader.RxImageLoader;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class LoadImageActivity extends AppCompatActivity {

    private static final String TAG = "LoadImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);

        List<String> urls = initUrls();
        List<ImageView> imageViews = initImageViews();

        for (int i = 0; i < urls.size(); i++) {
            RxImageLoader.with(LoadImageActivity.this)
                    .load(urls.get(i))
                    .into(imageViews.get(i));
        }

    }

    private List<String> initUrls() {
        List<String> urls = new ArrayList<>();
        urls.add("http://easyread.ph.126.net/29GFsDKgAFvANv3o-2DA0g==/7916706920849407429.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=3752382181,3307083341&fm=23&gp=0.jpg");
        urls.add("http://v1.qzone.cc/avatar/201507/14/11/36/55a483bc4c47c278.jpg%21200x200.jpg");
        urls.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3066850610,2144129312&fm=11&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493450866249&di=3e5790cb0d2b4c7b71bbb91629318465&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F0ea6b3e2d15216471d08566f46270840588506f2.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=3008488658,3117897653&fm=23&gp=0.jpg");
        urls.add("http://i0.hdslb.com/video/c8/c8cba43c9839e460262bfbe3190a8f98.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=474472604,2300242846&fm=23&gp=0.jpg                                                                                         ");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1410082068,46221431&fm=23&gp=0.jpg");
        return urls;
    }

    private List<ImageView> initImageViews(){
        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add((ImageView) findViewById(R.id.image_view_1));
        imageViews.add((ImageView) findViewById(R.id.image_view_2));
        imageViews.add((ImageView) findViewById(R.id.image_view_3));
        imageViews.add((ImageView) findViewById(R.id.image_view_4));
        imageViews.add((ImageView) findViewById(R.id.image_view_5));
        imageViews.add((ImageView) findViewById(R.id.image_view_6));
        imageViews.add((ImageView) findViewById(R.id.image_view_7));
        imageViews.add((ImageView) findViewById(R.id.image_view_8));
        imageViews.add((ImageView) findViewById(R.id.image_view_9));
        return imageViews;
    }

    @SuppressWarnings("unchecked")
    private void loadImageTest() {
        final Observable memoryObservable = Observable.create(new ObservableOnSubscribe<String>() {
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

        /*findViewById(R.id.load_image_button).setOnClickListener(new View.OnClickListener() {
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
