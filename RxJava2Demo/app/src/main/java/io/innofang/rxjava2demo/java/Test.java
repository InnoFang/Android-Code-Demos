package io.innofang.rxjava2demo.java;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: Inno Fang
 * Time: 2017/4/27 13:20
 * Description:
 */


public class Test {


    public static void main(String[] args) {
        Test test = new Test();
        Observable<String> observable = test.getObservable();
        Observer<String> observer = test.getObserver();
//        observable.subscribe(observer);
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("accept is called, " + s);
            }
        });
    }

    public Observable<String> getObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Today's news update");
                e.onNext("Today's topic is Study");
                e.onComplete();
            }
        });
        /* 下面两个方法作用类似，just 的内部调用的就是 fromArray */
//     return Observable.just("Topic 1", "Heat 1", "News");
//     return Observable.fromArray("Topic 1", "Heat 1", "News");
        /* 只能发送一个数据 */
        /*return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Topic is Study";
            }
        });*/
    }

    public Observer<String> getObserver() {
        return new Observer<String>() {
            /**
             * 每次接收前调用
             * @param d
             */
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe: is called");
            }

            @Override
            public void onNext(String value) {
                System.out.println("onNext: is called");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: is called");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete: is called");
            }
        };
    }

}
