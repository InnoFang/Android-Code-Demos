package io.innofang.rxjava2demo.imageloader.cache;

import io.innofang.rxjava2demo.imageloader.bean.Image;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 19:32
 * Description:
 */


public abstract class CacheObservable {

    public Observable getImage(final String url) {
        return Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(ObservableEmitter<Image> e) throws Exception {
                if (!e.isDisposed()) {
                    Image image = getDataFromCache(url);
                    if (null != image)
                        e.onNext(image);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public abstract Image getDataFromCache(String url);

    public abstract void putDataToCache(Image image);

}
