package io.innofang.bluetoothdemo

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent

/**
 * Author: Inno Fang
 * Time: 2018/3/16 16:35
 * Description:
 */

class BlueToothController {
    private val adapter = BluetoothAdapter.getDefaultAdapter()

    fun isSupportBlueTooth() = adapter != null

    fun getBlueToothStatus(): Boolean {
        assert(adapter != null)
        return adapter.isEnabled
    }

    fun turnOnBlueTooth(activity: Activity, requestCode: Int){
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        activity.startActivityForResult(intent, requestCode)
    }

    fun turnOffBlueTooth() = adapter.disable()
}