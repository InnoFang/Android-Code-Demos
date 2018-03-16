package io.innofang.bluetoothdemo

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_2.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1
    private val controller = BlueToothController()
    private val toast: Toast by lazy { Toast.makeText(this, "", Toast.LENGTH_SHORT) }

    private val deviceList = ArrayList<BluetoothDevice>()
    private val bondedDeviceList = ArrayList<BluetoothDevice>()

    private val deviceAdapter = DeviceAdapter(deviceList, this)

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
//            val state: Int = intent.getIntExtra(BluetoothAdapter.EXTRA_LOCAL_NAME, -1)
//            when (state) {
//                BluetoothAdapter.STATE_OFF -> toast.short("STATE_OFF")
//                BluetoothAdapter.STATE_ON -> toast.short("STATE_ON")
//                BluetoothAdapter.STATE_TURNING_ON -> toast.short("STATE TURNING ON")
//                BluetoothAdapter.STATE_TURNING_OFF -> toast.short("STATE TURNING OFF")
//                else -> toast.short("UNKNOWN STATE")
//            }
            when (intent.action) {
            // 开始查找
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
//                    progress_bar.visibility = View.VISIBLE
                    // 初始化列表
                    deviceList.clear()
                    deviceAdapter.notifyDataSetChanged()
                }
            // 查找结束
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
//                    progress_bar.visibility = View.GONE
                }
            // 查找设备
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    // 找到一个添加一个
                    deviceList.add(device)
                    deviceAdapter.notifyDataSetChanged()
                }
                BluetoothAdapter.ACTION_SCAN_MODE_CHANGED -> {
                    val scanMode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, 0)
                    if (scanMode == BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
//                        progress_bar.visibility = View.VISIBLE
                    } else {
//                        progress_bar.visibility = View.GONE
                    }
                }
                else -> {
                    val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    device ?: let { toast.short("No Device"); return }

                    val status = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, 0)
                    when (status) {
                        BluetoothDevice.BOND_BONDED -> toast.short("Bonded ${device.name}")
                        BluetoothDevice.BOND_BONDING -> toast.short("Bonding ${device.name}")
                        BluetoothDevice.BOND_NONE -> toast.short("Not bond ${device.name}")
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

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
        device_recycler_view.adapter = deviceAdapter
        device_recycler_view.layoutManager = LinearLayoutManager(this)

//        is_support_blue_tooth.setOnClickListener(this)
//        is_blue_tooth_enable.setOnClickListener(this)
//        turn_on_blue_tooth.setOnClickListener(this)
//        turn_off_blue_tooth.setOnClickListener(this)

//        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        val filter = IntentFilter().apply {
            addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)    // 开始查找
            addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)   // 结束查找
            addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)    // 设备扫描模式改变
            addAction(BluetoothDevice.ACTION_FOUND)                 // 查找设备
            addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)    // 绑定状态
        }

        registerReceiver(receiver, filter)

        controller.turnOnBlueTooth(this, REQUEST_CODE)
    }


//    override fun onClick(view: View) {
//        when (view.id) {
//            R.id.is_support_blue_tooth -> {
//                "Is support? ${controller.isSupportBlueTooth()}".let {
//                    it.i()
//                    toast.short(it)
//                }
//            }
//            R.id.is_blue_tooth_enable -> {
//                "Is enable? ${controller.getBlueToothStatus()}".let {
//                    it.i()
//                    toast.short(it)
//                }
//            }
//            R.id.turn_on_blue_tooth -> {
//                controller.turnOnBlueTooth(this, REQUEST_CODE)
//            }
//            R.id.turn_off_blue_tooth -> {
//                controller.turnOffBlueTooth()
//            }
//        }
//    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.enable_visibility -> controller.enableVisibly(this)
            R.id.find_device -> {
                deviceAdapter.refresh(deviceList)
                controller.findDevice()
                deviceAdapter.itemClickListener = { device -> device.createBond() }
            }
            R.id.bonded_device -> {
                bondedDeviceList.clear()
                bondedDeviceList.addAll(controller.getBonderDeviceList())
                deviceAdapter.refresh(bondedDeviceList)
                deviceAdapter.itemClickListener = {}
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
