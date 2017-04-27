# RxJava2Demo


## NOTE

### RxJava 的基本使用

#### 创建 Observable 对象

+ 使用 `create()` 方法创建 `Observable` 对象
```java
Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
    @Override
    public void subscribe(ObservableEmitter<String> e) throws Exception {
        e.onNext("Today's news update");
        e.onNext("Today's topic is Study");
        e.onComplete();
    }
});
```
**注意** `ObservableEmitter` 可以理解为发射器，可以调用 `onNext()`, `onComplete()`, `onError()` 等方法，`onNext()` 方法可以多次调用，`onComplete()` 方法和 `onError()` 方法是互斥的，也就是说两者只能调用其一，如果两者同时使用了，则会调用上面的那个

 + 使用 `just()` 方法 或 `fromArray()` 方法创建 `Observable` 对象的方式
```java
Observable<String> observable = Observable.just("Topic 1", "Heat 1", "News");
Observable<String> observable = Observable.fromArray("Topic 1", "Heat 1", "News");
```
上面调用的 `just()` 方法最多可以传递十个参数，而 `fromArray()` 方法可以传入不定长的参数，而在 `just()` 方法的内部也是调用的 `fromArray()` 方法

+ 使用 `fromCallable()` 方法创建 `Observable` 对象
```java
Observable<String> observable = Observable.fromCallable(new Callable<String>() {
    @Override
    public String call() throws Exception {
        return "Topic is Study";
    }
});
```
可以看到使用这种方式创建 `Observable` 对象只能返回一个数据

#### 创建 `Observer` 对象

创建 `Observer` 对象比较简单，直接 new 就可以了
```java
Observer<String> observer = new Observer<String>() {
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
```
**注意** ` onSubscribe(Disposable d)` 方法是在每次 Observer 接收来自 Observable 数据的时候调用，其余的 `onNext(String value)`方、`onError(Throwable e)`方法、`onComplete()`方法，对应于 Observable 中调用的方法，其中 `onError(Throwable e)`方法、`onComplete()`方法两者只能调用一个，都不会重复调用

#### 让 Observable 对象与 Observer 对象实现订阅关系

使用的是 Observable 对象的 `subscribe()` 方法，传入 Observer 对象
```java
observable.subscribe(observer);
```
若不想传入 Observer 对象，还可以更改为 Consumer 对象
```java
observable.subscribe(new Consumer<String>() {
    @Override
    public void accept(String s) throws Exception {
        System.out.println("accept is called, " + s);
    }
});
```
这里的 `accept()` 方法对应于之前的 `onNext()` 方法，也就是说若不没有 complete 或者 error 的情况下，就可以使用这种方式，使用了多少次 `onNext()` 方法，就会调用多少次 `accept()` 方法

### Scheduler 线程控制

 + `Schedulers.immediate();` 在当前线程中运行（默认）
 + `Schedulers.io();` 有读写操作或者信息交互操作时使用。会启动新的线程进行操作，但是在其内部使用的是无数量上线的线程池，若有空闲线程存在会直接调用，效率上有一定优势。
 + `Schedulers.computation();` 当进行 CPU 密集型计算时（并且不会被 IO 操作限制性能）使用。相比于 `io()` 方法内部的实现，`computation()` 的内部实现使用的是有固定数量的线程池，其大小为 CPU 核数。所以若在这里面执行 IO 操作会浪费 CPU 和大量时间
 + `AndroidSchedulers.mainThread();` 在 Android 主线程 ( 即 UI 线程 )中执行
 
 
 
 
 
