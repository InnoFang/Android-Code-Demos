package io.innofang.rxjava2demo.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 15:06
 * Description: RxJava2 Operator Test
 */


public class OperatorTest {

    public static void main(String[] args) {
        /* map : 将数字转化为字符串 */
        System.out.println("+-------------Operator <map> test-------------+");
        Observable.just(1).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "The number is " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });



        System.out.println();
        /* flatMap : 将 A(int) 转换为 B(float) 在 转换为 C(String) */
        System.out.println("+-------------Operator <flatMap> test-------------+");
        Observable.just(1).flatMap(new Function<Integer, ObservableSource<Float>>() {
            @Override
            public ObservableSource<Float> apply(Integer integer) throws Exception {
                return Observable.just(Float.valueOf(integer));
            }
        }).flatMap(new Function<Float, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Float aFloat) throws Exception {
                return Observable.just(String.valueOf(aFloat));
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });



        System.out.println();
        /* Merge : 合并两个 List */
        System.out.println("+-------------Operator <merge> test-------------+");
        Observable.merge(getListOne(), getListTwo()).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> list) throws Exception {
                for (String s : list) {
                    System.out.println(s);
                }
            }
        });



        System.out.println();
        /* debounce : 主要用于关键词搜索，这里效果不明显，所以一下代码仅作参考（一般结合 RxBinding 与 EditText 使用） */
        System.out.println("+-------------Operator <debounce> test-------------+");
        for (int i = 0; i < 5; i++) {
            debounceTest(i);
        }



        System.out.println();
        /* throttleFirst : 重复请求，规定时间内以第一个数据为准（一般结合 RxBinding 与 Button 使用） */
        System.out.println("+-------------Operator <throttleFirst> test-------------+");
        for (int i = 0; i < 5; i++) {
            throttleFirstTest(i);
        }



        System.out.println();
        /* interval : 创建一个按固定时间间隔发射整数序列的 Observable */
        System.out.println("+-------------Operator <interval> test-------------+");
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(20, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        System.out.println("accept " + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private static void throttleFirstTest(int i) {
        Observable.just(i)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }

    private static void debounceTest(int i) {
        Observable.just(i)
                .debounce(1000, TimeUnit.MILLISECONDS)
                /*以最近请求的数据为准*/
                .switchMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(String.valueOf(integer));
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

    }

    public static Observable<List<String>> getListOne(){
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        return Observable.just(list);
    }
    public static Observable<List<String>> getListTwo(){
        List<String> list = new ArrayList<>();
        list.add("three");
        list.add("four");
        return Observable.just(list);
    }
}
