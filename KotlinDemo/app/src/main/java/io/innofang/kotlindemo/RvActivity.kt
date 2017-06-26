package io.innofang.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.innofang.weather.R

class RvActivity : AppCompatActivity() {

    private val numberRecyclerView: RecyclerView
            by lazy { findViewById(R.id.number_recycler_view) as RecyclerView }

    private val adapter: RvAdapter by lazy { RvAdapter(initData()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        numberRecyclerView.layoutManager = LinearLayoutManager(RvActivity@ this)
        numberRecyclerView.adapter = adapter
    }

    fun initData(): List<Int> = intent?.getStringExtra("value")
            ?.let { Integer.valueOf(it) }
            ?.let {
                List<Int>(it) {
                    it ->
                    it
                }
            } ?: List<Int>(0) { it -> it }

}

