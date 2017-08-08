package io.innofang.library.router;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 10:28
 * Description:
 */


import android.widget.Toast;

import java.util.HashMap;

import io.innofang.library.App;

/**
 * 组件间交互数据的总线，每个组件通过定义各自提供出来的方法设置到总线中
 * 其他组件通过总线来获取到另一个组件提供出来的数据。
 */
public class FunctionBus {

    private static HashMap<Class, Object> sFunctionBus = new HashMap<>();

    /**
     * 将提供出来的数据对象设置到组件中
     *
     * @param o 提供出来的接口实现对象
     */
    public static void setFunction(Object o) {
        Class<?>[] interfaces = o.getClass().getInterfaces();
        for (Class c : interfaces) {
            if (sFunctionBus.containsKey(c)) {
                throw new IllegalStateException("已经使用" + c.getName() + "传递过一次数据");
            }
            sFunctionBus.put(c, o);
        }
    }

    /**
     * 通过字节码获取到提供出来的数据对象
     *
     * @param c
     * @param <T>
     * @return 返回的是对象
     */
    public static <T> T getFunction(Class<T> c) {
        T obj = (T) sFunctionBus.get(c);
        if (obj == null) {
            Toast.makeText(App.getContext(), c.getName() + "还没有提供方法出来", Toast.LENGTH_SHORT).show();
            return null;
        }
        //获取完数据，就从集合中清除。
        sFunctionBus.remove(c);
        return obj;
    }
}