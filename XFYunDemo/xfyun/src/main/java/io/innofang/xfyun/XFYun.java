package io.innofang.xfyun;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Author: Inno Fang
 * Time: 2017/10/3 16:06
 * Description:
 */


public class XFYun {

    public static void init(Context context) {
        SpeechUtility.createUtility(context, SpeechConstant.APPID +"=59b3bb5d");
    }

}
