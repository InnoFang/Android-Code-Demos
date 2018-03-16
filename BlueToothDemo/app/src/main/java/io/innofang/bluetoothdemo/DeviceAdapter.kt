package io.innofang.bluetoothdemo

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Author: Inno Fang
 * Time: 2018/3/16 20:52
 * Description:
 */

class DeviceAdapter
constructor(val list: ArrayList<BluetoothDevice>, val context: Context)
    : RecyclerView.Adapter<DeviceAdapter.DeviceHolder>() {

    var itemClickListener: (device: BluetoothDevice) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DeviceHolder {
        val view = LayoutInflater
                .from(context)
                .inflate(android.R.layout.simple_expandable_list_item_2, parent, false)
        return DeviceHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceHolder, position: Int) {
        holder.bindHolder(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun refresh(list: List<BluetoothDevice>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class DeviceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(android.R.id.text1)
        private val address = itemView.findViewById<TextView>(android.R.id.text2)

        fun bindHolder(device: BluetoothDevice) {

            name.text = device.name
            address.text = device.address

            itemClickListener?.let { listener ->
                itemView.setOnClickListener { listener(device) }
            }
        }
    }
}