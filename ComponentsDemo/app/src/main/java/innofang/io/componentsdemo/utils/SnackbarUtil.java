package innofang.io.componentsdemo.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Author: Inno Fang
 * Time: 2018/1/1 21:29
 * Description:
 */


public class SnackbarUtil {

    public static void showSnackbar(View v,String snackbarText) {
        if (null == v || null == snackbarText) return;

        Snackbar.make(v, snackbarText, Snackbar.LENGTH_LONG).show();
    }
}
