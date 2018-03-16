package io.innofang.bluetoothdemo

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
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

    /**
     * Turn on bluetooth
     */
    fun turnOnBlueTooth(activity: Activity, requestCode: Int) {
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        activity.startActivityForResult(intent, requestCode)
    }

    fun turnOffBlueTooth() = adapter.disable()

    /**
     * 打开蓝牙可见性
     */
    fun enableVisibly(context: Context) {
        with(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)) {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
            context.startActivity(this)
        }
    }

    /**
     * 查找设备
     */
    fun findDevice() {
        assert(adapter != null)
        adapter.startDiscovery()
    }

    /**
     * 获取绑定设备
     */
    fun getBonderDeviceList(): List<BluetoothDevice> = adapter.bondedDevices.toList()
}