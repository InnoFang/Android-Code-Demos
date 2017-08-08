package io.innofang.library.router;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;

import io.innofang.library.BuildConfig;
import io.innofang.library.App;
import io.innofang.library.router.annotation.RouterParam;
import io.innofang.library.router.annotation.RouterUri;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 09:47
 * Description:
 */


public class RouterBus {

    //存储代理类的集合
    private static HashMap<Class, Object> sRouterMap = new HashMap<>();

    /**
     * 获取代理类
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getRouter(Class<T> c) {
        T router = (T) sRouterMap.get(c);
        if (router == null) {
            //创建代理类
            router = (T) Proxy.newProxyInstance(c.getClassLoader(), new Class[]{c}, InvocationHandlerIml);
            sRouterMap.put(c, router);
        }
        return router;

    }

    //创建InvocationHandler对象，用于连接委托类和代理类。
    private static InvocationHandler InvocationHandlerIml = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //获取方法上的注解
            RouterUri routerUri = method.getAnnotation(RouterUri.class);
            if (routerUri == null || TextUtils.isEmpty(routerUri.value())) {
                throw new IllegalArgumentException("没有注解的方法");
            }
            //获取注解value值
            String path = routerUri.value();
            Uri.Builder uriBuilder = Uri.parse(path).buildUpon();
            //获取参数注解值
            Annotation[][] annotationsParam = method.getParameterAnnotations();
            if (annotationsParam == null || annotationsParam.length == 0) {
                throw new IllegalArgumentException("参数上没有注解");
            }
            for (int i = 0; i < annotationsParam.length; i++) {
                Annotation[] annotations = annotationsParam[i];
                if (annotations == null || annotations.length == 0) {
                    throw new IllegalArgumentException("method" + method.getName() + ", args at" + i + "lack of annotation");
                }

                boolean findAnnotation = false;
                for (Annotation a : annotations) {
                    if (a != null && (a.annotationType()) == RouterParam.class) {
                        String keyParam = ((RouterParam) a).value();
                        uriBuilder.appendQueryParameter(keyParam, new Gson().toJson(args[i]));
                        findAnnotation = true;

                    }
                }
                if (!findAnnotation) {
                    throw new IllegalArgumentException("method " + method.getName() + " args at " + i + ", lack of annotion");
                }

            }

            Uri uri = uriBuilder.build();
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //判断这个intent是不是有效的intent
            boolean isValid = checkIntent(intent);
            if (isValid) {
                return intent;
            } else {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(App.getContext(), "子模块作为独立程序启动时，跳不到其他模块哟", Toast.LENGTH_SHORT).show();
                } else {
                    throw new IllegalStateException("can't resolve uri with" + uri.toString());
                }
            }
            return null;
        }
    };


    /**
     * 判断intent是否是可用的
     *
     * @param intent
     * @return
     */
    private static boolean checkIntent(Intent intent) {
        Context context = App.getContext();
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        if (infos.isEmpty() || null == infos) {
            return false;
        } else {
            return true;
        }
    }
}
