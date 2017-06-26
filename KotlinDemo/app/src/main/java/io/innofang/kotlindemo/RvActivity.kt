package io.innofang.kotlindemo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import io.innofang.koolweather.utils.event.OnRecyclerItemListener

class RvActivity : AppCompatActivity() {

    private val numberRecyclerView: RecyclerView
            by lazy { findViewById(R.id.number_recycler_view) as RecyclerView }

    private val adapter: RvAdapter by lazy { RvAdapter(initData()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        numberRecyclerView.layoutManager = LinearLayoutManager(RvActivity@ this)
        numberRecyclerView.adapter = adapter

        // add a click listener for recycler view
        /*adapter.action = {
            toast(it)
        }*/
        val action = OnRecyclerItemListener(numberRecyclerView)
        action.onItemClick = {
            if (it is RvAdapter.RvViewHolder) {
                toast(it.infoTextView.text.toString())
            }
        }
        numberRecyclerView.addOnItemTouchListener(action)
    }

    fun initData(): List<Int> = intent?.getStringExtra("value")
            ?.let { Integer.valueOf(it) }
            ?.let {
                List<Int>(it) {
                    it ->
                    it
                }
            } ?: List<Int>(0) { it -> it }

    fun Context.toast(text: String = "", time: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, time).show()
    }
}

