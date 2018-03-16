package io.innofang.bluetoothdemo

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_CODE = 1
    private val controller = BlueToothController()
    private val toast: Toast by lazy { Toast.makeText(this, "", Toast.LENGTH_SHORT) }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val state: Int = intent.getIntExtra(BluetoothAdapter.EXTRA_LOCAL_NAME, -1)
            when (state) {
                BluetoothAdapter.STATE_OFF -> toast.short("STATE_OFF")
                BluetoothAdapter.STATE_ON -> toast.short("STATE_ON")
                BluetoothAdapter.STATE_TURNING_ON -> toast.short("STATE TURNING ON")
                BluetoothAdapter.STATE_TURNING_OFF -> toast.short("STATE TURNING OFF")
                else -> toast.short("UNKNOWN STATE")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RequestPermissions.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                object : RequestPermissions.OnPermissionsRequestListener {
                    override fun onGranted() {
                        initEvent()
                    }

                    override fun onDenied(deniedList: MutableList<String>?) {
                        toast.long("Permission has been denied, it's so sorry you cannot use our app.")
                    }

                })
    }

    private fun initEvent() {
        is_support_blue_tooth.setOnClickListener(this)
        is_blue_tooth_enable.setOnClickListener(this)
        turn_on_blue_tooth.setOnClickListener(this)
        turn_off_blue_tooth.setOnClickListener(this)

        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(receiver, filter)
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.is_support_blue_tooth -> {
                "Is support? ${controller.isSupportBlueTooth()}".let {
                    it.i()
                    toast.short(it)
                }
            }
            R.id.is_blue_tooth_enable -> {
                "Is enable? ${controller.getBlueToothStatus()}".let {
                    it.i()
                    toast.short(it)
                }
            }
            R.id.turn_on_blue_tooth -> {
                controller.turnOnBlueTooth(this, REQUEST_CODE)
            }
            R.id.turn_off_blue_tooth -> {
                controller.turnOffBlueTooth()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) return

        if (REQUEST_CODE == requestCode) {
            toast.short("Turn On")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RequestPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
