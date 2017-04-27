# RxJava2Demo

RxJava2 Study Demo

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

 + `Schedulers.immediate()` 在当前线程中运行（默认）
 + `Schedulers.io()` 有读写操作或者信息交互操作时使用。会启动新的线程进行操作，但是在其内部使用的是无数量上线的线程池，若有空闲线程存在会直接调用，效率上有一定优势。
 + `Schedulers.computation()` 当进行 CPU 密集型计算时（并且不会被 IO 操作限制性能）使用。相比于 `Schedulers.io()` 方法内部的实现，`Schedulers.computation()` 的内部实现使用的是有固定数量的线程池，其大小为 CPU 核数。所以若在这里面执行 IO 操作会浪费 CPU 和大量时间
 + `AndroidSchedulers.mainThread()` 在 Android 主线程 ( 即 UI 线程 )中执行
 
 #### 线程切换
 
 上面提到了线程控制，并且我们都知道耗时操作要在子线程中执行，更新 UI 的操作要在主线程中执行，那么我们怎样才能实现这种在两种线程内执行功能的操作呢？
 
 答案就是使用线程切换，RxJava 提供了两种方法
  + `subscribeOn()` 指定 Observable 对象所执行操作的线程
  + `observeOn()` 指定 Observer 对象所执行操作的线程
  
**注意** 如果指定了 `subscribeOn()` 但是没有指定 `observeOn()` 方法，那么对应的 Observer 对象的操作线程就是跟 Observable 一样的了

有了上面两个方法，那么就可以在创建 Observable 对象的时候进行网络请求，并用 `subscribeOn()`  指定在 `Schedulers.io()`  中执行，然后再利用 `observeOn()` 指定 Observer 的操作是在 `AndroidSchedulers.mainThread()` 中，那么我们就可以在 Observer 对象的 `onNext()` 方法中，将获得资源添加给对应的 View。参考操作如下：
```java
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
```
  
 
 
 
 
