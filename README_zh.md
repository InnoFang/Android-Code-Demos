# Android-Code-Demos

[English](https://github.com/InnoFang/Android-Code-Demos/blob/master/README.md) | 中文

这是我的 Android 学习 Demo  

+ [DataBindingDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/DataBingDemo)
  
  这个 Demo 包含 DataBinding 的基本用法
  
+ [MVPDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/MVPDemo)
 
   演示如何使用 MVP 模式
   
+ [SQLiteAdapterDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/SQLiteAdapterDemo) [SQLiteQueryDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/SQLIteQueryDemo)

  SQLiteAdapterDemo 演示了 SQLite 中适配器的基本用法，SQLiteQueryDemo 演示了 SQLite 中查询方法
  
+ [VolleyDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/VolleyDemo)

  这个 Demo 演示了 Volley 的基本用法，同时使用了 Volley 和 [聚合数据 API](https://www.juhe.cn/) 实现了一个查询手机归属地（中国）的功能，
  并且这个案例使用了 M-V-P 模式，所以你可以你可以学习如何使用 MVP 和 Volley 构建一个应用
  
  - 如果你想看 Volley 的基本用法，那么可以参考一下几个 java 文件 
  
    * MainActivity.java
    * VolleyInterface.java
    * VolleyRequest.java
  - 如果想看手机归属地应用的实现，可以看其余包中的文件
  
+ [CallBackDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/CallBackDemo) 
 
   - CallBackDemo0: 回调的基本用法

   - CallBackDemo1: 在 RecyclerView 中利用回调给 item 添加点击事件

   - CallBackDemo2: 使用回调加载网络图片并将图片显示在 ImageView 中
 
   [文章链接](http://innofang.github.io/2017/03/08/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%A7%8B%E7%9A%84%E5%9B%9E%E8%B0%83/)
   
+ [RecyclerViewDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/RecyclerViewDemo)

  RecyclerView 的基本用法，并且遵循单一职责原则，将点击事件与 ItemTouchHelper 结合写成一个工具类，可以对 item 做滑动操作，并且对 Grid 和 Linear 两种情况做了处理
  
+ [SwipeBottomNavigationDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/SwipeBottomNavigationDemo)

  将 ViewPager 和 BottomNavigationView 结合在了一起，并且利用 _反射_ 修改了 BottomNavigationView 的切换动效
  
+ [ViewsDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/ViewsDemo)

  自定义 View 的练习，一些常用用法，并且自己实现了自定义时钟 [ClockView](https://github.com/InnoFang/Android-Code-Demos/blob/master/ViewsDemo/app/src/main/java/com/innofang/viewsdemo/views/ClockView.java) 自定义
  蜘蛛网数据展示 [SpiderNetView](https://github.com/InnoFang/Android-Code-Demos/blob/master/ViewsDemo/app/src/main/java/com/innofang/viewsdemo/views/SpiderNetView.java)
+ [NDKDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/NDKDemo) 

  使用了 Android.mk , Application.mk 和 ndk-build 的方式构建，演示了 NDK 的基本用法
  
  [文章链接](http://innofang.github.io/2017/04/16/Android-NDK%E5%BC%80%E5%8F%91%E4%BB%8E0%E5%88%B01/)
  
+ [CrashHandlerDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/CrashHandlerDemo)

  当应用崩溃时，错误日志会写入 SD 卡中，并会上传到服务器

+ [RuntimePermissionDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/RuntimePermissionDemo)

  运行时权限的封装与基本使用
## LICENSE

[Apache License 2.0](https://github.com/InnoFang/Android-Code-Demos/blob/master/LICENSE)
  
