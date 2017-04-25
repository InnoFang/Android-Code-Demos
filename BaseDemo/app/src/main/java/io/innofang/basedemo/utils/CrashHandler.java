package io.innofang.basedemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.innofang.basedemo.R;

import static android.os.Build.VERSION.SDK_INT;

/**
 * Author: Inno Fang
 * Description: 全局未捕获异常处理器
 */


public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private String mPath;
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler() {
    }

    /**
     * 获取单例对象
     *
     * @return CrashHandler 对象
     */
    public static CrashHandler getInstance() {
        return SingletonHolder.sInstance;
    }

    /**
     * 单例模式
     */
    private static class SingletonHolder {
        private static final CrashHandler sInstance = new CrashHandler();
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
        mPath = Environment.getExternalStorageDirectory().getPath()
                + "/"
                + mContext.getResources().getString(R.string.app_name)
                + "/log/";
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当程序中有未被捕获的异常时，系统将会自动调用该方法
     *
     * @param thread 出现未捕获异常的线程
     * @param ex     未捕获异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            /* 导出异常信息到 SD 卡中 */
            dumpExceptionToSDCard(ex);
            /* 这里可以上传异常信息到服务器 */
            uploadExceptionToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
/* 如果 SD 卡不存在或无法使用，则无法把异常信息写入 SD 卡 */
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                L.w(TAG, "sdcard unmounted, skip dump exception");
                return;
            }
        }

        File dir = new File(mPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.CHINA)
                .format(new Date(current));
        File file = new File(mPath + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (Exception e) {
            L.e(TAG, "dump crash info failed");
        }
    }

    @SuppressWarnings("deprecation")
    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        /* Android 版本号 */
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print('_');
        pw.println(SDK_INT);

        /* 手机制造商 */
        pw.print("Vendor: ");
        pw.println(Build.MODEL);

        /* CPU 架构 */
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }


    private void uploadExceptionToServer() {
        /*Upload Exception Message To Your Web Server*/
    }
}
