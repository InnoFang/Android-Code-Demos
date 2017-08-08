package io.innofang.library.router.module_b;

import android.content.Intent;

import io.innofang.library.router.annotation.RouterParam;
import io.innofang.library.router.annotation.RouterUri;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 10:17
 * Description:
 */


public interface RouterModuleB {

    public static String ROUTER_PARAM_NUM = "num";

    @RouterUri("innofang://module_b")
    public Intent newIntentFromModuleB(@RouterParam(ROUTER_PARAM_NUM) int num);

}
