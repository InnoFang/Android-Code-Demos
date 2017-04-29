package io.innofang.rxjava2demo.imageloader;

import android.content.Context;
import android.widget.ImageView;

import io.innofang.rxjava2demo.imageloader.bean.Image;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 19:15
 * Description:
 */


public class RxImageLoader {
    private static final String TAG = "RxImageLoader";
    private static RxImageLoader mSingleton;
    private String mUrl;
    private RequestCreator mRequestCreator;

    private RxImageLoader(Builder builder) {
        mRequestCreator = new RequestCreator(builder.mContext);
    }

    public static RxImageLoader with(Context context) {
        if (null == mSingleton) {
            synchronized (RxImageLoader.class) {
                mSingleton = new Builder(context).build();
            }
        }
        return mSingleton;
    }

    public RxImageLoader load(String url) {
        mUrl = url;
        return mSingleton;
    }

    @SuppressWarnings("unchecked")
    public void into(final ImageView imageView) {
        Observable.concat(mRequestCreator.getImageFromMemory(mUrl),
                mRequestCreator.getImageFromDisk(mUrl),
                mRequestCreator.getImageFromNetwork(mUrl))
                .first(new Function<Image, Boolean>() {
                    @Override
                    public Boolean apply(Image image) throws Exception {
                        return image != null;
                    }

                }).subscribe(new Consumer<Image>() {
                    @Override
                    public void accept(Image image) throws Exception {
                        imageView.setImageBitmap(image.getBitmap());
                    }

                });
    }

    public static class Builder {

        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        public RxImageLoader build() {
            return new RxImageLoader(this);
        }

    }
}