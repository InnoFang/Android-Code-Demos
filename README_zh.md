# Android-Code-Demos

[English](https://github.com/InnoFang/Android-Code-Demos/blob/master/README.md) | 中文

这是我的 Android 学习 Demo  

+ [BaseDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/BaseDemo)

  这个 Demo 将集成一些常用代码，诸如 运行时权限封装与管理，CrashHandler，Fragment托管 等业务代码
  
+ [DataBindingDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/DataBingDemo)
  
  这个 Demo 包含 DataBinding 的基本用法
  
+ [MVPDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/MVPDemo)
 
  演示如何使用 MVP 模式
   
+ [SQLiteAdapterDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/SQLiteAdapterDemo) [SQLiteQueryDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/SQLIteQueryDemo)

  SQLiteAdapterDemo 演示了 SQLite 中适配器的基本用法，SQLiteQueryDemo 演示了 SQLite 中查询方法
  
+ [VolleyDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/VolleyDemo)

  这个 Demo 演示了 Volley 的基本用法，同时使用了 Volley 和 [聚合数据 API](https://www.juhe.cn/) 实现了一个查询手机归属地（中国）的功能，
  并且这个案例使用了 M-V-P 模式，所以你可以你可以学习如何使用 MVP 和 Volley 构建一个应用
  
+ [CallBackDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/CallBackDemo)   
    
   回调的基本用法，如何在 RecyclerView 中利用回调给 item 添加点击事件，如何使用回调加载网络图片并将图片显示在 ImageView 中      
   [文章链接](http://innofang.github.io/2017/03/08/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%A7%8B%E7%9A%84%E5%9B%9E%E8%B0%83/)
   
+ [RecyclerViewDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/RecyclerViewDemo)

   RecyclerView 的基本用法，并且遵循单一职责原则，将点击事件与 ItemTouchHelper 结合写成一个工具类，可以对 item 做滑动操作，并且对 Grid 和 Linear 两种情况做了处理
  
+ [SwipeBottomNavigationDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/SwipeBottomNavigationDemo)

   将 ViewPager 和 BottomNavigationView 结合在了一起，并且利用 _反射_ 修改了 BottomNavigationView 的切换动效
  
+ [ViewsDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/ViewsDemo)

   自定义 View 的练习，一些常用用法
   自定义时钟 [ClockView](https://github.com/InnoFang/Android-Code-Demos/blob/master/ViewsDemo/app/src/main/java/com/innofang/viewsdemo/views/ClockView.java)   
   自定义蜘蛛网数据展示 [SpiderNetView](https://github.com/InnoFang/Android-Code-Demos/blob/master/ViewsDemo/app/src/main/java/com/innofang/viewsdemo/views/SpiderNetView.java)  
  
+ [NDKDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/NDKDemo) 

   使用了 Android.mk , Application.mk 和 ndk-build 的方式构建，演示了 NDK 的基本用法  
   [文章链接](http://innofang.github.io/2017/04/16/Android-NDK%E5%BC%80%E5%8F%91%E4%BB%8E0%E5%88%B01/)
  
+ [CrashHandlerDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/CrashHandlerDemo)

   当应用崩溃时，错误日志会写入 SD 卡中，并会上传到服务器

+ [RuntimePermissionDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/RuntimePermissionDemo)

   运行时权限的封装与基本使用
  
+ [RxJava2Demo](https://github.com/InnoFang/Android-Code-Demos/tree/master/RxJava2Demo)   

   RxJava2 学习代码，并利用 RxJava2 实现了一个简单的还有三级缓存的图片加载框架 _RxImageLoader_  
   [文章链接](https://innofang.github.io/2017/04/28/RxJava2-%E4%BD%BF%E7%94%A8%E5%B0%8F%E8%AE%B0/)


+ [TransitionDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/TransitionDemo)            
                            
   Android 转场动画的学习，基本实现效果   
               
+ [OpenCVDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/OpenCVDemo)   
                 
   在 Android 中使用 OpenCV 进行图像处理   

+ [ProcessingDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/ProcessingDemo)   
                     
   使用 Android Studio 在 Android 设备中构建 processing 项目
             
+ [ModularizatinoDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/ModularizationDemo) &nbsp;&nbsp; [ARouterDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/ARouterDemo)             

   组件化的出现，使得项目的开发更易于多人协作，做到高内聚，低耦合，模块与模块之间相互独立，并且每个模块可以单独编译，其原理是使用 Gradle 进行管理。 ModularizationDemo 中演示了组件化开发中 Gradle 的基本配置，并且实现了一个简单的路由，用于页面跳转与数据传递。ARouter 是由阿里开源的一款用于组件化开发模块间跳转、数据传递的路由框架。ARouterDemo 演示了如何配置与基本的页面路由跳转以及数据传递。
   
+ [HTMLDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/HTMLDemo)

   HTMLDemo 不是用于演示关于 HTML 的编写，而是演示如何在 Android 中借助 WebView 使用 HTML 加载页面，并且还演示了 Java 与 JavaScript 的交互，如函数调用。
   
+ [JSoupDDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/JSoupDemo)

   当想解析某一网页的数据时，而有苦于没有特定的 api 的话，此时就可以尝试一下 [jsoup](https://github.com/jhy/jsoup)，这一个用于 Java 解析 HTML 的第三方框架。JSoupDemo 中演示了如何简单解析自己博客的的首页数据。

+ [EventBusDemo](https://github.com/InnoFang/Android-Code-Demos/tree/master/EventBusDemo)

   演示 EventBus 的基本使用
   
-----    


## LICENSE

[Apache License 2.0](https://github.com/InnoFang/Android-Code-Demos/blob/master/LICENSE)
  
