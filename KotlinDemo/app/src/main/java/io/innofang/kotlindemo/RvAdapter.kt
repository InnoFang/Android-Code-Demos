package io.innofang.kotlindemo

import android.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Author: Inno Fang
 * Time: 2017/6/24 11:35
 * Description:
 */

public class RvAdapter(var list: List<Int>) : RecyclerView.Adapter<RvAdapter.RvViewHolder>() {


    var action: ((text: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RvViewHolder {
        return RvViewHolder(LayoutInflater.from(parent!!.context)
                .inflate(R.layout.simple_list_item_1, parent, false))

    }

    override fun onBindViewHolder(holder: RvViewHolder?, position: Int) {
        holder!!.infoTextView.text = list[position].toString()
        holder.itemView.setOnClickListener { action?.invoke(list[position].toString()) }

    }

    override fun getItemCount(): Int = list?.let { list.size } ?: 0

    inner class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val infoTextView: TextView by lazy { itemView.findViewById(android.R.id.text1) as TextView }


    }

}
