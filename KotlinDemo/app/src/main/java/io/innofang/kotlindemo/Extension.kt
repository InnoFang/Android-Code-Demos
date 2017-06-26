package io.innofang.kotlindemo

import android.support.v7.widget.RecyclerView

/**
 * Author: Inno Fang
 * Time: 2017/6/24 12:40
 * Description:
 */

class Extension {

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }


}