# CrashHandlerDemo

When the App crash, the log will be writtern to the SD card and uploaded to the remote serve.


## Reference

《Android开发艺术探索》

## NOTE

在 Thead 类中有这样一个方法 

```java
public static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
  SecurityManager sm = System.getSecurityManager();
  if (sm != null) {
      sm.checkPermission(
          new RuntimePermission("setDefaultUncaughtExceptionHandler")
              );
  }

   defaultUncaughtExceptionHandler = eh;
}
```
对这个方法官方的描述如下
>Set the default handler invoked when a thread abruptly terminates
due to an uncaught exception, and no other handler has been defined
for that thread.

大致意思如下：

当线程由于一个未捕获的异常而终止时，并且没有定义其他的处理程序，那么这个设置默认处理程序的方法就会被调用

通俗的说，如果有一个程序由于一个未被捕获的异常而异常终止时，那么就会调用这个方法，并且回调 `UncaughtExceptionHandler` 的 `uncaughtException(Thread t, Throwable e)` 方法

UncaughtExceptionHandler 接口及方法如下：
```java
 @FunctionalInterface
    public interface UncaughtExceptionHandler {
        /**
         * Method invoked when the given thread terminates due to the
         * given uncaught exception.
         * <p>Any exception thrown by this method will be ignored by the
         * Java Virtual Machine.
         * @param t the thread
         * @param e the exception
         */
        void uncaughtException(Thread t, Throwable e);
    }
```

那么我们要做的就是，实现这个接口，当程序由于未捕获的异常而终止时，可以执行我们想要的操作，比如给用户一个提示框提示用户程序一崩溃而不是闪退，把 Crash 日志写入 SD 卡，然后上传至服务器，这样有利于开发者对发现的错误进行调试。
