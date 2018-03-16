package io.innofang.bluetoothdemo

import android.widget.Toast

/**
 * Author: Inno Fang
 * Time: 2018/3/16 17:09
 * Description:
 */

fun Toast.short(text: String) {
    setText(text)
    duration = Toast.LENGTH_SHORT
    show()
}

fun Toast.long(text: String) {
    setText(text)
    duration = Toast.LENGTH_LONG
    show()
}